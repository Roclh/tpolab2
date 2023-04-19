package org.Roclh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roclh.math.MathUtils;
import org.roclh.math.funciton.Cos;
import org.roclh.math.funciton.Sin;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SinTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0000001");

    @Mock
    private Cos mockCos;
    @Spy
    private Cos spyCos;

    @Test
    void shouldCallCosFunction(){
        final Sin sin = new Sin(spyCos);
        try {
            sin.calculate(new BigDecimal(6), new BigDecimal("0.001"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            verify(spyCos, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void shouldCalculateWithMockCos() {
        final BigDecimal number = new BigDecimal(5);
        final BigDecimal cosResult = new BigDecimal("0.95892427466313846889315440615599399").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            when(mockCos.calculate(eq(MathUtils.getPi().divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN).subtract(number)), any(BigDecimal.class))).thenReturn(cosResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final Sin sin = new Sin(mockCos);
        try {
            assertEquals(cosResult, sin.calculate(number, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForZero() {
        final Sin sin = new Sin();
        try {
            assertEquals(ZERO, sin.calculate(ZERO, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForPiDividedByTwo() {
        final Sin sin = new Sin();
        final BigDecimal arg =
                MathUtils.getPi().divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
        try {
            assertEquals(
                    ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), sin.calculate(arg, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForOne() {
        final Sin sin = new Sin();
        final BigDecimal expected = new BigDecimal("0.8414709848078965066525023216302989996225630607983710656727517099").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            assertEquals(expected, sin.calculate(ONE, DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCalculateForPeriodic() {
        final Sin sin = new Sin();
        final BigDecimal expected = new BigDecimal("0.0088211661138858770062190045466560198161620903047781923669204229").setScale(DEFAULT_PRECISION.scale(), HALF_EVEN);
        try {
            assertEquals(expected, sin.calculate(new BigDecimal(-333), DEFAULT_PRECISION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
