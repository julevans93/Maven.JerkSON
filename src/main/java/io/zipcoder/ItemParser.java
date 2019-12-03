package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    private Integer invalidEntry = 0;
    private Integer total = 0;

    public List<Item> parseItemList(String valueToParse) throws ItemParseException {
        List<Item> itemList = new ArrayList<>();
        Pattern pattern = Pattern.compile("([^#]*)##");
        Matcher matcher = pattern.matcher(valueToParse);

        for (int i = 0; matcher.find(); i++){
            Item temp = parseSingleItem(matcher.group());
            if (temp != null) itemList.add(temp);
        }

        return itemList;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        //every item includes the following
        String name = "";
        Double price = 0.0;
        String type = "";
        String expiration = "";

        //create matcher object
        Pattern pattern = Pattern.compile("(?<=[:@^*%])(.*?)(?=[;#])");
        Matcher matcher = pattern.matcher(singleItem);

        //try catch to catch errors while looping through the items
        try{
            for (int i = 0; matcher.find(); i++){
                if (i == 0){name = matcher.group().toLowerCase(); total++;}
                else if (i == 1){price = Double.parseDouble(matcher.group()); total++;}
                else if (i == 2){type = matcher.group().toLowerCase(); total++;}
                else if (i == 3){expiration = matcher.group(); total++;}
                else {
                    System.out.println("Invalid Entry");
                }
            }
            //if less than 4 items throw exception
            if (total < 4){
                invalidEntry++;
                throw new ItemParseException();
                }
                if (name.equals("") || price == 0.0 || type.equals("") || expiration.equals("")){
                    invalidEntry++;
                    return null;
                }
                return new Item(name, price, type, expiration);
            } catch (Exception e){
            invalidEntry++;
            throw new ItemParseException();
        }//return  null;
    }

    public Integer getErrors(){
        return invalidEntry;
    }
}
