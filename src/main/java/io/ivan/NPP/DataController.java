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
	
	private Group group;
	private TeamInfo teamInfo;
	private List<String> team;
	
	@PostMapping("/tableInfo")
	public String tablesInfo(Model model, @RequestParam("tableInfo") String tableInfo) {
		
		group = tablicaFactory.makeGroup(tableInfo);

        model.addAttribute("teams", group.getTeams());
        
        return "tableInfo";
        
	}
	
	@PostMapping("/teamInfo")
	public String teamInfo(Model model, @RequestParam("teamInfo") String teamName) {
		
		teamInfo = GetTeamData.getData(teamName);
		
		model.addAttribute("name", teamInfo.getName());
		model.addAttribute("address", teamInfo.getAddress());
		model.addAttribute("phone", teamInfo.getPhone());
		model.addAttribute("website", teamInfo.getWebsite());	
		model.addAttribute("email", teamInfo.getEmail());	
		model.addAttribute("founded", teamInfo.getFounded());	
		model.addAttribute("clubColors", teamInfo.getClubColors());
		model.addAttribute("venue", teamInfo.getVenue());
		
		return "teamInfo";
			
	}
	
	@PostMapping("/getTeam")
	public String getTeam(Model model, @RequestParam("getTeam") String teamName) {
		
		teamInfo = GetTeamData.getData(teamName);
		
		team = repo.GetTeam(teamInfo);
		
		model.addAttribute("infos", team);
		
		return "baseTeamInfo";
		
	}
	
	@PostMapping("/saveTeam")
	public String saveTeam(Model model, @RequestParam("saveTeam") String teamName) {
		
		teamInfo = GetTeamData.getData(teamName);
			
		repo.InsertTeam(teamInfo);
		
		return "redirect:/home";
		
	}
	
	@PostMapping("/getAllTeams")
	public String getAllTeamsInfo(Model model) {	
		
		List<String> allTeamsInfo = repo.GetAllTeams();
		
		model.addAttribute("infos", allTeamsInfo);
		
		return "baseTeamInfo";
	
	}
	
	@PostMapping("/removeTeam")
	public String removeTeam(Model model, @RequestParam("removeTeam") String teamName) {
		
		teamInfo = GetTeamData.getData(teamName);
		
		repo.RemoveTeam(teamInfo);
	
		return "redirect:/home";
		
	}
	
	
}
