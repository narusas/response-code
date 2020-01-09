package foo.bar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DomainsTest {
    @Test
    public void domain() {
        assertEquals("OKOK", FooCodes.FO_00001_OKOK.getDescription());

    }
}
