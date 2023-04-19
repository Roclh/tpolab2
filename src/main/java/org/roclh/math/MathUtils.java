package org.roclh.math;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class MathUtils {
    private static final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
    private static final BigDecimal pi = BigDecimalMath.pi(mc);
    public static BigDecimal correctTrigonometricalValue(BigDecimal bigDecimal){
        return bigDecimal.remainder(pi.multiply(new BigDecimal(2)));
    }

    public static BigDecimal getPi(){
        return pi;
    }
}
