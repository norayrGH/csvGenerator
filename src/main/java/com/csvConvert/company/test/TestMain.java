package com.csvConvert.company.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public static void main(String[] args) {
        List<A> list = Arrays.asList(new A("issuer1"), new A("issuer2"), new A("issuer3"));
        for (A a : list) {
            for (String data : a.getData()) {
                System.out.println(data);
            }
            System.out.println();
        }
    }
}
