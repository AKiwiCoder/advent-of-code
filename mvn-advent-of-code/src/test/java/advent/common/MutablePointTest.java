package advent.common;

public class MutablePointTest extends AbstractValueObjectTest<MutablePoint> {

    @Override
    protected boolean isMutableObject() {
        return true;
    }

    @Override
    protected Class<MutablePoint> getClassUnderTest() {
        return MutablePoint.class;
    }
}