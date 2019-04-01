package com.csvConvert.company.extractcardrenewalcounter.extension;


import com.csvConvert.company.extractcardrenewalcounter.enums.DetailEnum;
import com.csvConvert.company.extractcardrenewalcounter.enums.OutputFormatEnum;
import com.csvConvert.company.extractcardrenewalcounter.xml.IssuerMC;
import com.csvConvert.company.extractcardrenewalcounter.xml.RenewalCounter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

public class RenewalCounterCSVFileGeneration implements RenewalCounterFileGeneration {

    @Override
    public void generateFile(RenewalCounter renewalCounter, String fileName) {
        if (!renewalCounter.getDetails().equals(DetailEnum.PER_TEMPLATES_STYLES_ARTWORKS)) {
            throw new IllegalArgumentException("the Details of renewalCounter not Per Card Templates - Card Styles - Artworks");
        }


        renewalCounter.getPeriods();
        File file = new File(fileName);


        List<IssuerMC> issuerMCS = renewalCounter.getIssuers();

        Iterator iterator = issuerMCS.iterator();

        while (iterator.hasNext())
        {

        }
        System.out.println();
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath(), Charset.forName("iso-8859-1"));

        } catch (IOException e) {
            e.printStackTrace();
        }

      /*  Serializer serializer = CsvIOFactory.createFactory(RenewalCounter.class).createSerializer();
        StringWriter writer = new StringWriter();
        serializer.open(writer);
        serializer.write(renewalCounter.getIssuers());


            serializer.open(writer);


            serializer.close(true);*/
    }


    @Override
    public String getName() {
        return OutputFormatEnum.CSV.name();
    }
}