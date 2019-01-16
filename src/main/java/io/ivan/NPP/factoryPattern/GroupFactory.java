package io.ivan.NPP.factoryPattern;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import io.ivan.NPP.apiRequest.CollectData;

@Component
public class GroupFactory {

	public Group makeGroup(String groupName) {

        Group g = null;
        List<Team> teams = null;
        
        String tablesData = CollectData.getInstance().getJSON("http://api.football-data.org/v2/competitions/WC/standings");
        
		JsonElement jelement = new JsonParser().parse(tablesData);
		
		JsonObject jobject = jelement.getAsJsonObject();	
		
		jelement = jobject.getAsJsonArray("standings");

		JsonArray jArray = (JsonArray) jelement;
		
		switch(groupName){
			case "A":
				jobject = (JsonObject) jArray.get(0);
				break;
			case "B":
				jobject = (JsonObject) jArray.get(3);
				break;
			case "C":
				jobject = (JsonObject) jArray.get(6);
				break;
			case "D":
				jobject = (JsonObject) jArray.get(9);
				break;
			case "E":
				jobject = (JsonObject) jArray.get(12);
				break;
			case "F":
				jobject = (JsonObject) jArray.get(15);
				break;
			case "G":
				jobject = (JsonObject) jArray.get(18);
				break;
			case "H":
				jobject = (JsonObject) jArray.get(21);
				break;
		}
		
		jelement = jobject.getAsJsonArray("table");
		
		Type groupList = new TypeToken<ArrayList<Team>>(){}.getType();	
		teams = new Gson().fromJson(jelement, groupList);
		
		if (teams != null) {
			g = new Group();
			g.setTeams(teams);
		}
		
        return g;
	}

}

