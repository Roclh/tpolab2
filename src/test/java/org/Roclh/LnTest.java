package org.Roclh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roclh.math.funciton.Ln;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LnTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0000000001");

    @Test
    void shouldNotCalculateForZero() {
        final Ln ln = new Ln();
        assertThrows(ArithmeticException.class, () -> ln.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForOne() {
        final Ln ln = new Ln();
        try {
            assertEquals(ZERO, ln.calculate(ONE, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForPositive() {
        final Ln ln = new Ln();
        final BigDecimal expected = new BigDecimal("1.0986122886681096913952452369225257046474905578227494517346943336").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            assertEquals(expected, ln.calculate(new BigDecimal(3), DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
