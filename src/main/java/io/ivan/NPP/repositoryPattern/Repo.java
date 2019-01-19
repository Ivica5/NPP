package io.ivan.NPP.repositoryPattern;

import java.util.List;

import io.ivan.NPP.builderPattern.TeamInfo;

public interface Repo {
		
	void InsertTeam(TeamInfo team);
	List<String> GetTeam(TeamInfo team);
	
}
