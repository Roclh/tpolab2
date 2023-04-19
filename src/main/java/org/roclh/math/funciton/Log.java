package org.roclh.math.funciton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.roclh.math.LimitedMathFunction;
import org.roclh.math.MathUtils;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Log implements LimitedMathFunction {
    private final Logger logger = LogManager.getLogger(Log.class);
    private final Ln ln;
    private final int base;

    public Log(final int base) {
        super();
        this.ln = new Ln();
        this.base = base;
    }

    public Log(final Ln ln, final int base) {
        super();
        this.ln = ln;
        this.base = base;
    }

    @Override
    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision) {
        MathUtils.defaultValidate(x, precision);
        logger.info("Calculating Log{} function x:{}, precision:{}", base, x, precision);
        if (x.compareTo(ZERO) <= 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }

        final BigDecimal result =
                ln.calculate(x, precision)
                        .divide(
                                ln.calculate(new BigDecimal(base), precision),
                                DECIMAL128.getPrecision(),
                                HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
