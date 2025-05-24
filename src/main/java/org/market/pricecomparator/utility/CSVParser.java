package org.market.pricecomparator.utility;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import com.opencsv.exceptions.CsvValidationException;

import org.market.pricecomparator.model.entity.Discount;
import org.market.pricecomparator.model.entity.PriceEntry;
import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.Store;
import org.market.pricecomparator.model.enums.MeasurementUnit;
import org.market.pricecomparator.repository.DiscountRepository;
import org.market.pricecomparator.repository.PriceEntryRepository;
import org.market.pricecomparator.repository.ProductRepository;
import org.market.pricecomparator.repository.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CSVParser {

    private static final Logger logger = LoggerFactory.getLogger(CSVParser.class);
    private final ProductRepository productRepository;
    private final PriceEntryRepository priceEntryRepository;
    private final DiscountRepository discountRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public CSVParser(ProductRepository productRepository, PriceEntryRepository priceEntryRepository, DiscountRepository discountRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.priceEntryRepository = priceEntryRepository;
        this.discountRepository = discountRepository;
        this.storeRepository = storeRepository;
    }

    /**
     * Finds CSV files in the given directory, categorizes them based on content
     * as product or discount files, according to naming conventions.
     * Store name and date are extracted from the filenames, for each filed is
     * called a method that processes the content
     */
    public void processCSVDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        if(!directory.exists() || !directory.isDirectory()) {
            logger.error("Invalid directory path: " + directoryPath);
            return;
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".csv"));

        if(files == null || files.length == 0) {
            logger.warn("No files in directory: " + directoryPath);
            return;
        }

        Map<Path, CSVFileInfo> productFiles = new HashMap<>();
        Map<Path, CSVFileInfo> discountFiles = new HashMap<>();
        for(File file : files) {
            String fileName = file.getName();
            String keyword = "_discounts_";

            if(fileName.contains(keyword) && fileName.endsWith(".csv")) {
                int index = fileName.indexOf(keyword);
                String storeName = fileName.substring(0, index);

                int startIndexDate = index + keyword.length();
                int endIndexDate = fileName.indexOf(".csv");
                String dateString = fileName.substring(startIndexDate, endIndexDate);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateString, formatter);
                //System.out.println(storeName + " " + date);

                discountFiles.put(file.toPath(), new CSVFileInfo(storeName, date));
            }
            else
                if (fileName.endsWith(".csv")){
                    int index = fileName.lastIndexOf('_');
                    String storeName = fileName.substring(0, index);

                    int startIndexDate = fileName.lastIndexOf("_");
                    int endIndexDate = fileName.indexOf(".csv");
                    String dateString = fileName.substring(startIndexDate + 1, endIndexDate);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    //System.out.println(storeName + " " + date);

                    productFiles.put(file.toPath(), new CSVFileInfo(storeName, date));
                }
        }

        logger.info("{} product files found", productFiles.size());
        logger.info("{} discount files found", discountFiles.size());

        productFiles.forEach(this::processProductFile);
        discountFiles.forEach(this::processDiscountFile);

        logger.info("All files in {} directory have been processed", directory.getName());
    }

    private void processProductFile(Path filePath, CSVFileInfo csvFileInfo) {
        try {
            logger.info("Processing product file {}", filePath.getFileName());
            parseProductFile(filePath, csvFileInfo.getStoreName(), csvFileInfo.getEntryDate());
        } catch (IOException e) {
            logger.error("Error reading product file {}", filePath.getFileName(), e);
        } catch (Exception e) {
            logger.error("Error processing product file {}", filePath.getFileName(), e);
        }

    }
    private void processDiscountFile(Path filePath, CSVFileInfo csvFileInfo){
        try {
            logger.info("Processing discount file {}", filePath.getFileName());
            parseDiscountFile(filePath, csvFileInfo.getStoreName(), csvFileInfo.getEntryDate());
        } catch (IOException e) {
            logger.error("Error reading discount file {}", filePath.getFileName(), e);
        } catch (Exception e) {
            logger.error("Error processing discount file {}", filePath.getFileName(), e);
        }
    }


    /**
     * Reads a CSV file containing products, creates or finds the store and products,
     * saves price entries for each product in the file
     */
    @Transactional
    public void parseProductFile(Path filePath, String storeName, LocalDate entryDate) throws IOException {

        Store store = new Store();
        Product product = new Product();

        Optional<Store> existingStore = storeRepository.findByName(storeName);

        if(existingStore.isEmpty()) {
            logger.info("Store {} not found", storeName);
            Store newStore = new Store();
            newStore.setName(storeName);
            storeRepository.save(newStore);

            logger.info("Store {} created", storeName);

            store = newStore;
        }
        else
            store = existingStore.get();

        com.opencsv.CSVParser parserInstance = new CSVParserBuilder().
                withSeparator(';').
                build();

        try(Reader fileReader = new FileReader(filePath.toFile());
            CSVReader reader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .withCSVParser(parserInstance).
                    build()) {

            String[] record;
            int lineIndex = 1;

            while((record = reader.readNext()) != null)
            {
                lineIndex++;
               if (record.length < 8)
               {
                   logger.warn("Incomplete entry on line {} in file {}", lineIndex, filePath.getFileName());
                   continue;
               }

               if(record[0] == null || record[1] == null || record[2] == null || record[3] == null ||
                       record[4] == null || record[5] == null || record[6] == null || record[7] == null) {
                   logger.warn("Missing values on line {} in file {}", lineIndex, filePath.getFileName());
                   continue;
               }

               String recordName = record[1].trim();
               String recordCategory = record[2].trim();
               String recordBrand = record[3].trim();
               Double recordPackageQuantity = Double.parseDouble(record[4].trim());
               MeasurementUnit recordUnit = parseUnit(record[5]);
               BigDecimal recordPrice = new BigDecimal(record[6].trim());
               String recordCurrency = record[7].trim();


               Optional<Product> existingProduct = productRepository.findByNameAndBrandAndPackageQuantityAndUnit(recordName, recordBrand, recordPackageQuantity, recordUnit);
               if(existingProduct.isEmpty()) {

                   Product newProduct = new Product();;
                   newProduct.setName(recordName);
                   newProduct.setCategory(recordCategory);
                   newProduct.setBrand(recordBrand);
                   newProduct.setPackageQuantity(recordPackageQuantity);
                   newProduct.setUnit(recordUnit);
                   newProduct.setCurrency(recordCurrency);
                   productRepository.save(newProduct);

                   logger.info("Product {} created", recordName);

                   product = newProduct;
               }
               else
                   product = existingProduct.get();


               Optional<PriceEntry> existingPriceEntry = priceEntryRepository.findPriceEntryByProductAndStoreAndEntryDate(product, store, entryDate);

               if(existingPriceEntry.isEmpty()) {
                   PriceEntry priceEntry = new PriceEntry();
                   priceEntry.setProduct(product);
                   priceEntry.setStore(store);
                   priceEntry.setPrice(recordPrice);
                   priceEntry.setEntryDate(entryDate);
                   priceEntryRepository.save(priceEntry);

                   store.addPriceEntry(priceEntry);
                   product.addPriceEntry(priceEntry);

                   logger.info("Price entry for product {} in store {} created", product.getName(), store.getName());
               }
               else {
                   store.addPriceEntry(existingPriceEntry.get());
                   product.addPriceEntry(existingPriceEntry.get());

               }

            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a CSV file containing discounts, creates or finds the store and products,
     * saves discounts for each product in the file
     */
    @Transactional
    public void parseDiscountFile(Path filePath, String storeName, LocalDate entryDate) throws IOException {
        Store store = new Store();
        Product product = new Product();

        Optional<Store> existingStore = storeRepository.findByName(storeName);

        if(existingStore.isEmpty()) {
            logger.info("Store {} not found", storeName);
            Store newStore = new Store();
            newStore.setName(storeName);
            storeRepository.save(newStore);

            logger.info("Store {} created", storeName);

            store = newStore;
        }
        else
            store = existingStore.get();

        com.opencsv.CSVParser parserInstance = new CSVParserBuilder().
                withSeparator(';').
                build();

        try(Reader fileReader = new FileReader(filePath.toFile());
            CSVReader reader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .withCSVParser(parserInstance).
                    build()) {

            String[] record;
            int lineIndex = 1;

            while((record = reader.readNext()) != null)
            {
                lineIndex++;
                if (record.length < 9)
                {
                    logger.warn("Incomplete entry on line {} in file {}", lineIndex, filePath.getFileName());
                    continue;
                }

                if(record[0] == null || record[1] == null || record[2] == null || record[3] == null || record[4] == null ||
                        record[5] == null || record[6] == null || record[7] == null || record[8] == null) {
                    logger.warn("Missing values on line {} in file {}", lineIndex, filePath.getFileName());
                    continue;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String recordName = record[1].trim();
                String recordBrand = record[2].trim();
                Double recordPackageQuantity = Double.parseDouble(record[3].trim());
                MeasurementUnit recordUnit = parseUnit(record[4]);
                String recordCategory = record[5].trim();
                LocalDate recordStartDate = LocalDate.parse(record[6], formatter);
                LocalDate recordEndDate = LocalDate.parse(record[7], formatter);
                BigDecimal recordPercentage = new BigDecimal(record[8].trim());


                Optional<Product> existingProduct = productRepository.findByNameAndBrandAndPackageQuantityAndUnit(recordName, recordBrand, recordPackageQuantity, recordUnit);
                if(existingProduct.isEmpty()) {

                    Product newProduct = new Product();;
                    newProduct.setName(recordName);
                    newProduct.setCategory(recordCategory);
                    newProduct.setBrand(recordBrand);
                    newProduct.setPackageQuantity(recordPackageQuantity);
                    newProduct.setUnit(recordUnit);
                    newProduct.setCurrency("RON");
                    productRepository.save(newProduct);

                    logger.info("Product {} created", recordName);

                    product = newProduct;
                }
                else
                    product = existingProduct.get();


                Optional<Discount> existingDiscount = discountRepository.findByProductAndStoreAndStartDateAndEndDate(product, store, recordStartDate, recordEndDate);

                if(existingDiscount.isEmpty()) {
                    Discount newDiscount = new Discount();
                    newDiscount.setProduct(product);
                    newDiscount.setStore(store);
                    newDiscount.setStartDate(recordStartDate);
                    newDiscount.setEndDate(recordEndDate);
                    newDiscount.setPercentage(recordPercentage);
                    newDiscount.setEntryDate(entryDate);
                    discountRepository.save(newDiscount);

                    store.addDiscount(newDiscount);
                    product.addDiscount(newDiscount);

                    logger.info("Discount for product {} in the store {} created", product.getName(), store.getName());
                }
                else {
                    store.addDiscount(existingDiscount.get());
                    product.addDiscount(existingDiscount.get());
                }

            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     *Converts a string into the corresponding measurement unit
     */
    private MeasurementUnit parseUnit(String unit) {
        if(unit == null || unit.isEmpty())
            return null;

        String normalizedUnit = unit.trim().toLowerCase();

        switch (normalizedUnit) {
            case "kg":
                return MeasurementUnit.KG;
            case "g":
                return MeasurementUnit.G;
            case "l":
                return MeasurementUnit.L;
            case "ml":
                return MeasurementUnit.ML;
            case "buc":
            case "bucati":
            case "bucata":
            case"rola":
            case"role":
                return MeasurementUnit.PIECE;
            default:
                logger.warn("Unrecognized measurement unit {}", normalizedUnit);
                return null;
        }

    }
    private static class CSVFileInfo {
        private final String storeName;
        private final LocalDate entryDate;

        public CSVFileInfo(String storeName, LocalDate entryDate) {
            this.storeName = storeName;
            this.entryDate = entryDate;
        }

        public String getStoreName() {return storeName;}
        public LocalDate getEntryDate() {return entryDate;}
    }
}
