import gassMap.db.Address
import gassMap.springSecurity.Role
import gassMap.springSecurity.Person
import gassMap.springSecurity.PersonRole

class BootStrap {
	
	def init = {
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		
		// Admin anlegen
		def adminUser = Person.findByUsername('admin') ?: new Person( username: 'admin', password: 'admin', email:'admin@admin.de', enabled: true).save(failOnError: true)
		if (!adminUser.authorities.contains(adminRole)) {
			PersonRole.create(adminUser, adminRole, true)
			def address = new Address(street:'arschstrasse').save(failOnError: true)
			adminUser.addresses.add(address)
			adminUser.save(flush: true)
			}
		
		// TestUser anlegen
		def testUser = Person.findByUsername('test') ?: new Person( username: 'test', password: 'chill', email:'admin@test.de', enabled: true).save(failOnError: true)
		if (!testUser.authorities.contains(userRole)) {
			PersonRole.create(testUser, userRole, true)
			address = new Address(
					street:'Christpohstrasse',
					streetNumber:'15',
					zipcode:'54290',
					city:'Trier',
					country:'Deutschland'
					)
			address.save(failOnError: true)
			testUser.addresses.add(address)
			testUser.save(flush:true)
			}
		}
	
    def destroy = {
    }
}
