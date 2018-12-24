package advent.twenty_eighteen.support;

import advent.common.AbstractValueObjectTest;

import static org.junit.Assert.*;

public class CartTest extends AbstractValueObjectTest<Cart> {

    @Override
    protected boolean isMutableObject() {
        return true;
    }

    @Override
    protected Class<Cart> getClassUnderTest() {
        return Cart.class;
    }
}