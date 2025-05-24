package org.market.pricecomparator.service;

import org.market.pricecomparator.dto.GraphPointDTO;
import org.market.pricecomparator.exceptions.ResourceNotFound;
import org.market.pricecomparator.model.entity.PriceEntry;
import org.market.pricecomparator.repository.PriceEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceEntryService {

    private final PriceEntryRepository priceEntryRepository;

    @Autowired
    public PriceEntryService(PriceEntryRepository priceEntryRepository) {
        this.priceEntryRepository = priceEntryRepository;
    }

    public Map<String, List<GraphPointDTO>> getPointsByProductAndStore(Long productId, Long storeId) {
        List<PriceEntry> priceEntries = priceEntryRepository.findByProductAndStoreOrderByEntryDateAsc(productId, storeId);

        if(priceEntries.isEmpty()) {
            String message = String.format("There are no price entries found for product id %d and store id %d", productId, storeId);
            throw new ResourceNotFound(message);
        }

        List<GraphPointDTO> points = new ArrayList<>();
        String storeName = priceEntries.get(0).getStore().getName();

        for (PriceEntry priceEntry : priceEntries) {
            points.add(new GraphPointDTO(priceEntry.getProduct().getName(), priceEntry.getPrice(), priceEntry.getEntryDate()));
        }

        Map<String, List<GraphPointDTO>> result = new HashMap<>();
        result.put(storeName, points);

        return result;
    }

    public Map<String, List<GraphPointDTO>> getPointsByStoreAndBrand(Long productId, Long storeId, String brand) {
        List<PriceEntry> priceEntries = priceEntryRepository.findByProductAndBrandAndStoreIdOrderByProductNameAscEntryDateAsc(productId, brand, storeId);

        if(priceEntries.isEmpty()) {
            String message = String.format("There are no price entries found for product id %d of brand %s in store id %d", productId, brand, storeId);
            throw new ResourceNotFound(message);
        }

        List<GraphPointDTO> points = new ArrayList<>();
        String storeName = priceEntries.get(0).getStore().getName();

        for (PriceEntry priceEntry : priceEntries) {
            points.add(new GraphPointDTO(priceEntry.getProduct().getName(), priceEntry.getPrice(), priceEntry.getEntryDate()));
        }

        Map<String, List<GraphPointDTO>> result = new HashMap<>();
        result.put(storeName, points);

        return result;
    }

    public Map<String, List<GraphPointDTO>> getPointsByStoreAndCategory(Long productId, Long storeId, String category) {
        List<PriceEntry> priceEntries = priceEntryRepository.findByProductAndCategoryAndStoreIdOrderByProductNameAscEntryDateAsc(productId, category, storeId);

        if(priceEntries.isEmpty()) {
            String message = String.format("There are no price entries found for product id %d in category %s in store id %d", productId, category, storeId);
            throw new ResourceNotFound(message);
        }

        List<GraphPointDTO> points = new ArrayList<>();
        String storeName = priceEntries.get(0).getStore().getName();

        for (PriceEntry priceEntry : priceEntries) {
            points.add(new GraphPointDTO(priceEntry.getProduct().getName(), priceEntry.getPrice(), priceEntry.getEntryDate()));
        }

        Map<String, List<GraphPointDTO>> result = new HashMap<>();
        result.put(storeName, points);

        return result;
    }

}
