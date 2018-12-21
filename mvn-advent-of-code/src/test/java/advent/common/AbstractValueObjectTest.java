package advent.common;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public abstract class AbstractValueObjectTest<T> {
    protected abstract Class<T> getClassUnderTest();

    @Test
    public void verifyHashcodeAndEquals() {
        EqualsVerifier.forClass(getClassUnderTest()).usingGetClass().verify();
    }
}
