package io.ivan.NPP.repositoryPattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.ivan.NPP.builderPattern.TeamInfo;

@Component
public class RepoImplementation implements Repo{
	
	private String error = "Something went wrong :(";
	private String user = "user";
	private String password = "user123";
	private String url = "jdbc:mysql://localhost:3306/demo?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
	private PreparedStatement statement;
	private Connection con;	
	private ResultSet myRs;
	private List<String> teamInfos,allTeamsInfo;
	
	@Override
	public void InsertTeam(TeamInfo team) {
		
		try {
			con = DriverManager.getConnection(url,user,password);
			
			statement = (PreparedStatement) con.prepareStatement("INSERT INTO teams(TeamName, TeamAddress, TeamPhone, TeamWebsite, TeamEmail, TeamFounded, TeamClubColors, TeamVenue) VALUES(?,?,?,?,?,?,?,?)");

			statement.setString(1, team.getName());
			statement.setString(2, team.getAddress());
			statement.setString(3, team.getPhone());
			statement.setString(4, team.getWebsite());
			statement.setString(5, team.getEmail());
			statement.setInt(6, team.getFounded());
			statement.setString(7, team.getClubColors());
			statement.setString(8, team.getVenue());
			statement.executeUpdate();
			con.close();
			
			System.out.println("Team inserted!");
		} catch (RuntimeException e) {
			throw new RuntimeException();
		} catch (SQLException e) {
			System.out.println(error);
		}	
		
	}

	@Override
	public List<String> GetTeam(TeamInfo team) {
		teamInfos = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(url,user,password);
			statement = (PreparedStatement) con.prepareStatement("SELECT*FROM teams WHERE TeamName=?");
			statement.setString(1, team.getName());
			myRs = statement.executeQuery();
					
			while(myRs.next()){
				teamInfos.add(myRs.getString("TeamName"));
				teamInfos.add(myRs.getString("TeamAddress"));
				teamInfos.add(myRs.getString("TeamPhone"));
				teamInfos.add(myRs.getString("TeamWebsite"));
				teamInfos.add(myRs.getString("TeamEmail"));
				teamInfos.add(myRs.getString("TeamFounded"));
				teamInfos.add(myRs.getString("TeamClubColors"));
				teamInfos.add(myRs.getString("TeamVenue"));
			}

			statement.close();
			con.close();
			
		} catch (RuntimeException e) {
			throw new RuntimeException();
		} catch (SQLException e) {
			System.out.println(error);
		}
		return teamInfos;
	}
	
	@Override
	public List<String> GetAllTeams() {
		allTeamsInfo = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(url,user,password);
			statement = (PreparedStatement) con.prepareStatement("SELECT*FROM teams");
			myRs = statement.executeQuery();
		      
		      while (myRs.next())
		      {
		    	  allTeamsInfo.add(myRs.getString("TeamName"));
		    	  allTeamsInfo.add(myRs.getString("TeamAddress"));
		    	  allTeamsInfo.add(myRs.getString("TeamPhone"));
		    	  allTeamsInfo.add(myRs.getString("TeamWebsite"));
		    	  allTeamsInfo.add(myRs.getString("TeamEmail"));
		    	  allTeamsInfo.add(myRs.getString("TeamFounded"));
		    	  allTeamsInfo.add(myRs.getString("TeamClubColors"));
		    	  allTeamsInfo.add(myRs.getString("TeamVenue"));
		      }
		      
		      statement.close();
		      con.close();
		      
		} catch (RuntimeException e) {
			throw new RuntimeException();
		} catch (SQLException e) {
			System.out.println(error);
		}
		return allTeamsInfo;
	}

	@Override
	public void RemoveTeam(TeamInfo team) {
		try {
			con = DriverManager.getConnection(url,user,password);
			
			statement = (PreparedStatement) con.prepareStatement("DELETE FROM teams WHERE TeamName=?");
			statement.setString(1, team.getName());
			statement.executeUpdate();
			System.out.println("Team deleted!");
			
			statement.close();
			con.close();
			
		} catch (RuntimeException e) {
			throw new RuntimeException();
		} catch (SQLException e) {
			System.out.println(error);
		}
		
	}


}

