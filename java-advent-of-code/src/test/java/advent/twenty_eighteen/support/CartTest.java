package advent.twenty_eighteen.support;

import advent.common.AbstractValueObjectTest;

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