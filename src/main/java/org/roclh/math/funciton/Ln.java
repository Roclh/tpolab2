package org.roclh.math.funciton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.roclh.math.LimitedMathFunction;
import org.roclh.math.MathUtils;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

public class Ln implements LimitedMathFunction {
    private static final long maxIterations = 1_000_000;
    private final Logger logger = LogManager.getLogger(Ln.class);

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        MathUtils.defaultValidate(x, precision);
        logger.info("Calculating Ln function x:{}, precision:{}", x, precision);
        if (precision.compareTo(ZERO) <= 0 || precision.compareTo(ONE) >= 0) {
            throw new ArithmeticException("Precision must be less than one and more than zero");
        }
        if (x.compareTo(ZERO) <= 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }
        if (x.compareTo(ONE) == 0) {
            return ZERO;
        }

        BigDecimal curValue = ZERO, prevValue;
        int i = 1;

        BigDecimal powed = new BigDecimal("0.1").pow(precision.scale()+6);
        if (x.subtract(ONE).abs().compareTo(ONE) <= 0) {
            do {
                prevValue = curValue;
                curValue = curValue.add(
                        (
                                (BigDecimal.valueOf(-1).pow(i - 1))
                                        .multiply(x.subtract(ONE).pow(i))
                        )
                                .divide(BigDecimal.valueOf(i), powed.scale(), HALF_UP)
                );
                i++;
            } while (powed.compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < maxIterations);
            return curValue.add(prevValue).divide(BigDecimal.valueOf(2), HALF_EVEN);
        } else {
            do {
                prevValue = curValue;
                curValue = curValue.add(
                        (
                                BigDecimal.valueOf(-1).pow(i - 1)
                                        .divide(x.subtract(ONE).pow(i), powed.scale(), HALF_UP)
                        )
                                .divide(BigDecimal.valueOf(i), powed.scale(), HALF_UP)
                );
                i++;
            } while (powed.compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < maxIterations);

            curValue = curValue.add(calculate(x.subtract(ONE), precision));
        }
        return curValue.setScale(precision.scale(), HALF_EVEN);
    }
}
