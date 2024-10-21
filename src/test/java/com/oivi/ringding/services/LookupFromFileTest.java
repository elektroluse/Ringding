package com.oivi.ringding.services;

import com.oivi.ringding.services.impl.GulesiderScraper;
import com.oivi.ringding.services.impl.Ringding;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LookupFromFileTest {

    @Test
    public void testFileLookup() {

        List<LookupRecord> expected = new ArrayList<LookupRecord>();
        expected.add(new LookupRecord("23291700", "NORSK GALLUP INSTITUTT AS", true));
        expected.add(new LookupRecord("41353151", "N/A",false));
        expected.add(new LookupRecord("48841237","NORSTAT AS",true));

        Ringding ringding = new Ringding(new GulesiderScraper());

        URL url = this.getClass().getResource("/test3num.txt");
        File f = new File(url.getFile());
        List<LookupRecord> actual =  ringding.lookupBulkFile(f);

        assertThat(actual).isEqualTo(expected);


    }
}
