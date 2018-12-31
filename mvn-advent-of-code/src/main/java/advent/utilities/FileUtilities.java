package advent.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface FileUtilities {
    static <T> List<T> readLines(String name, Function<String, T> parser) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtilities.class.getResourceAsStream(name)))) {
            return reader.lines().map(parser).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot load '" + name + "'");
        }
    }

    static <T> List<T> readLinesWithIndex(String name, BiFunction<Integer, String, T> parser) {
        List<String> lines = readLines(name, Parsers::TO_STRING);
        List<T> results = new ArrayList<>(lines.size());
        for (int i = 0; i != lines.size(); i++) {
            results.add(parser.apply(i, lines.get(i)));
        }
        return results;
    }
}
