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
	
	@Before("execution(* io.ivan.NPP.DataController.*(..))")
	public void teamsAdvice(JoinPoint joinPoint) {
		
		TeamInfo team;
		List<String> teamResults;
		
		System.out.println("Pointcut triggered");
		System.out.println(joinPoint.getSignature().getName());
				
		switch(joinPoint.getSignature().getName()) {
			case "tablesInfo":
				Group group = tablicaFactory.makeGroup((String) joinPoint.getArgs()[1]);
				if(group == null) {
					throw new RuntimeException("wrongInputModal");
				}
				break;
			case "getAllTeamsResults":
				teamResults = repo.GetAllTeam();				
				if(teamResults.isEmpty()) {
					throw new RuntimeException("baseModal");
				}
				break;
			case "getTeam":
				team = GetTeamData.getData((String) joinPoint.getArgs()[1]);
				if(team == null) {
					throw new RuntimeException("wrongInputModal");
				}
				teamResults = repo.GetTeam(team);			
				if(teamResults.isEmpty()) {
					throw new RuntimeException("baseModal");
				}
				break;
			case "saveTeam":
			case "teamInfo":
				team = GetTeamData.getData((String) joinPoint.getArgs()[1]);
				if(team == null) {
					throw new RuntimeException("wrongInputModal");
				}
				break;
			case "removeTeam":
				team = GetTeamData.getData((String) joinPoint.getArgs()[1]);		
				if(team == null){
					throw new RuntimeException("baseModal");
				}
				break;
		}	
	}
}
