package com.csvConvert.company.test;


import com.csvConvert.company.DateConvert;
import com.csvConvert.company.extractcardrenewalcounter.enums.OutputFormatEnum;

import com.csvConvert.company.extractcardrenewalcounter.xml.*;
import net.sf.jsefa.xml.XmlIOFactory;
import net.sf.jsefa.xml.XmlSerializer;
import net.sf.jsefa.xml.config.XmlConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class TestMain {

    public interface I<T> {
        String getName();
        List<String> getData();
    }

    public static class A implements I<B> {
        private String name;
        private List<B> data = Arrays.asList(new B("cardProducer1"), new B("cardProducer2"), new B("cardProducer3"));

        public A(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public List<String> getData() {
            List<String> retVal = new ArrayList<>();
            for (B datum : data) {
                for (String s : datum.getData()) {
                    retVal.add(name + ";" + s);
                }
            }
            return retVal;
        }
    }

    public static class B implements I<C> {
        private String name;
        private List<C> data = Arrays.asList(new C("cardTemplate1"), new C("cardTemplate2"), new C("cardTemplate3"));

        public B(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public List<String> getData() {
            List<String> retVal = new ArrayList<>();
            for (C datum : data) {
                for (String s : datum.getData()) {
                    retVal.add(name + ";" + s);
                }
            }
            return retVal;
        }
    }

    public static class C implements I<D> {
        private String name;
        private List<D> data = Arrays.asList(new D("cardStyle1"), new D("cardStyle2"), new D("cardStyle3"));

        public C(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public List<String> getData() {
            List<String> retVal = new ArrayList<>();
            for (D datum : data) {
                for (String s : datum.getData()) {
                    retVal.add(name + ";" + s);
                }
            }
            return retVal;
        }
    }

    public static class D implements I {
        private String name;
        private List<String> data = Arrays.asList("artwork1", "artwork2", "artwork3");

        public D(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public List<String> getData() {
            List<String> retVal = new ArrayList<>();
            for (String datum : data) {
                retVal.add(name + ";" + datum);
            }
            return retVal;
        }
    }
    static XmlSerializer serializer;
    public static void main(String[] args) {
        Map<String, Long> stringLongMap = new HashMap<String, Long>() {{

            put("value1", 1L);
            put("value2", 2L);
            put("value3", 3L);
            put("value4", 4L);
        }};

        final List<PeriodicCounter> periodicCounterList = new ArrayList<PeriodicCounter>() {{

            add(new PeriodicCounter("period", 1L));
            add(new PeriodicCounter("period2", 2L));
            add(new PeriodicCounter("period3", 3L));
            add(new PeriodicCounter("period4", 4L));

        }};

        final PeriodicCounters periodicCounter1 = new PeriodicCounters(periodicCounterList, stringLongMap);
        final PeriodicCounters periodicCounter2 = new PeriodicCounters(periodicCounterList, stringLongMap);
        final PeriodicCounters periodicCounter3 = new PeriodicCounters(periodicCounterList, stringLongMap);


        final List<ArtworkMC> artworkMCS = new ArrayList<ArtworkMC>() {{
            add(new ArtworkMC("artworkName1").getPeriodicCounters().setPeriodicCounterList(periodicCounterList));
            add(new ArtworkMC("artworkName2").getPeriodicCounters().setPeriodicCounterList(periodicCounterList));
            add(new ArtworkMC("artworkName3").getPeriodicCounters().setPeriodicCounterList(periodicCounterList));

        }};

        final List<CardStyleMC> cardStyleMCS = new ArrayList<CardStyleMC>() {{

            add(new CardStyleMC(new CardStyle(), artworkMCS));
            add(new CardStyleMC(new CardStyle() ,artworkMCS));
            add(new CardStyleMC(new CardStyle(), artworkMCS));

        }};
        final List<CardTemplateMC> cardTemplateMCS = new ArrayList<CardTemplateMC>() {{

            add(new CardTemplateMC("templateReference",
                    "technologyAndApplicationName", cardStyleMCS));
            add(new CardTemplateMC("templateReference",
                    "technologyAndApplicationName",cardStyleMCS));
            add(new CardTemplateMC("templateReference",
                    "technologyAndApplicationName",cardStyleMCS));


        }};
        final List<CardProducerMC> cardProducerMCS = new ArrayList<CardProducerMC>() {{

            add(new CardProducerMC("cardProducerCode", cardTemplateMCS));
            add(new CardProducerMC("cardProducerCode", cardTemplateMCS));
            add(new CardProducerMC("cardProducerCode", cardTemplateMCS));
        }};


        List<IssuerMC> issuerMCS = new ArrayList<IssuerMC>() {{

            add(new IssuerMC(1L, "issuerName1",3L, cardProducerMCS));
            add(new IssuerMC(2L, "issuerName2",3L, cardProducerMCS));
            add(new IssuerMC(3L, "issuerName3",3L, cardProducerMCS));

        }};
        List<String> periods = new ArrayList<String>() {{

            add("period1");
            add("period2");
            add("period3");

        }};

        RenewalCounter renewalCounter = new RenewalCounter("extractReference",
                new Date(), new Date(),
                "cardProducer", "issuer", "All",12,"periodicity","outputFormat"
                , issuerMCS, periods);
        String date = "2019-12-02";

        System.out.println(DateConvert.parseYYYYMMDDHHMMSSToDate(date));


        File outputFile = new File("../test" + OutputFormatEnum.XML.getExtension());
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = Files.newBufferedWriter(outputFile.toPath(), Charset.forName("iso-8859-1"));
            XmlConfiguration xmlConfiguration = new XmlConfiguration();
            serializer = XmlIOFactory.createFactory(xmlConfiguration, RenewalCounter.class).createSerializer();
            serializer.open(bufferedWriter);
            serializer.write(renewalCounter);
            writeDetails(renewalCounter.getIssuers());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }






        /*List<A> list = Arrays.asList(new A("issuer1"), new A("issuer2"), new A("issuer3"));
        for (A a : list) {
            for (String data : a.getData()) {
                System.out.println(data);
            }
            System.out.println();
        }*/
    }
    public static void writeDetails(List<IssuerMC> renewalCounterIssuers) throws Exception {
        for (Iterator iter = renewalCounterIssuers.iterator(); iter.hasNext(); ) {
            IssuerMC element = (IssuerMC) iter.next();
            serializer.write(element);
        }
    }
}
