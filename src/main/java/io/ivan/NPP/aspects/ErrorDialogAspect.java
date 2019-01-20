package io.ivan.NPP.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.ivan.NPP.factoryPattern.Group;
import io.ivan.NPP.factoryPattern.GroupFactory;

@Aspect
@Component
public class ErrorDialogAspect {
	
	@Autowired
	private GroupFactory tablicaFactory;	
	
	@Before("execution(* io.ivan.NPP.DataController.*(..))")
	public void teamsAdvice(JoinPoint joinPoint) {
						
		System.out.println("Pointcut triggered");
		System.out.println(joinPoint.getSignature().getName());
				
		switch(joinPoint.getSignature().getName()) {
			case "tablesInfo":
				Group group = tablicaFactory.makeGroup((String) joinPoint.getArgs()[1]);
				if(group == null) {
					throw new RuntimeException("wrongInputModal");
				}
				break;
		}	
	}
}
