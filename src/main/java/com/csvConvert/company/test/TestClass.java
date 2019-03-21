package com.csvConvert.company.test;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

@CsvDataType(defaultPrefix = "TC")
public class TestClass {
    @CsvField( pos = 1)
    private TestClass1 testClass1;
    @CsvField ( pos = 2)
    private TestClass2 testClass2;

    // getter setter methods


    public TestClass() {
    }

    public TestClass(TestClass1 testClass1, TestClass2 testClass2) {
        this.testClass1 = testClass1;
        this.testClass2 = testClass2;
    }

    public TestClass1 getTestClass1() {
        return testClass1;
    }

    public void setTestClass1(TestClass1 testClass1) {
        this.testClass1 = testClass1;
    }

    public TestClass2 getTestClass2() {
        return testClass2;
    }

    public void setTestClass2(TestClass2 testClass2) {
        this.testClass2 = testClass2;
    }
}