package com.oivi.ringding.services;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
    Just a simple to test to confirm that
    AssertThat(Actual).isEqualTo(Expected)
    and isNotEqualTo works correctly with LookupRecords

 */


public class AssertThatIsOrNotEqualTest {

    @Test
    public void testIsEqualTo(){
        LookupRecord a = new LookupRecord("12","a", true);
        LookupRecord b = new LookupRecord("12","a", true);

        assertThat(a).isEqualTo(b);
    }

    @Test
    public void testIsNotEqualTo(){
        LookupRecord a = new LookupRecord("12","a", true);
        LookupRecord b = new LookupRecord("12","a", false);
        LookupRecord c = new LookupRecord("23","a", true);
        LookupRecord d = new LookupRecord("12","b", true);
        assertThat(a).isNotEqualTo(b);
        assertThat(a).isNotEqualTo(c);
        assertThat(a).isNotEqualTo(d);
    }


}
