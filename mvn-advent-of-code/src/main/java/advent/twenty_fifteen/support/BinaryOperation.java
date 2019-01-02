package advent.twenty_fifteen.support;

import java.util.Map;
import java.util.Objects;

public abstract class BinaryOperation {
    private static class InputOperation extends BinaryOperation {
        private final String output;
        private final String input;

        public InputOperation(String output, String input) {
            this.output = output;
            this.input = input;
        }

        @Override
        public boolean areInputsReady(Map<String, Integer> values) {
            return values.containsKey(input) && !values.containsKey(output);
        }

        @Override
        public void apply(Map<String, Integer> values) {
            values.put(output, values.get(input));
        }

        @Override
        public String toString() {
            return "" + input + " -> " + output;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InputOperation that = (InputOperation) o;
            return Objects.equals(output, that.output) &&
                    Objects.equals(input, that.input);
        }

        @Override
        public int hashCode() {
            return Objects.hash(output, input);
        }
    }

    private static class AndOperation extends BinaryOperation {
        private final String output;
        private final String inputA;
        private final String inputB;

        public AndOperation(String output, String inputA, String inputB) {
            this.output = output;
            this.inputA = inputA;
            this.inputB = inputB;
        }

        @Override
        public boolean areInputsReady(Map<String, Integer> values) {
            System.out.println("" + inputA + " AND " + inputB + " -> " + output + " " + inputA + " " + values.containsKey(inputA) + "   " + inputB + " " + values.containsKey(inputB) + " " + values.keySet());
            return values.containsKey(inputA) && values.containsKey(inputB) && !values.containsKey(output);
        }

        @Override
        public void apply(Map<String, Integer> values) {
            values.put(output, values.get(inputA).intValue() & values.get(inputB).intValue());
        }

        @Override
        public String toString() {
            return "" + inputA + " AND " + inputB + " -> " + output;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AndOperation that = (AndOperation) o;
            return Objects.equals(output, that.output) &&
                    Objects.equals(inputA, that.inputA) &&
                    Objects.equals(inputB, that.inputB);
        }

        @Override
        public int hashCode() {
            return Objects.hash(output, inputA, inputB);
        }
    }

    private static class OrOperation extends BinaryOperation {
        private final String output;
        private final String inputA;
        private final String inputB;

        public OrOperation(String output, String inputA, String inputB) {
            this.output = output;
            this.inputA = inputA;
            this.inputB = inputB;
        }

        @Override
        public boolean areInputsReady(Map<String, Integer> values) {
            return values.containsKey(inputA) && values.containsKey(inputB) && !values.containsKey(output);
        }

        @Override
        public void apply(Map<String, Integer> values) {
            values.put(output, values.get(inputA).intValue() | values.get(inputB).intValue());
        }

        @Override
        public String toString() {
            return "" + inputA + " OR " + inputB + " -> " + output;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrOperation that = (OrOperation) o;
            return Objects.equals(output, that.output) &&
                    Objects.equals(inputA, that.inputA) &&
                    Objects.equals(inputB, that.inputB);
        }

        @Override
        public int hashCode() {
            return Objects.hash(output, inputA, inputB);
        }
    }

    private static class LeftShiftOperation extends BinaryOperation {
        private final String output;
        private final String input;
        private final int count;

        public LeftShiftOperation(String output, String input, int count) {
            this.output = output;
            this.input = input;
            this.count = count;
        }

        @Override
        public boolean areInputsReady(Map<String, Integer> values) {
            return values.containsKey(input) && !values.containsKey(output);
        }

        @Override
        public void apply(Map<String, Integer> values) {
            values.put(output, values.get(input).intValue() << count);
        }

        @Override
        public String toString() {
            return "" + input + " LSHIFT " + count + " -> " + output;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LeftShiftOperation that = (LeftShiftOperation) o;
            return count == that.count &&
                    Objects.equals(output, that.output) &&
                    Objects.equals(input, that.input);
        }

        @Override
        public int hashCode() {
            return Objects.hash(output, input, count);
        }
    }

    private static class RightShiftOperation extends BinaryOperation {
        private final String output;
        private final String input;
        private final int count;

        public RightShiftOperation(String output, String input, int count) {
            this.output = output;
            this.input = input;
            this.count = count;
        }

        @Override
        public boolean areInputsReady(Map<String, Integer> values) {
            return values.containsKey(input) && !values.containsKey(output);
        }

        @Override
        public void apply(Map<String, Integer> values) {
            values.put(output, values.get(input).intValue() >> count);
        }

        @Override
        public String toString() {
            return "" + input + " RSHIFT " + count + " -> " + output;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RightShiftOperation that = (RightShiftOperation) o;
            return count == that.count &&
                    Objects.equals(output, that.output) &&
                    Objects.equals(input, that.input);
        }

        @Override
        public int hashCode() {
            return Objects.hash(output, input, count);
        }
    }

    private static class NotOperation extends BinaryOperation {
        private final String output;
        private final String input;

        public NotOperation(String output, String input) {
            this.output = output;
            this.input = input;
        }

        @Override
        public boolean areInputsReady(Map<String, Integer> values) {
            return values.containsKey(input) && !values.containsKey(output);
        }

        @Override
        public void apply(Map<String, Integer> values) {
            values.put(output, (-values.get(input).intValue() - 1) + 65536);
        }

        @Override
        public String toString() {
            return "NOT " + input + " -> " + output;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NotOperation that = (NotOperation) o;
            return Objects.equals(output, that.output) &&
                    Objects.equals(input, that.input);
        }

        @Override
        public int hashCode() {
            return Objects.hash(output, input);
        }
    }

    abstract public boolean areInputsReady(Map<String, Integer> values);

    abstract public void apply(Map<String, Integer> values);

    @Override
    abstract public String toString();

    /*
         123 -> x
         x AND y -> z
         p LSHIFT 2 -> q
         NOT e -> f
         */
    public static BinaryOperation PARSE(String line) {
        String[] bits = line.split(" ");
        if (bits.length == 3) {
            return new InputOperation(bits[2], bits[0]);
        }
        if (bits.length == 5 && line.contains("AND")) {
            return new AndOperation(bits[4], bits[0], bits[2]);
        }
        if (bits.length == 5 && line.contains("OR")) {
            return new OrOperation(bits[4], bits[0], bits[2]);
        }
        if (bits.length == 5 && line.contains("LSHIFT")) {
            return new LeftShiftOperation(bits[4], bits[0], Integer.parseInt(bits[2]));
        }
        if (bits.length == 5 && line.contains("RSHIFT")) {
            return new RightShiftOperation(bits[4], bits[0], Integer.parseInt(bits[2]));
        }
        if (bits.length == 4) {
            return new NotOperation(bits[3], bits[1]);
        }
        throw new IllegalArgumentException("Cannot parse '" + line + "'");
    }
}
