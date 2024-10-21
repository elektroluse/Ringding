package com.oivi.ringding.controllers;

import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.impl.Ringding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class LookupController {
    private Ringding ringding;

    public LookupController(Ringding ringding){
        this.ringding = ringding;
    }

    @GetMapping(path = "/api/lookup/{number}")
    public ResponseEntity<LookupRecord> lookup(@PathVariable("number") String number) {

        LookupRecord result = ringding.lookup(number);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    /*  Takes a JSON string array of numbers in the request body,
        responsibility of processing a file can be given to the
        client rather than processing the file serverside.
     */
    @PostMapping(path = "/api/lookup/list")
    public ResponseEntity<List<LookupRecord>> listLookup(@RequestBody List<String> nums) {
        List<LookupRecord> result = ringding.lookupList(nums);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
