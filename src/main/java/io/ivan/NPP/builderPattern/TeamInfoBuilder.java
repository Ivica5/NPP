package io.ivan.NPP.builderPattern;

public class TeamInfoBuilder {
	
	private String name,address,phone,website,email,clubColors,venue;
	private int founded;
	
	public TeamInfoBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public TeamInfoBuilder setAddress(String address) {
		this.address = address;
		return this;
	}
	
	public TeamInfoBuilder setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	
	public TeamInfoBuilder setWebsite(String website) {
		this.website = website;
		return this;
	}
	
	public TeamInfoBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public TeamInfoBuilder setFounded(int founded) {
		this.founded = founded;
		return this;
	}
	
	public TeamInfoBuilder setClubColors(String clubColors) {
		this.clubColors = clubColors;
		return this;
	}
	
	public TeamInfoBuilder setVenue(String venue) {
		this.venue = venue;
		return this;
	}
	
	public TeamInfo build() {
		return new TeamInfo(name, address, phone, website, email, founded, clubColors, venue);
	}
}

