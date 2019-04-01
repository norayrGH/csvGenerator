package com.csvConvert.company.extractcardrenewalcounter.extension;



import com.csvConvert.company.extractcardrenewalcounter.enums.OutputFormatEnum;
import com.csvConvert.company.extractcardrenewalcounter.xml.IssuerMC;
import com.csvConvert.company.extractcardrenewalcounter.xml.RenewalCounter;
import net.sf.jsefa.xml.XmlIOFactory;
import net.sf.jsefa.xml.XmlSerializer;
import net.sf.jsefa.xml.config.XmlConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;


public class RenewalCounterXMLFileGeneration implements RenewalCounterFileGeneration {

    private XmlSerializer serializer;


    @Override
    public void generateFile(RenewalCounter renewalCounter, String fileName) {
        try {
            initialize(renewalCounter, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (serializer != null) {
                serializer.close(true);
            }
        }
    }


    @Override
    public String getName() {
        return OutputFormatEnum.XML.name();
    }

    public void initialize(RenewalCounter renewalCounter, String fileName) throws Exception {

        File outputFile = new File(fileName + OutputFormatEnum.XML.getExtension());
        BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFile.toPath(), Charset.forName("iso-8859-1"));
        XmlConfiguration xmlConfiguration = newXmlConfiguration();
        this.serializer = XmlIOFactory.createFactory(xmlConfiguration, RenewalCounter.class).createSerializer();
        this.serializer.open(bufferedWriter);
        this.serializer.write(renewalCounter);
        writeDetails(renewalCounter.getIssuers());
    }
    public void writeDetails(List<IssuerMC> renewalCounterIssuers) throws Exception {
        for (Iterator iter = renewalCounterIssuers.iterator(); iter.hasNext(); ) {
            IssuerMC element = (IssuerMC) iter.next();
            this.serializer.write(element);
        }
    }

    private XmlConfiguration newXmlConfiguration() {

        return new XmlConfiguration();
    }

}
