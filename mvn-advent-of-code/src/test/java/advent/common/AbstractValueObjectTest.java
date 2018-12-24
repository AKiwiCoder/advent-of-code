package advent.common;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifierApi;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public abstract class AbstractValueObjectTest<T> {
    protected abstract Class<T> getClassUnderTest();

    protected boolean isMutableObject() {
        return false;
    }

    @Test
    public void verifyHashcodeAndEquals() {
        EqualsVerifierApi<T> verifierApi = EqualsVerifier.forClass(getClassUnderTest());
        if (isMutableObject()) {
            verifierApi = verifierApi.suppress(Warning.NONFINAL_FIELDS);
        }
        verifierApi.usingGetClass().verify();
    }
}
