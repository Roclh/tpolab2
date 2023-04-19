package org.Roclh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roclh.math.funciton.Ln;
import org.roclh.math.funciton.Log;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0000000001");

    @Mock
    private Ln mockLn;
    @Spy
    private Ln spyLn;

    @Test
    void shouldCallLnFunction() {
        final Log log = new Log(spyLn, 5);
        try {
            log.calculate(new BigDecimal(6), DEFAULT_PRECISION);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            verify(spyLn, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateWithLnMock() {
        final BigDecimal number = new BigDecimal(126);

        try {
            when(mockLn.calculate(eq(new BigDecimal(126)), any(BigDecimal.class)))
                    .thenReturn(new BigDecimal("4.8362819069514779973130753387464077070075659795876153460494588267").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            when(mockLn.calculate(eq(new BigDecimal(5)), any(BigDecimal.class)))
                    .thenReturn(new BigDecimal("1.6094379124341003746007593332261876395256013542685177219126478914").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final Log log = new Log(mockLn, 5);
        final BigDecimal expected = new BigDecimal("3.0049509021675311977025556328534302092356866615369279973483256218").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            assertTrue(expected.compareTo(log.calculate(number, DEFAULT_PRECISION).add(DEFAULT_PRECISION)) <= 0 ||
                            expected.compareTo(log.calculate(number, DEFAULT_PRECISION).subtract(DEFAULT_PRECISION)) >= 0,
                    "Number " + log.calculate(number, DEFAULT_PRECISION) + " is not in range [" + expected.subtract(DEFAULT_PRECISION) + ";" + expected.add(DEFAULT_PRECISION) + "]");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldNotCalculateForZero() {
        final Log log = new Log(5);
        assertThrows(ArithmeticException.class, () -> log.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForOne() {
        final Log log = new Log(5);
        try {
            assertEquals(
                    ZERO.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), log.calculate(ONE, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForPositive() {
        final Log log = new Log(5);
        final BigDecimal number = new BigDecimal(16);
        final BigDecimal expected = new BigDecimal("1.7227062322935722026804262750558625282791677283190417972879041518").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            assertTrue(expected.compareTo(log.calculate(number, DEFAULT_PRECISION).add(DEFAULT_PRECISION)) <= 0 ||
                            expected.compareTo(log.calculate(number, DEFAULT_PRECISION).subtract(DEFAULT_PRECISION)) >= 0,
                    "Number " + log.calculate(number, DEFAULT_PRECISION) + " is not in range [" + expected.subtract(DEFAULT_PRECISION) + ";" + expected.add(DEFAULT_PRECISION) + "]");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
