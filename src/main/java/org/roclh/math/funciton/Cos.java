package org.roclh.math.funciton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.roclh.math.LimitedMathFunction;
import org.roclh.math.MathUtils;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class Cos implements LimitedMathFunction {
    Logger logger = LogManager.getLogger(Cos.class);

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        MathUtils.defaultValidate(x, precision);
        logger.info("Calculating Cos function x:{}, precision:{}", x, precision);
        BigDecimal X = MathUtils.correctTrigonometricalValue(x);
        BigDecimal PI2 = MathUtils.getPi().multiply(new BigDecimal(2));
        int i = 0;
        BigDecimal sum = new BigDecimal(0), prev;

        if (X.compareTo(PI2) >= 0) {
            while (X.compareTo(PI2) > 0) {
                X = X.subtract(PI2);
            }
        } else if (X.compareTo(PI2) < 0) {
            while (X.compareTo(PI2) < 0) {
                X = X.add(PI2);
            }
        }

        do {
            prev = sum;
            sum = sum.add(minusOnePow(i).multiply(prod(X, 2 * i)));
            i++;
        } while (new BigDecimal("0.1").pow(precision.scale()).compareTo(prev.subtract(sum).abs()) < 0);

        return sum.setScale(precision.scale(), HALF_EVEN);
    }

    private static BigDecimal minusOnePow(int n) {
        return BigDecimal.valueOf(1 - (n % 2) * 2);
    }

    private static BigDecimal prod(BigDecimal x, int n) {
        BigDecimal accum = new BigDecimal(1);

        for (int i = 1; i <= n; i++) {
            accum = accum.multiply(new BigDecimal(x.doubleValue() / i));
        }

        return accum;
    }
}
