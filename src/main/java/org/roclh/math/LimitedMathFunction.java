package org.roclh.math;

import java.math.BigDecimal;

public interface LimitedMathFunction {
    BigDecimal calculate(final BigDecimal x, final BigDecimal precision);
}
