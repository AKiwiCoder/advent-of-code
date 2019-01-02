package advent.twenty_fifteen.support;

import advent.common.AbstractValueObjectTest;

public class ParcelTest extends AbstractValueObjectTest<Parcel> {

    @Override
    protected Class<Parcel> getClassUnderTest() {
        return Parcel.class;
    }
}