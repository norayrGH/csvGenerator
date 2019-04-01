//package com.csvConvert.company.extractcardrenewalcounter;
//
//import net.atos.xa.eff.server.mbeans.EngineMBean;
//
//public class RenewalCounterBatchRemote extends EngineMBean implements RenewalCounterBatchRemoteMBean {
//
//    private RenewalCounterBatchLauncher exportBatchLauncher = new RenewalCounterBatchLauncher();
//
//    public RenewalCounterBatchRemote() throws javax.management.NotCompliantMBeanException {
//        super(new String[]{"periodicity", "nbPeriods", "cardProducer","issuer", "details", "outputFormat", "effectiveDate"});
//    }
//
//    @Override
//    public String syncLaunch(String periodicity, String nbPeriods, String cardProducer, String issuer, String details, String outputFormat, String effectiveDate, String restartId) {
//        return exportBatchLauncher.syncLaunch(periodicity, nbPeriods, cardProducer, issuer, details, outputFormat, effectiveDate, restartId).toString();
//    }
//}
