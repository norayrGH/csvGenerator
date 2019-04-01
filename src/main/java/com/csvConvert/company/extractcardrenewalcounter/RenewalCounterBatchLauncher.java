//package com.csvConvert.company.extractcardrenewalcounter;
//
//
//import net.atos.xa.eff.cflow.engine.Engine;
//import net.atos.xa.eff.cflow.engine.EngineState;
//import net.atos.xa.eff.cflow.engine.EngineStatus;
//
//import javax.persistence.PersistenceException;
//import java.util.Date;
//import java.util.UUID;
//
//public class RenewalCounterBatchLauncher extends ExportBatchLauncher {
//
//    public EngineStatus syncLaunch(String periodicity, String nbPeriods, String cardProducer, String issuer,
//                                   String details, String outputFormat, String effectiveDate, String restartId) {
//
//        ExportBatch batch = ExportBatch.builder()
//                .withItemProcessor(RenewalCounterProcessorImpl.class)
//                .withTaskProcessor(new RenewalCounterTaskProcessorImpl())
//                .withFetchMode(FetchMode.FETCH_ALL)
//                .withCapacity((short) 1)
//                .withCommitInterval(1)
//                .withJobName(JobList.RENEWALCOUNTER.getJobName())
//                .withVariable(RenewalCounterConstants.PERIODICITY, PeriodicityEnum.valueOf(periodicity))
//                .withVariable(RenewalCounterConstants.NB_PERIODS, StringUtils.emptyStr(nbPeriods) ? 12 : Integer.parseInt(nbPeriods.trim()))
//                .withVariable(RenewalCounterConstants.DETAILS, StringUtils.emptyStr(details) ? DetailEnum.PER_TEMPLATES : DetailEnum.valueOf(details))
//                .withVariable(RenewalCounterConstants.ISSUER_ID, Long.parseLong(issuer.trim()))
//                .withVariable(RenewalCounterConstants.CARD_PRODUCER, cardProducer)
//                .withVariable(RenewalCounterConstants.OUTPUT_FORMAT, StringUtils.emptyStr(outputFormat) ? OutputFormatEnum.CSV : OutputFormatEnum.valueOf(outputFormat))
//                .withVariable(RenewalCounterConstants.EFFECTIVE_DATE, StringUtils.emptyStr(effectiveDate) ? new Date() : DateUtils.parseYYYYMMDDToDate(effectiveDate))
//                .withReportInOneLine(true)
//                .build();
//
//        EngineStatus engineStatus;
//        engineStatus = syncLaunch(batch, restartId);
//        return engineStatus;
//    }
//
//    @Override
//    public EngineState syncLaunch(Engine engine, long pollingTime) {
//        try {
//            String jobName = JobList.RENEWALCOUNTER.getJobName();
//            UUID idBatch = engine.getId();
//            MDC.put("CORRELATION_ID", "[" + jobName + ":" + idBatch + "]");
//            super.syncLaunch(engine, pollingTime);
//        } catch (PersistenceException var6) {
//            throw new EJBException();
//        }
//
//        return engine.getState();
//    }
//
//}
