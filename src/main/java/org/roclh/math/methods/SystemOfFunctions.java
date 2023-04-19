package org.roclh.math.methods;

import org.roclh.math.LimitedMathFunction;
import org.roclh.math.funciton.Cos;
import org.roclh.math.funciton.Csc;
import org.roclh.math.funciton.Ln;
import org.roclh.math.funciton.Log;
import org.roclh.math.funciton.Sin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class SystemOfFunctions implements LimitedMathFunction {
    private final Cos cos;
    private final Csc csc;
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log5;

    public SystemOfFunctions() {
        this.cos = new Cos();
        this.csc = new Csc();
        this.ln = new Ln();
        this.log2 = new Log(2);
        this.log3 = new Log(3);
        this.log5 = new Log(5);
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        BigDecimal cosValue = cos.calculate(x, precision);
        BigDecimal cscValue = csc.calculate(x, precision);
        BigDecimal lnValue = ln.calculate(x, precision);
        BigDecimal log2Value = log2.calculate(x, precision);
        BigDecimal log3Value = log3.calculate(x, precision);
        BigDecimal log5Value = log5.calculate(x, precision);
        if (x.compareTo(ZERO) <= 0) {
            return (((cosValue.divide(cscValue, DECIMAL128.getPrecision(), HALF_EVEN)
                    .subtract(cosValue))
                    .multiply(cosValue))
                    .subtract(cosValue).divide(cos.calculate(x, precision), DECIMAL128.getPrecision(), HALF_EVEN))
                    .setScale(precision.scale(), HALF_EVEN);
        } else {
            return (((((log5Value.subtract(log3Value))
                    .divide(lnValue.divide(log2Value, DECIMAL128.getPrecision(), HALF_EVEN), DECIMAL128.getPrecision(), HALF_EVEN))
                    .add(log5Value))
                    .subtract(lnValue))
                    .pow(3))
                    .setScale(precision.scale(), HALF_EVEN);
        }
    }
}
