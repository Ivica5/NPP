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
	
	private Group group;
	private TeamInfo teamInfo;
	private List<String> allTeamInfos;
	
	@Test
	public void getTableInfo_check_correctInput_for_GroupA() {
		group = tablicaFactory.makeGroup("A");
		
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
		group = tablicaFactory.makeGroup("Z");
		
		Assert.assertTrue("Wrong input.Group Z doesn't exist", group == null);
        
    	System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void getTeamInfo_correctInput_for_teamCroatia() {
		teamInfo = GetTeamData.getData("Croatia");
		
		Assert.assertTrue("Correct input.Team exist", teamInfo != null);
		
		System.out.println();
		
		System.out.println(teamInfo);
		
		System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void getTeamInfo_check_wrongInput_for_teamJapan() {
		teamInfo = GetTeamData.getData("Croati");
		
		Assert.assertTrue("Wron input.Team doesn't exist", teamInfo == null);
		
		System.out.println();
		
		System.out.println(teamInfo);
		
		System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void saveTeamInBase_check_is_it_saved() {
		int beforeInsert = repo.GetAllTeams().size();
		
		System.out.println("Number of data before insert: " + beforeInsert);
		
		teamInfo = GetTeamData.getData("Poland");
			
		repo.InsertTeam(teamInfo);
		
		int afterInsert = repo.GetAllTeams().size();
		
		System.out.println();
		
		System.out.println("Number of data after insert: " + afterInsert);
				
		Assert.assertTrue("Team is inserted.", afterInsert > beforeInsert);
		
		System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void getAllTeams_at_least_one_team_should_be_in_base() {
		allTeamInfos = repo.GetAllTeams();
				
		Assert.assertTrue("Database contains at least one team.", !allTeamInfos.isEmpty());
		
		System.out.println();
		
		System.out.println(allTeamInfos);
		
		System.out.println("------------------------------------------------------");  
	}
	
	@Test
	public void getTeamFromBase_team_should_not_exist_in_base() {
		teamInfo = GetTeamData.getData("Senegal");
		
		allTeamInfos = repo.GetTeam(teamInfo);
		
		Assert.assertTrue("Team doesn't exist in base.", allTeamInfos.isEmpty());
		
		System.out.println();
		
		System.out.println(allTeamInfos);
		
		System.out.println("------------------------------------------------------");  
	}

}


