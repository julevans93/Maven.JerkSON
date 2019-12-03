package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;

public class Main {

    public Main() throws FileNotFoundException {
    }

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawInput.JerkSON"));
        return result;
    }

    public static void main(String[] args) throws Exception {
        String output = (new Main()).readRawDataToString();
        //System.out.println(output);
        ItemParser parser = new ItemParser();
        //parser.parseItemList(output);

        System.out.println(parser.parseItemList(output));


    }
}
