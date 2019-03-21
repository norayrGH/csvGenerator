package com.csvConvert.company;

import com.csvConvert.company.test.TestClass;
import com.csvConvert.company.test.TestClass1;
import com.csvConvert.company.test.TestClass2;
import net.sf.jsefa.Serializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        FileWriter fw = null;


        Map<String, Long> stringLongMap = new HashMap<String, Long>() {{

            put("value1", 1L);
            put("value2", 2L);
            put("value3", 3L);
            put("value4", 4L);
        }};

        List<PeriodicCounter> periodicCounterList = new ArrayList<PeriodicCounter>() {{

            add(new PeriodicCounter("period", 1L));
            add(new PeriodicCounter("period2", 2L));
            add(new PeriodicCounter("period3", 3L));
            add(new PeriodicCounter("period4", 4L));

        }};

        final PeriodicCounters periodicCounter1 = new PeriodicCounters(periodicCounterList, stringLongMap);
        final PeriodicCounters periodicCounter2 = new PeriodicCounters(periodicCounterList, stringLongMap);
        final PeriodicCounters periodicCounter3 = new PeriodicCounters(periodicCounterList, stringLongMap);

        final List<ArtworkMC> artworkMCS = new ArrayList<ArtworkMC>() {{

            add(new ArtworkMC("artworkName1", periodicCounter1));
            add(new ArtworkMC("artworkName2", periodicCounter2));
            add(new ArtworkMC("artworkName3", periodicCounter3));

        }};

        final List<CardStyleMC> cardStyleMCS = new ArrayList<CardStyleMC>() {{

            add(new CardStyleMC("cardStyleName1", periodicCounter1, artworkMCS));
            add(new CardStyleMC("cardStyleName2", periodicCounter2, artworkMCS));
            add(new CardStyleMC("cardStyleName3", periodicCounter3, artworkMCS));

        }};
        final List<CardTemplateMC> cardTemplateMCS = new ArrayList<CardTemplateMC>() {{

            add(new CardTemplateMC("templateReference",
                    "technologyAndApplicationName", periodicCounter1, cardStyleMCS));
            add(new CardTemplateMC("templateReference",
                    "technologyAndApplicationName", periodicCounter2, cardStyleMCS));
            add(new CardTemplateMC("templateReference",
                    "technologyAndApplicationName", periodicCounter3, cardStyleMCS));


        }};
        final List<CardProducerMC> cardProducerMCS = new ArrayList<CardProducerMC>() {{

            add(new CardProducerMC("cardProducerCode", periodicCounter1, cardTemplateMCS));
            add(new CardProducerMC("cardProducerCode", periodicCounter2, cardTemplateMCS));
            add(new CardProducerMC("cardProducerCode", periodicCounter3, cardTemplateMCS));
        }};


        List<IssuerMC> issuerMCS = new ArrayList<IssuerMC>() {{

            add(new IssuerMC(1L, "issuerName1", cardProducerMCS));
            add(new IssuerMC(2L, "issuerName2", cardProducerMCS));
            add(new IssuerMC(3L, "issuerName3", cardProducerMCS));

        }};
        List<String> periods = new ArrayList<String>() {{

            add("period1");
            add("period2");
            add("period3");

        }};

        /*RenewalCounter renewalCounter = new RenewalCounter("extractReference",
                "cardProducer", "issuer",
                "details", 15, "periodicity",
                "outputFormat", issuerMCS, periods);
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        Serializer serializer = CsvIOFactory.createFactory(csvConfiguration,RenewalCounter.class).createSerializer();
        File file = new File("../csvFile.txt");
        try {

            BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath(), Charset.forName("iso-8859-1"));
            serializer.open(bufferedWriter);
            serializer.write(renewalCounter);

            serializer.close(true);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        File file = new File("../csvFile.txt");
        Serializer serializer = CsvIOFactory.createFactory(TestClass.class,  TestClass2.class).createSerializer();
        TestClass1 testClass1 = new TestClass1(1,"HTC");
        TestClass2 testClass2 = new TestClass2(512,"HTC500");
        TestClass testClass = new TestClass();

    testClass.setTestClass1(testClass1);
    testClass.setTestClass2(testClass2);

        try {

            serializer.open(new FileWriter(file));
            serializer.write(testClass);
            serializer.close(true);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
