package org.Roclh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roclh.math.MathUtils;
import org.roclh.math.funciton.Csc;
import org.roclh.math.funciton.Sin;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CscTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0000001");
    @Mock
    private Sin mockSin;
    @Spy
    private Sin spySin;

    @Test
    void shouldCallSinFunction() {
        final Csc csc = new Csc(spySin);
        try {
            csc.calculate(ONE, DEFAULT_PRECISION);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            verify(spySin, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateWithMockSin() {
        final BigDecimal number = new BigDecimal(5);
        try {
            when(mockSin.calculate(eq(number), any(BigDecimal.class)))
                    .thenReturn(new BigDecimal("0.958924274663138468893154406155993973352461543964601778131672454").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final Csc csc = new Csc(mockSin);
        final BigDecimal expectedCscResult = new BigDecimal("1.042835212771405819783119855907759843972351752364546174404470858").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            assertEquals(expectedCscResult, csc.calculate(number, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldNotCalculateForZero(){
        final Csc csc = new Csc();
        assertThrows(ArithmeticException.class, ()-> csc.calculate(new BigDecimal(0), DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForPiDividedByTwo(){
        final Csc csc = new Csc();
        try {
            assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), csc.calculate(MathUtils.getPi().divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN), DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForOne() {
        final Csc csc = new Csc();
        final BigDecimal expected = new BigDecimal("1.1883951057781212162615994523745510035278298340979626252652536663").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            assertEquals(expected, csc.calculate(ONE, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForPeriodic() {
        final Csc csc = new Csc();
        final BigDecimal expected = new BigDecimal("-1.000088147662058332745252188707453152498790853882341557072172710").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            assertEquals(expected, csc.calculate(new BigDecimal(-33), DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
