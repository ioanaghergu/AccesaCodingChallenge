package org.market.pricecomparator.controller;

import jakarta.validation.Valid;
import org.market.pricecomparator.dto.PriceAlertRequestDTO;
import org.market.pricecomparator.dto.PriceAlertResponseDTO;
import org.market.pricecomparator.service.PriceAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class PriceAlertController {

    private final PriceAlertService priceAlertService;

    @Autowired
    public PriceAlertController(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    @PostMapping("/createAlert/{userId}")
    public ResponseEntity<PriceAlertResponseDTO> createAlert(@PathVariable Long userId, @Valid @RequestBody PriceAlertRequestDTO priceAlertRequestDTO) {
        if(!priceAlertRequestDTO.getUserId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PriceAlertResponseDTO alert = priceAlertService.createPriceAlert(priceAlertRequestDTO);
        return new ResponseEntity<>(alert, HttpStatus.CREATED);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PriceAlertResponseDTO>> getAllAlertsForUser(@PathVariable Long userId) {
        List<PriceAlertResponseDTO> alerts = priceAlertService.getUserActiveAlerts(userId);
        return new ResponseEntity<>(alerts, HttpStatus.OK);
    }

}
