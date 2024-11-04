package com.oivi.ringding.controllers;

import com.oivi.ringding.dao.PhonebookRecordDao;
import com.oivi.ringding.dao.impl.PhonebookRecordDaoImpl;
import com.oivi.ringding.domain.PhonebookRecord;
import com.oivi.ringding.mappers.Mapper;
import com.oivi.ringding.mappers.impl.LookupRecordMapperImpl;
import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.PhonebookService;
import com.oivi.ringding.services.impl.Ringding;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PhonebookController {

    private Ringding ringding;
    private PhonebookService phonebookService;
    public PhonebookController(Ringding ringding, PhonebookService phonebookService) {
        this.ringding = ringding;
        this.phonebookService = phonebookService;
    }

    @PostMapping(path = "/api/db/lookup/save")
    public ResponseEntity<Optional<PhonebookRecord>> lookupAndCreate(@RequestBody String phoneNum){

        LookupRecord lr = ringding.lookup(phoneNum);
        Optional<PhonebookRecord> result = phonebookService.saveToPhonebook(lr);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @GetMapping(path = "/api/db/{number}")
    public ResponseEntity<Optional<PhonebookRecord>> readOne(@PathVariable String number){
        Optional<PhonebookRecord> result = phonebookService.readOne(number);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping(path = "/api/db/all")
    public ResponseEntity<List<PhonebookRecord>> readAll(){

        List<PhonebookRecord> result = phonebookService.readAll();
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @GetMapping(path = "/api/db/all/{number}")
    public ResponseEntity<List<PhonebookRecord>> readAll(@PathVariable String number){
        List<PhonebookRecord> result = phonebookService.readAll(number);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }
    @GetMapping(path = "/api/db/latest/{number}")
    public ResponseEntity<Optional<PhonebookRecord>> readLatest(@PathVariable String number){
        Optional<PhonebookRecord> result = phonebookService.findLatestNum(number);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/db/delete/old/{number}")
    public ResponseEntity<Integer> deleteOld(@PathVariable String number){
        Integer result = phonebookService.deleteOld(number);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping(path = "/api/db/insert")
    public ResponseEntity<Integer> insertPR(@RequestBody PhonebookRecord phonebookRecord){
        int result = phonebookService.insertToPhonebook(phonebookRecord);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

}
