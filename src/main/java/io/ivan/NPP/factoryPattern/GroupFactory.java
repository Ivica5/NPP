package io.ivan.NPP.factoryPattern;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import io.ivan.NPP.apiRequest.CollectData;

@Component
public class GroupFactory {
	
	private String allTables;
    private Group group;
    private List<Team> teams;
    private JsonObject allTablesJson;
    private JsonArray tableInfo;
    
	public Group makeGroup(String groupName) {
		allTablesJson = new JsonObject();
      
        allTables = CollectData.getInstance().getJSON("http://api.football-data.org/v2/competitions/WC/standings");
        
        allTablesJson = new JsonParser().parse(allTables).getAsJsonObject();	
        	
		switch(groupName){
			case "A":
				allTablesJson = (JsonObject) allTablesJson.getAsJsonArray("standings").get(0);
				break;
			case "B":
				allTablesJson = (JsonObject) allTablesJson.getAsJsonArray("standings").get(3);
				break;
			case "C":
				allTablesJson = (JsonObject) allTablesJson.getAsJsonArray("standings").get(6);
				break;
			case "D":
				allTablesJson = (JsonObject) allTablesJson.getAsJsonArray("standings").get(9);
				break;
			case "E":
				allTablesJson = (JsonObject) allTablesJson.getAsJsonArray("standings").get(12);
				break;
			case "F":
				allTablesJson = (JsonObject) allTablesJson.getAsJsonArray("standings").get(15);
				break;
			case "G":
				allTablesJson = (JsonObject) allTablesJson.getAsJsonArray("standings").get(18);
				break;
			case "H":
				allTablesJson = (JsonObject) allTablesJson.getAsJsonArray("standings").get(21);
				break;
		}
		
		tableInfo = allTablesJson.getAsJsonArray("table");
		
		Type groupList = new TypeToken<ArrayList<Team>>(){}.getType();	
		teams = new Gson().fromJson(tableInfo, groupList);
		
		if (teams != null) {
			group = new Group();
			group.setTeams(teams);
			
			return group;
		}else {
			return null;
		}

	}

}

