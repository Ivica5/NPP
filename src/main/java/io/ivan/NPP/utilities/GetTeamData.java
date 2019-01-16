package io.ivan.NPP.utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.ivan.NPP.apiRequest.CollectData;
import io.ivan.NPP.builderPattern.TeamInfo;
import io.ivan.NPP.builderPattern.TeamInfoBuilder;

public class GetTeamData {
		
	public static TeamInfo getData(String teamName) {
		TeamInfo teamInfo = null;
		int count = 0;
		
		String teams = CollectData.getInstance().getJSON("http://api.football-data.org/v2/competitions/WC/teams"); 
		
	    JsonElement jelement = new JsonParser().parse(teams);
	     
	    JsonObject jobject = jelement.getAsJsonObject();	
	    
	    count = jobject.get("count").getAsInt();
	    
	    JsonArray jarray = jobject.getAsJsonArray("teams");
	    		
		for(int i = 0; i < count; i++){
			jobject = jarray.get(i).getAsJsonObject();

			if(jobject.get("name").getAsString().equals(teamName)){
				 teamInfo = new TeamInfoBuilder()
							 		 .setName(jobject.get("name").getAsString())
									 .setAddress(jobject.get("address").getAsString())
									 .setPhone(jobject.get("phone").getAsString())
									 .setWebsite(jobject.get("website").getAsString())
									 .setEmail(jobject.get("email").getAsString())
									 .setFounded(jobject.get("founded").getAsInt())
									 .setClubColors(jobject.get("clubColors").getAsString())
									 .setVenue(jobject.get("venue").getAsString())
									 .build();
								
				break;		 
			}
		}
		
		return teamInfo;
	}	
}

