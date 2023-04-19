package org.roclh;

import org.roclh.math.funciton.Cos;
import org.roclh.math.funciton.Csc;
import org.roclh.math.funciton.Ln;
import org.roclh.math.funciton.Log;
import org.roclh.math.funciton.Sin;
import org.roclh.math.methods.SystemOfFunctions;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class Main {
    public static void main(String[] args) throws IOException {
        final Cos cos = new Cos();
        CsvWriter.write(
                "csv/cos.csv",
                cos,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000001")
        );
        final Sin sin = new Sin();
        CsvWriter.write(
                "csv/sin.csv",
                sin,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Csc csc = new Csc();
        CsvWriter.write(
                "csv/csc.csv",
                csc,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Ln ln = new Ln();
        CsvWriter.write(
                "csv/ln.csv",
                ln,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("2"),
                new BigDecimal("0.0000000001"));

        final Log log3 = new Log(3);
        CsvWriter.write(
                "csv/log3.csv",
                log3,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("2"),
                new BigDecimal("0.00000000001"));

        final Log log5 = new Log(5);
        CsvWriter.write(
                "csv/log5.csv",
                log5,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("2"),
                new BigDecimal("0.00000000001"));

        final Log log10 = new Log(10);
        CsvWriter.write(
                "csv/log10.csv",
                log10,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("2"),
                new BigDecimal("0.00000000001"));

        final SystemOfFunctions func = new SystemOfFunctions();
        CsvWriter.write(
                "csv/func.csv",
                func,
                new BigDecimal(-2),
                new BigDecimal(2),
                new BigDecimal("1"),
                new BigDecimal("0.00000000001"));
    }

}