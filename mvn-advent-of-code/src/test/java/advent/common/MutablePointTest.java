package advent.common;

import static org.junit.Assert.*;

public class MutablePointTest extends AbstractValueObjectTest<MutablePoint> {

    @Override
    protected Class<MutablePoint> getClassUnderTest() {
        return MutablePoint.class;
    }
}