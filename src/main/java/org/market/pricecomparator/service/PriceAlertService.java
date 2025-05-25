package org.market.pricecomparator.service;

import org.market.pricecomparator.dto.PriceAlertRequestDTO;
import org.market.pricecomparator.dto.PriceAlertResponseDTO;
import org.market.pricecomparator.exceptions.ResourceNotFound;
import org.market.pricecomparator.mapper.PriceAlertMapper;
import org.market.pricecomparator.model.entity.PriceAlert;
import org.market.pricecomparator.model.entity.PriceEntry;
import org.market.pricecomparator.model.entity.Product;
import org.market.pricecomparator.model.entity.User;
import org.market.pricecomparator.repository.PriceAlertRepository;
import org.market.pricecomparator.repository.PriceEntryRepository;
import org.market.pricecomparator.repository.ProductRepository;
import org.market.pricecomparator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PriceAlertService {

    private static final Logger logger = LoggerFactory.getLogger(PriceAlertService.class);
    private final PriceAlertRepository priceAlertRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PriceAlertMapper priceAlertMapper;
    private final PriceEntryRepository priceEntryRepository;


    @Autowired
    public PriceAlertService(PriceAlertRepository priceAlertRepository, UserRepository userRepository, ProductRepository productRepository, PriceAlertMapper priceAlertMapper, PriceEntryRepository priceEntryRepository) {
        this.priceAlertRepository = priceAlertRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.priceAlertMapper = priceAlertMapper;
        this.priceEntryRepository = priceEntryRepository;
    }

    @Transactional
    public PriceAlertResponseDTO createPriceAlert(PriceAlertRequestDTO priceAlertRequestDTO) {
        User user = userRepository.findById(priceAlertRequestDTO.getUserId()).orElseThrow(() -> new ResourceNotFound("User not found"));
        Product product = productRepository.findById(priceAlertRequestDTO.getProductId()).orElseThrow(() -> new ResourceNotFound("Product not found"));

        Optional<PriceAlert> existingAlert = priceAlertRepository.findByUserIdAndProductIdAndIsActiveTrue(user.getId(), product.getId());

        //update target price for existing alert
        if (existingAlert.isPresent()) {
            existingAlert.get().setTargetPrice(priceAlertRequestDTO.getTargetPrice());
            PriceAlert updatedAlert = existingAlert.get();
            priceAlertRepository.save(updatedAlert);
            return priceAlertMapper.toPriceAlertResponseDTO(updatedAlert);
        }

        PriceAlert priceAlert = new PriceAlert();
        priceAlert.setUser(user);
        priceAlert.setProduct(product);
        priceAlert.setTargetPrice(priceAlertRequestDTO.getTargetPrice());
        priceAlert.setIsActive(true);

        PriceAlert newAlert = priceAlertRepository.save(priceAlert);

        return priceAlertMapper.toPriceAlertResponseDTO(newAlert);
    }

    @Transactional
    public List<PriceAlertResponseDTO> getUserActiveAlerts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found"));

        List<PriceAlert> alerts = priceAlertRepository.findByUserIdAndIsActiveTrue(user.getId());

        return alerts.stream().map(priceAlertMapper::toPriceAlertResponseDTO).collect(Collectors.toList());

    }

    public List<PriceAlertResponseDTO> checkForTriggeredAlerts() {
        List<PriceAlert> activeAlerts = priceAlertRepository.findAllByIsActiveTrue();
        List<PriceAlertResponseDTO> triggeredAlerts = new ArrayList<>();

        if(activeAlerts.isEmpty())
        {
            logger.info("No active alerts");
            return triggeredAlerts;
        }

        for(PriceAlert alert: activeAlerts)
        {
            Product product = alert.getProduct();

            if(product == null)
            {
                logger.info("Product {} not found", product.getName());
                continue;
            }

            List<PriceEntry> latestPricesPerStore = priceEntryRepository.findLatestPriceEntryInEachStoreByProductId(product.getId());

            if(latestPricesPerStore.isEmpty())
            {
                logger.info("No price entries found for product {}",product.getName());
                continue;
            }

            Optional<PriceEntry> minPriceEntry = latestPricesPerStore.stream()
                    .min(Comparator.comparing(PriceEntry::getPrice));

            if(!minPriceEntry.isEmpty())
            {
                BigDecimal lowestPrice = minPriceEntry.get().getPrice();

                if(lowestPrice.compareTo(alert.getTargetPrice()) <= 0) {
                    logger.info("Triggered alert for user {} for product {}",alert.getUser().getId(), product.getName());
                    triggeredAlerts.add(priceAlertMapper.toPriceAlertResponseDTO(alert));

                    alert.setIsActive(false);
                    priceAlertRepository.save(alert);
                }
            }
            else
            {
                logger.info("No price entries found for product {}",product.getName());
            }
        }

        return triggeredAlerts;
    }


}
