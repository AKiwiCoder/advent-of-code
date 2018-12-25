package advent.twenty_eighteen.support;

import advent.common.AbstractValueObjectTest;

import static org.junit.Assert.*;

public class LocationTest extends AbstractValueObjectTest<Location> {
    @Override
    protected Class<Location> getClassUnderTest() {
        return Location.class;
    }
}