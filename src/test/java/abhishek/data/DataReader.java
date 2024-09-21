package abhishek.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\resources\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		//String to HashMap - Jackson Databind

		return new ObjectMapper().readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});

	}

}

// Now this class is not in use as this method is written in BaseTest.java


