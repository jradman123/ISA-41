package model;

public class Address {

	private String streetName;
	private String streetNumber;
	private City city;
	
	public Address() {
		super();
	}
	public Address(String streetName, String streetNumber, City city) {
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
	}
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
	
	
}
