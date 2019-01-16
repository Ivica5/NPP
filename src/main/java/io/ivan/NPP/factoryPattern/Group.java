package io.ivan.NPP.factoryPattern;

import java.util.List;

public class Group {
	
	private String groupName;
    private List<Team> teams;
    
    public void setTeams(List<Team> teams){
    	this.teams = teams;
    }
    
    public List<Team> getTeams(){
    	return teams;
    }

	public void setName(String groupName) {
		this.groupName = groupName;		
	}
	
	public String getName() {
		return groupName;		
	}
}
