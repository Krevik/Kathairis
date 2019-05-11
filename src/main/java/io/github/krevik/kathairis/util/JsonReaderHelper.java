package io.github.krevik.kathairis.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.github.krevik.kathairis.Kathairis;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.Context;
import net.minecraft.util.ResourceLocation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author Krevik
 */
public class JsonReaderHelper {

    public JsonElement readJsonFile(ResourceLocation path){
        JsonParser parser = new JsonParser();
        try {
            FileReader reader = new FileReader(path.getPath());
            return parser.parse(new JsonReader(reader));
        } catch (FileNotFoundException e) {
            Kathairis.KATHAIRIS_LOG.error("Desired File not found/cannot be read!");
            e.printStackTrace();
        }
        Kathairis.KATHAIRIS_LOG.error("Something went wrong, returning null JsonElement");
        return null;
    }

    public String getStringForTitle(ResourceLocation path,String title){
        String result;
        JsonObject json = (JsonObject) readJsonFile(path);
        result=json.get(title).toString();
        return result;
    }

    public ArrayList<String> getStringsForTitle(ResourceLocation path, String title){
        boolean shouldContinue=true;
        ArrayList<String> result = new ArrayList<>();
        int i=0;
        while(shouldContinue){
            String fullTitle = title+"_"+i;
            String readString = getStringForTitle(path,fullTitle);
            if(readString!=null){
                if(!readString.isEmpty()){
                    result.add(readString);
                }else{
                    shouldContinue=false;
                }
            }else{
                shouldContinue=false;
            }

            i++;
        }

        return result;
    }

}
