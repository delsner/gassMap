package gassMap.springSecurity
import gassMap.db.Address

class Person {

	transient springSecurityService

	String username
	String password

	// springSecurity Variables
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	// personal Data
	String email
	String firstName
	String lastName
	int exchangeYear
	String sex
	String festnetz
	String mobile
	
	// Working Variables
	boolean initialLogin = false // Logged in before?
	boolean mailingList = true // Receive newsletter?
	//	boolean hibernate = false // GRAILS Spring Security, =false
	Date dateCreated // Auto-populated by Grails
	Date dateUpdated // Auto-populated by Grails
	
	// Valid values
	public static final String MALE = "männlich"
	public static final String FEMALE = "weiblich"
	
	static final List SEXs = [
	MALE,
	FEMALE,
	null
	]
	
	List addresses = []
	// START: Relationships
	static hasMany = [
	addresses: Address
	]
	
	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		dateUpdated nullable:true
		dateCreated nullable:true
		festnetz nullable:true
		email nullable:true
		firstName nullable:true
		lastName nullable:true
		sex nullable:true
		mobile nullable:true
		//email blank: false, unique: true
		//firstName blank: false
		//lastName blank: false
		//exchangeYear min: 1950, max: 2050
		//sex (inList: SEXs)
		//addresses minSize: 1, maxSize: 2
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		PersonRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
