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
	
	@Before("execution(* io.ivan.NPP.DataController.*(..))")
	public void teamsAdvice(JoinPoint joinPoint) {
	
		System.out.println("Pointcut triggered");
		System.out.println(joinPoint.getSignature().getName());
				
		switch(joinPoint.getSignature().getName()) {
			case "tablesInfo":
				group = tablicaFactory.makeGroup((String) joinPoint.getArgs()[1]);
				
				if(group == null) {
					throw new RuntimeException("wrongInputModal");
				}
				
				break;
			case "getAllTeamsInfo":
				team = repo.GetAllTeams();
				
				if(team.isEmpty()) {
					throw new RuntimeException("baseModal");
				}
				
				break;
			case "getTeam":
				teamInfo = GetTeamData.getData((String) joinPoint.getArgs()[1]);
						
				if(teamInfo == null) {
					throw new RuntimeException("baseModal");
				}
				
				team = repo.GetTeam(teamInfo);
				
				if(team.isEmpty()) {
					throw new RuntimeException("baseModal");
				}
				
				break;
			case "saveTeam":
			case "teamInfo":
				teamInfo = GetTeamData.getData((String) joinPoint.getArgs()[1]);
				
				if(teamInfo == null) {
					throw new RuntimeException("wrongInputModal");
				}
				
				break;
			case "removeTeam":
				teamInfo = GetTeamData.getData((String) joinPoint.getArgs()[1]);

				if(teamInfo == null){
					throw new RuntimeException("baseModal");
				}							
				
				break;
		}	
	}
}
