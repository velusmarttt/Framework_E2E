package Velusmarttacademy_Test.Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;


public class DataReader {
    public List<HashMap<String, String>> getJsonDataMap() throws IOException {
        //read json to string
        String jsoncontent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src\\test\\java\\Velusmarttacademy_Test\\Data\\PurchaseOrder.json")
                , StandardCharsets.UTF_8);
        //Convert string to Hashmap using jackson databind
        ObjectMapper objectMapper=new ObjectMapper();
        List<HashMap<String, String>> data=objectMapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
}
