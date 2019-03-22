package com.csvConvert.company.test;

import com.csvConvert.company.CardProducerMC;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import net.sf.jsefa.csv.annotation.CsvSubRecord;
import net.sf.jsefa.csv.annotation.CsvSubRecordList;
import net.sf.jsefa.rbf.annotation.Record;

import java.util.List;

@CsvDataType(defaultPrefix = "T0")
public class TestClass {
    @CsvField( pos = 0)
    private TestClass1 testClass1;
    @CsvSubRecordList(pos = 1, records = @Record(prefix = ""))
    private List<TestClass2> testClass2;

    // getter setter methods


    public TestClass() {
    }



    public TestClass(TestClass1 testClass1, List<TestClass2> testClass2) {
        this.testClass1 = testClass1;
        this.testClass2 = testClass2;
    }

    public TestClass1 getTestClass1() {
        return testClass1;
    }

    public void setTestClass1(TestClass1 testClass1) {
        this.testClass1 = testClass1;
    }

    public List<TestClass2> getTestClass2() {
        return testClass2;
    }

    public void setTestClass2(List<TestClass2> testClass2) {
        this.testClass2 = testClass2;
    }
}