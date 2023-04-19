package org.roclh.math;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class MathUtils {
    private static final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
    private static final BigDecimal pi = BigDecimalMath.pi(mc);
    private static final BigDecimal e = BigDecimalMath.e(mc);
    public static BigDecimal correctTrigonometricalValue(BigDecimal bigDecimal){
        return bigDecimal.remainder(pi.multiply(new BigDecimal(2)));
    }

    public static BigDecimal getE(){return e;}

    public static BigDecimal getPi(){
        return pi;
    }

    public static void defaultValidate(BigDecimal number, BigDecimal precision) throws NullPointerException{
        if(number == null || precision == null){
            throw new NullPointerException("Number or precision is null");
        }
        if (precision.compareTo(ZERO) <= 0 || precision.compareTo(ONE) >= 0) {
            throw new ArithmeticException("Precision must be less than one and more than zero");
        }
    }
}
