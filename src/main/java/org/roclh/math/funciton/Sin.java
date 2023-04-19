package org.roclh.math.funciton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.roclh.math.LimitedMathFunction;
import org.roclh.math.MathUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Sin implements LimitedMathFunction {
    private final Logger logger = LogManager.getLogger(Sin.class);
    private final Cos cos;

    public Sin(){
        this.cos = new Cos();
    }

    public Sin(Cos cos){
        this.cos = cos;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        MathUtils.defaultValidate(x, precision);
        logger.info("Calculating Sin function x:{}, precision:{}", x, precision);
        BigDecimal X = MathUtils.correctTrigonometricalValue(x);
        if(X.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }

        final BigDecimal result =
                cos.calculate(
                        MathUtils.getPi()
                                .divide(new BigDecimal(2), MathContext.DECIMAL128.getPrecision(), RoundingMode.HALF_EVEN)
                                .subtract(X),
                        precision
                );
        return result.setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
