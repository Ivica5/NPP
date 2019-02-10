package io.ivan.NPP.aspects;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.ivan.NPP.factoryPattern.Group;
import io.ivan.NPP.factoryPattern.GroupFactory;

import io.ivan.NPP.builderPattern.TeamInfo;
import io.ivan.NPP.repositoryPattern.RepoImplementation;

import io.ivan.NPP.utilities.GetTeamData;

@Aspect
@Component
public class ErrorDialogAspect {
	
	@Autowired
	private GroupFactory tablicaFactory;
	
	@Autowired
	private RepoImplementation repo;
	
	private TeamInfo teamInfo;
	private List<String> team;
	private Group group;
	
	@Before("execution(* io.ivan.NPP.DataController.tablesInfo(..))")
	public void tablesInfo(JoinPoint joinPoint) {
		group = tablicaFactory.makeGroup((String) joinPoint.getArgs()[1]);
		
		if(group == null) {
			throw new RuntimeException("wrongInputModal");
		}
	}
	
	@Before("execution(* io.ivan.NPP.DataController.teamInfo(..))")
	public void teamInfo(JoinPoint joinPoint) {
		teamInfo = GetTeamData.getData((String) joinPoint.getArgs()[1]);
		
		if(teamInfo == null) {
			throw new RuntimeException("wrongInputModal");
		}	
	}
	
	@Before("execution(* io.ivan.NPP.DataController.getAllTeamsInfo(..))")
	public void getAllTeamsInfo() {
		team = repo.GetAllTeams();
		
		if(team.isEmpty()) {
			throw new RuntimeException("baseModal");
		}
	}
	
	@Before("execution(* io.ivan.NPP.DataController.saveTeam(..))")
	public void saveTeam(JoinPoint joinPoint) {
		teamInfo = GetTeamData.getData((String) joinPoint.getArgs()[1]);
		
		if(teamInfo == null) {
			throw new RuntimeException("wrongInputModal");
		}	
	}
	
	@Before("execution(* io.ivan.NPP.DataController.getTeam(..))")
	public void getTeam(JoinPoint joinPoint) {
		teamInfo = GetTeamData.getData((String) joinPoint.getArgs()[1]);
		
		if(teamInfo == null) {
			throw new RuntimeException("baseModal");
		}
	}
	
	@Before("execution(* io.ivan.NPP.DataController.removeTeam(..))")
	public void removeTeam(JoinPoint joinPoint) {
		teamInfo = GetTeamData.getData((String) joinPoint.getArgs()[1]);
		
		if(teamInfo == null){
			throw new RuntimeException("baseModal");
		}		
	}	
}

