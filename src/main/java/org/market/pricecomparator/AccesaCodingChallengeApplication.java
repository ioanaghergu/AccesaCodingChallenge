package org.market.pricecomparator;

import org.market.pricecomparator.utility.CSVParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;


@SpringBootApplication
public class AccesaCodingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccesaCodingChallengeApplication.class, args);

        CSVParser csvParser = new CSVParser();
        Resource resource = new ClassPathResource("data/lidl_2025-05-01.csv");
        try {
            InputStream inputStream = resource.getInputStream();
            csvParser.parseCSV(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
