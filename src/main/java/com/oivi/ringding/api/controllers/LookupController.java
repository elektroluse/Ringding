package com.oivi.ringding.api.controllers;

import com.oivi.ringding.api.services.LookupService;
import com.oivi.ringding.lib.LookupRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LookupController {
    private LookupService lookupService;

    public LookupController(LookupService lookupService){
        this.lookupService = lookupService;
    }

    @GetMapping(path = "/api/lookup/{number}")
    public ResponseEntity<LookupRecord> lookup(@PathVariable("number") String number) {

        LookupRecord result = lookupService.lookup(number);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
