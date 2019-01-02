package advent.twenty_eighteen.support;

import advent.common.AbstractValueObjectTest;

public class LocationTest extends AbstractValueObjectTest<Location> {
    @Override
    protected Class<Location> getClassUnderTest() {
        return Location.class;
    }
}