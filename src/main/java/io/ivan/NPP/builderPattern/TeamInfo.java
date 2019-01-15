package io.ivan.NPP.builderPattern;

public class TeamInfo {
	
	private String name;
	private String address;
	private String phone;
	private String website;
	private String email;
	private int founded;
	private String clubColors;
	private String venue;
	
	public TeamInfo(String name, String address, String phone, String website, String email, int founded, String clubColors, String venue){
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.email = email;
		this.founded = founded;
		this.clubColors = clubColors;
		this.venue = venue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getFounded() {
		return founded;
	}
	
	public String getClubColors() {
		return clubColors;
	}
	
	public String getVenue() {
		return venue;
	}
	
	@Override
	public String toString() {
		return String.format("Name: \\n%3s Address: \n%3s \n\nPhone:\n%3s \n\nWebsite:\n%3s \n\nEmail:\n%3s \n\nFounded:\n%3s \n\nClub colors:\n%3s \n\nVenue:\n%3s", name, address, phone, website, email, founded, clubColors, venue);
	}	
	
}
