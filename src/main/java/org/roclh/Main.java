package org.roclh;

import org.roclh.math.methods.SystemOfFunctions;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        SystemOfFunctions sf = new SystemOfFunctions();
        System.out.println(sf.calculate(new BigDecimal(2), new BigDecimal("0.0000000001")));
    }
}