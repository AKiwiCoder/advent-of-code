package advent.twenty_eighteen.support;

import advent.common.AbstractValueObjectTest;
import advent.twenty_eighteen.support.Rule;

public class RuleTest extends AbstractValueObjectTest<Rule> {

    @Override
    protected Class<Rule> getClassUnderTest() {
        return Rule.class;
    }
}