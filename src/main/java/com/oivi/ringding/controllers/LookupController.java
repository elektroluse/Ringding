package com.oivi.ringding.controllers;

import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.impl.Ringding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
