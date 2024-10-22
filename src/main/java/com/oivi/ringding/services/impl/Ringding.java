package com.oivi.ringding.services.impl;


import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.Scraper;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ringding {

    private Scraper scraper;

    public Ringding(Scraper scraper) {
        this.scraper = scraper;
    }

    public LookupRecord lookup(String phoneNum){
        return scraper.lookup(phoneNum);
    }


    public List<LookupRecord> lookupBulkFile(File f){
        List<String> phoneNumbers = fileToList(f);
        return lookupList(phoneNumbers);
    }

    public List<LookupRecord> lookupList(List<String> listOfNums){
        List<LookupRecord> results = new ArrayList<>();
        for(String s : listOfNums){
            results.add(lookup(s));
        }
        return results;
    }

    private List<String> fileToList(File f){

        BufferedReader br = openABufferedReader(f);
        List<String> result = br.lines().toList();
        closeBufferedReader(br);

        return result;
    }
    public List<LookupRecord> lookupInputStream(InputStream is){
        BufferedReader br = openStreamBr(is);
        return lookupList(bufferedReaderToList(br));
    }

    public List<String> inputStreamToList(InputStream is){
        BufferedReader br = openStreamBr(is);
        return bufferedReaderToList(br);

    }

    public List<String> bufferedReaderToList(BufferedReader br){

        List<String> result = br.lines().toList();
        closeBufferedReader(br);
        return result;
    }

    public List<LookupRecord> lookupFromResource(Resource resource){
        BufferedReader br = openStreamBr(openInputStream(resource));
        return lookupList(bufferedReaderToList(br));
    }

    /*
        Utility methods to avoid cluttering the application logic
        with try-catch statements and/or propagating a throws statement
        up the call chain

        (Consider refactoring to a util class with static methods maybe)

     */

    private InputStream openInputStream(Resource resource){
        InputStream inputStream;
        try{
            inputStream = resource.getInputStream();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return inputStream;
    }
    private BufferedReader openABufferedReader(File fileObj){
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(fileObj));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return br;
    }

    private void closeBufferedReader(BufferedReader br){
        try{
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader openStreamBr(InputStream source){
        BufferedReader br;
        try{
            br = new BufferedReader(new InputStreamReader(source));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return br;
    }

}
