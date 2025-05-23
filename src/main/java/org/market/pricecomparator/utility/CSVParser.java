package org.market.pricecomparator.utility;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@NoArgsConstructor
public class CSVParser {

    public static void parseCSV(InputStream inputStream) {

        com.opencsv.CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();

        try(CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream))
                .withSkipLines(1)
                .withCSVParser(parser)
                .build())
        {
            String[] record;

            while((record = reader.readNext()) != null)
            {
                for(String value : record)
                    System.out.println(value + " ");
                System.out.println();
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);

        }
    }
}
