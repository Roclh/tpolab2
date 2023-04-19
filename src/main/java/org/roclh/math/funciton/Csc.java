package org.roclh.math.funciton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.roclh.math.LimitedMathFunction;
import org.roclh.math.MathUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Csc implements LimitedMathFunction {
    private final Logger logger = LogManager.getLogger(Csc.class);
    private final Sin sin;

    public Csc(){
        this.sin = new Sin();
    }

    public Csc(Sin sin){
        this.sin = sin;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        MathUtils.defaultValidate(x, precision);
        logger.info("Calculating Csc function x:{}, precision:{}", x, precision);
        BigDecimal X = MathUtils.correctTrigonometricalValue(x);
        return new BigDecimal(1)
                .divide(sin.calculate(X, precision), MathContext.DECIMAL128.getPrecision(), RoundingMode.HALF_EVEN)
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
