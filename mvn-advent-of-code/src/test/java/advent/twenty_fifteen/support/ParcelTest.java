package advent.twenty_fifteen.support;

import advent.common.AbstractValueObjectTest;

import static org.junit.Assert.*;

public class ParcelTest extends AbstractValueObjectTest<Parcel> {

    @Override
    protected Class<Parcel> getClassUnderTest() {
        return Parcel.class;
    }
}