package org.market.pricecomparator;

import org.market.pricecomparator.utility.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;


@SpringBootApplication
public class AccesaCodingChallengeApplication implements CommandLineRunner {

    @Autowired
    private CSVParser csvParser;

    public static void main(String[] args) {
        SpringApplication.run(AccesaCodingChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        try {
            Resource resource = new ClassPathResource("data");
            String directorypath = resource.getFile().getAbsolutePath();
            csvParser.processCSVDirectory(directorypath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}
