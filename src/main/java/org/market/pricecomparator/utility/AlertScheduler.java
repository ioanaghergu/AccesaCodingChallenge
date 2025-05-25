package org.market.pricecomparator.utility;


import org.market.pricecomparator.dto.PriceAlertResponseDTO;
import org.market.pricecomparator.service.PriceAlertService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import org.slf4j.Logger;

@Component
public class AlertScheduler {

    private static final Logger logger = LoggerFactory.getLogger(AlertScheduler.class);
    private final PriceAlertService priceAlertService;

    @Autowired
    public AlertScheduler(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    @Scheduled(cron = "*/30 * * * * ?")
    public void checkForAlerts() {
        try {
            List<PriceAlertResponseDTO> triggeredAlerts = priceAlertService.checkForTriggeredAlerts();
            if(triggeredAlerts.isEmpty()) {
                logger.info("No alert triggered");
            }
            else {
                logger.info("{} alerts triggered", triggeredAlerts.size());

                for(PriceAlertResponseDTO alert: triggeredAlerts) {
                    logger.info("Alert with id {} for user with id {} for product with id {} and name {} triggered", alert.getId(), alert.getUserId(), alert.getProductId(), alert.getProductName());
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        logger.info("All alerts processed");
    }
}
