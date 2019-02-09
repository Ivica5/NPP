package io.ivan.NPP.utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.ivan.NPP.apiRequest.CollectData;
import io.ivan.NPP.builderPattern.TeamInfo;
import io.ivan.NPP.builderPattern.TeamInfoBuilder;

public class GetTeamData {
		
	private static final int numberOfTeams = 32;
	private static String allTeams;
	private static JsonObject allTeamsJson,teamInfo;
	
	public static TeamInfo getData(String teamName) {
			
		allTeams = CollectData.getInstance().getJSON("http://api.football-data.org/v2/competitions/WC/teams"); 
	     
	    allTeamsJson = new JsonParser().parse(allTeams).getAsJsonObject();	
	        		
		for(int i = 0; i < numberOfTeams; i++){
			teamInfo = allTeamsJson.getAsJsonArray("teams").get(i).getAsJsonObject();

			if(teamInfo.get("name").getAsString().equals(teamName)){
				 return new TeamInfoBuilder()
							 		 .setName(teamInfo.get("name").getAsString())
									 .setAddress(teamInfo.get("address").getAsString())
									 .setPhone(teamInfo.get("phone").getAsString())
									 .setWebsite(teamInfo.get("website").getAsString())
									 .setEmail(teamInfo.get("email").getAsString())
									 .setFounded(teamInfo.get("founded").getAsInt())
									 .setClubColors(teamInfo.get("clubColors").getAsString())
									 .setVenue(teamInfo.get("venue").getAsString())
									 .build();
										 
			}
		}
		
		return null;
	}	
}

