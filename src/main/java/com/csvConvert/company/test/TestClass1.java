package com.csvConvert.company.test;



import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

import javax.persistence.Embeddable;
import java.io.Serializable;
@CsvDataType(defaultPrefix = "T1")
public class TestClass1 implements Serializable {

private static final long serialVersionUID = 371968159365574089L;

@CsvField(pos = 0)
private int id;

@CsvField(pos = 1)
private String Name;


    public TestClass1() {
    }

    public TestClass1(int id, String name) {
        this.id = id;
        Name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}