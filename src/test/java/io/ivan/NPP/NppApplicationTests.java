package io.ivan.NPP;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.ivan.NPP.builderPattern.TeamInfo;
import io.ivan.NPP.factoryPattern.Group;
import io.ivan.NPP.factoryPattern.GroupFactory;
import io.ivan.NPP.factoryPattern.Team;
import io.ivan.NPP.repositoryPattern.RepoImplementation;
import io.ivan.NPP.utilities.GetTeamData;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NppApplicationTests {
	
	@Autowired
	private GroupFactory tablicaFactory;
	
	@Autowired
	private RepoImplementation repo;
	

	@Test
	public void getTableInfo_check_correctInput_for_GroupA() {
		Group group = tablicaFactory.makeGroup("A");
		
		Assert.assertTrue("Correct input.Group A exist", group != null);
		
		System.out.println();
		
		System.out.println("#   Club                      GP    PTS    GF    GA    GD");
        for (Team t : group.getTeams()) {
            System.out.println(t);
        }
        
        System.out.println("------------------------------------------------------");   
	}
	
	@Test
	public void getTableInfo_wrongGroupInput_check_if_group_Z_exist() {
		Group group = tablicaFactory.makeGroup("Z");
		
		Assert.assertTrue("Wrong input.Group Z doesn't exist", group == null);
        
    	System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void getTeamInfo_correctInput_for_teamCroatia() {
		TeamInfo team = GetTeamData.getData("Croatia");
		
		Assert.assertTrue("Correct input.Team exist", team != null);
		
		System.out.println();
		
		System.out.println(team);
		
		System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void getTeamInfo_check_wrongInput_for_teamJapan() {
		TeamInfo team = GetTeamData.getData("Croati");
		
		Assert.assertTrue("Wron input.Team doesn't exist", team == null);
		
		System.out.println();
		
		System.out.println(team);
		
		System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void getAllTeamsInfos_at_least_one_teamInfos_should_be_in_base() {
		List<String> allTeamInfos = repo.GetAllTeam();
				
		Assert.assertTrue("Database contains at least one team.", !allTeamInfos.isEmpty());
		
		System.out.println();
		
		System.out.println(allTeamInfos);
		
		System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void getTeamFromBase_team_should_not_exist_in_base() {
		TeamInfo team = GetTeamData.getData("Senegal");
		
		List<String> teamInfos = repo.GetTeam(team);
		
		Assert.assertTrue("Team doesn't exist in base.", teamInfos.isEmpty());
		
		System.out.println();
		
		System.out.println(teamInfos);
		
		System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void saveTeamInBase_check_is_it_saved() {
		int beforeInsert = repo.GetAllTeam().size();
		
		System.out.println("Number of data before insert: " + beforeInsert);
		
		TeamInfo team = GetTeamData.getData("Poland");
			
		repo.InsertTeam(team);
		
		int afterInsert = repo.GetAllTeam().size();
		
		System.out.println();
		
		System.out.println("Number of data after insert: " + afterInsert);
				
		Assert.assertTrue("Team is inserted.", afterInsert > beforeInsert);
		
		System.out.println("------------------------------------------------------");  
	}

}


