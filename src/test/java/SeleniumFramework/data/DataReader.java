package SeleniumFramework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Utf8;

public class DataReader {

	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		//read json to string
		String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//SeleniumFramework//data//PurchaseData.json"),StandardCharsets.UTF_8);
	
	
		//String to HashMap- Jackson Datbind
		
		ObjectMapper maper= new ObjectMapper();
		List<HashMap<String, String>> data = maper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		return data;
		
	
	
	
	
	}
	
}