package org.roclh;

import org.roclh.math.LimitedMathFunction;
import org.roclh.math.funciton.Csc;
import org.roclh.math.methods.SystemOfFunctions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvWriter {
    public static void write(
            final String filename,
            final LimitedMathFunction function,
            final BigDecimal from,
            final BigDecimal to,
            final BigDecimal step,
            final BigDecimal precision
            ) throws IOException{
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();
        final PrintWriter printWriter = new PrintWriter(file);
        for (BigDecimal current = from; current.compareTo(to) <= 0; current= current.add(step)){
            if(function.getClass().equals(Csc.class) && current.compareTo(BigDecimal.ZERO) == 0) continue;
            if (function.getClass().equals(SystemOfFunctions.class) && (current.compareTo(BigDecimal.ZERO) == 0 || current.compareTo(BigDecimal.ONE) == 0)) continue;
            printWriter.println(current + "," + function.calculate(current, precision));
        }
        printWriter.close();
    }
}
