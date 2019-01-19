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
	
	String error = "Something went wrong :(";
	String user = "user";
	String password = "user123";
	String url = "jdbc:mysql://localhost:3306/demo?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
	Connection con = null;
	PreparedStatement statement = null;
	ResultSet myRs = null;
	
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
		List<String> teamInfos = new ArrayList<>();
		
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
	public List<String> GetAllTeam() {
		List<String> allTeamInfos = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(url,user,password);
			statement = (PreparedStatement) con.prepareStatement("SELECT*FROM teams");
			myRs = statement.executeQuery();
		      
		      while (myRs.next())
		      {
					allTeamInfos.add(myRs.getString("TeamName"));
					allTeamInfos.add(myRs.getString("TeamAddress"));
					allTeamInfos.add(myRs.getString("TeamPhone"));
					allTeamInfos.add(myRs.getString("TeamWebsite"));
					allTeamInfos.add(myRs.getString("TeamEmail"));
					allTeamInfos.add(myRs.getString("TeamFounded"));
					allTeamInfos.add(myRs.getString("TeamClubColors"));
					allTeamInfos.add(myRs.getString("TeamVenue"));
		      }
		      
		      statement.close();
		      con.close();
		      
		} catch (RuntimeException e) {
			throw new RuntimeException();
		} catch (SQLException e) {
			System.out.println(error);
		}
		return allTeamInfos;
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

