package org.Roclh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roclh.math.MathUtils;
import org.roclh.math.funciton.Cos;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CosTest {

    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0000001");

    @Test
    void shouldCalculateForZero() {
        final Cos cos = new Cos();
        try {
            assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), cos.calculate(ZERO, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForPiDividedByTwo() {
        final Cos cos = new Cos();
        final BigDecimal piDividedByTwo = MathUtils.getPi().divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
        assertEquals(ZERO.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), cos.calculate(piDividedByTwo, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForOne() {
        final Cos cos = new Cos();
        final BigDecimal expected = new BigDecimal("0.5403023058681397174009366074429766037323104206179222276700972553").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        assertEquals(expected, cos.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForPeriodic() {
        final Cos cos = new Cos();
        final BigDecimal expected = new BigDecimal("0.9999610927573088488533204013522065403824782689030668549013123503").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        assertEquals(expected, cos.calculate(new BigDecimal(-333), DEFAULT_PRECISION));
    }
}
