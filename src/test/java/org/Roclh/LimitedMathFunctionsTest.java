package org.Roclh;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.roclh.math.LimitedMathFunction;
import org.roclh.math.funciton.Cos;
import org.roclh.math.funciton.Csc;
import org.roclh.math.funciton.Ln;
import org.roclh.math.funciton.Log;
import org.roclh.math.funciton.Sin;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LimitedMathFunctionsTest {

    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.000001");

    @ParameterizedTest
    @MethodSource("functions")
    void shouldNotAcceptNullArguments(final LimitedMathFunction function){
        assertThrows(NullPointerException.class, ()-> function.calculate(null, DEFAULT_PRECISION));
    }

    @ParameterizedTest
    @MethodSource("functions")
    void shouldNotAcceptNullPrecision(final LimitedMathFunction function){
        assertThrows(NullPointerException.class, () -> function.calculate(ONE, null));
    }

    private static Stream<Arguments> functions(){
        return Stream.of(
                Arguments.of(new Sin()),
                Arguments.of(new Cos()),
                Arguments.of(new Csc()),
                Arguments.of(new Ln()),
                Arguments.of(new Log(3)),
                Arguments.of(new Log(5)),
                Arguments.of(new Log(10))
        );
    }
}
