package io.ivan.NPP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.ivan.NPP.factoryPattern.Group;
import io.ivan.NPP.factoryPattern.GroupFactory;
import io.ivan.NPP.builderPattern.TeamInfo;
import io.ivan.NPP.repositoryPattern.RepoImplementation;
import io.ivan.NPP.utilities.GetTeamData;

@Controller
public class DataController {
	
	@Autowired
	private GroupFactory tablicaFactory;
	
	@Autowired
	private RepoImplementation repo;
	
	@PostMapping("/tableInfo")
	public String tablesInfo(Model model, @RequestParam("tableInfo") String tableInfo) {
		
		Group group = tablicaFactory.makeGroup(tableInfo);

        model.addAttribute("teams", group.getTeams());
        
        return "tableInfo";
        
	}
	
	@PostMapping("/teamInfo")
	public String teamInfo(Model model, @RequestParam("teamInfo") String teamName) {
		
		TeamInfo team = GetTeamData.getData(teamName);
		
		model.addAttribute("name", team.getName());
		model.addAttribute("address", team.getAddress());
		model.addAttribute("phone", team.getPhone());
		model.addAttribute("website", team.getWebsite());	
		model.addAttribute("email", team.getEmail());	
		model.addAttribute("founded", team.getFounded());	
		model.addAttribute("clubColors", team.getClubColors());
		model.addAttribute("venue", team.getVenue());
		
		return "teamInfo";
			
	}
	
	@PostMapping("/getTeam")
	public String getTeam(Model model, @RequestParam("getTeam") String teamName) {
		
		TeamInfo team = GetTeamData.getData(teamName);
		
		List<String> teamInfos = repo.GetTeam(team);
		
		model.addAttribute("infos", teamInfos);
		
		return "baseTeamInfo";
		
	}
	
	@PostMapping("/saveTeam")
	public String saveTeam(Model model, @RequestParam("saveTeam") String teamName) {
		
		TeamInfo team = GetTeamData.getData(teamName);
			
		repo.InsertTeam(team);
		
		return "redirect:/home";
		
	}
	
	@PostMapping("/getAllTeams")
	public String getAllTeamsResults(Model model) {	
		
		List<String> allTeamsInfo = repo.GetAllTeam();
		
		model.addAttribute("infos", allTeamsInfo);
		
		return "baseTeamInfo";
	
	}
	
	@PostMapping("/removeTeam")
	public String removeTeam(Model model, @RequestParam("removeTeam") String teamName) {
		
		TeamInfo teamInfo = GetTeamData.getData(teamName);
		
		repo.RemoveTeam(teamInfo);
	
		return "redirect:/home";
		
	}
	
	
}
