package com.oivi.ringding.controllers;

import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.impl.Ringding;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    @PostMapping(path = "/api/lookup/upload")
    public ResponseEntity<List<LookupRecord>> upload(@RequestPart("file") MultipartFile mf){
        List<LookupRecord> result = new ArrayList<LookupRecord>();
        String filename = mf.getOriginalFilename();
        String contentType = mf.getContentType();
        Resource fileContent = mf.getResource();
        //System.out.println(filename + " content type:   " + contentType);
        if(!contentType.equals("text/plain")){
            return new ResponseEntity<>(result, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        result = ringding.lookupFromResource(fileContent);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
