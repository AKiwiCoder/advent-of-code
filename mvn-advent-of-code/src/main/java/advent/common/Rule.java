package advent.common;

import java.util.Objects;

public final class Rule {
    private final String pattern;
    private final char output;

    public Rule(String pattern, char output) {
        this.pattern = pattern;
        this.output = output;
    }

    public String getPattern() {
        return pattern;
    }

    public char getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return output == rule.output &&
                Objects.equals(pattern, rule.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, output);
    }
}
