package org.Roclh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roclh.math.MathUtils;
import org.roclh.math.methods.SystemOfFunctions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SystemOfFunctionsTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.000000001");

    @Test
    void shouldNotAcceptNullArgument() {
        final SystemOfFunctions system = new SystemOfFunctions();
        assertThrows(NullPointerException.class, () -> system.calculate(null, DEFAULT_PRECISION));
    }

    @Test
    void shouldNotAcceptNullPrecision() {
        final SystemOfFunctions system = new SystemOfFunctions();
        assertThrows(NullPointerException.class, () -> system.calculate(new BigDecimal(-2), null));
    }

    @ParameterizedTest
    @MethodSource("illegalPrecisions")
    void shouldNotAcceptIncorrectPrecisions(final BigDecimal precision) {
        final SystemOfFunctions system = new SystemOfFunctions();
        assertThrows(ArithmeticException.class, () -> system.calculate(new BigDecimal(-2), precision));
    }

    @Test
    void shouldNotAcceptZeroArgument() {
        final SystemOfFunctions system = new SystemOfFunctions();
        assertThrows(ArithmeticException.class, ()->system.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldNotCalculateForPiDividedByTwo(){
        final SystemOfFunctions system = new SystemOfFunctions();
        assertThrows(ArithmeticException.class, ()-> system.calculate(MathUtils.getPi().divide(new BigDecimal(2).negate(), DECIMAL128.getPrecision(), RoundingMode.HALF_EVEN), DEFAULT_PRECISION));
    }

    @Test
    void shouldAcceptOneArgument() {
        final SystemOfFunctions system = new SystemOfFunctions();
        assertThrows(ArithmeticException.class, ()-> system.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForPositiveValue() {
        final SystemOfFunctions system = new SystemOfFunctions();
        final BigDecimal expected = new BigDecimal("-2.098395316481609750580018941660568773290233222884847555592902282").setScale(DEFAULT_PRECISION.scale(), RoundingMode.HALF_EVEN);
        assertEquals(expected, system.calculate(new BigDecimal(5), DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForNegativeValue() {
        final SystemOfFunctions system = new SystemOfFunctions();
        final BigDecimal expected = new BigDecimal("-1.011651630018541357764265340587868667492601085794103999143267060").setScale(DEFAULT_PRECISION.scale(), RoundingMode.HALF_EVEN);
        assertEquals(expected, system.calculate(new BigDecimal(-5), DEFAULT_PRECISION));
    }

    private static Stream<Arguments> illegalPrecisions() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1)),
                Arguments.of(BigDecimal.valueOf(0)),
                Arguments.of(BigDecimal.valueOf(1.01)),
                Arguments.of(BigDecimal.valueOf(-0.01)));
    }
}
