package gassMap.db

class Address {
	String street
	String streetNumber
	String extension // relevant additional information such as apartment #
	String city
	String zipcode
	String country
	
    static constraints = {
		street nullable:true
		streetNumber nullable:true
		extension nullable:true// relevant additional information such as apartment #
		city nullable:true
		zipcode nullable:true
		country nullable:true
    }
	
	public String toString () {
		String result = street + " "
		result = result + streetNumber + " "
		//result = result + extension + " "
		result = result + zipcode + " "
		result = result + city + " "
		result = result + country
		return result
	}
}
