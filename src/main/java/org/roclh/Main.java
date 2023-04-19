package org.roclh;

import org.roclh.math.funciton.Ln;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class Main {
    public static void main(String[] args) {
        Ln ln = new Ln();
        try {
            System.out.println(ln.calculate(new BigDecimal(3), new BigDecimal("0.0000001")) + "\n" + new BigDecimal("1.0986122886681096913952452369225257046474905578227494517346943336").setScale(new BigDecimal("0.0000001").scale(), HALF_EVEN));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}