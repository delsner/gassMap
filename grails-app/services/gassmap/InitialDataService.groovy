package gassmap

import grails.plugin.geocode.Point
import grails.transaction.Transactional
import gassMap.db.Address
import gassMap.springSecurity.Role
import gassMap.springSecurity.Person
import gassMap.springSecurity.PersonRole

@Transactional
class InitialDataService {
	def grailsApplication
	def geocodingService
	
    def initialize() {
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		
		// Admin anlegen
		def adminUser = Person.findByUsername('admin') ?: new Person( username: 'admin', password: 'admin', email:'admin@admin.de', enabled: true).save(failOnError: true)
		if (!adminUser.authorities.contains(adminRole)) {
			PersonRole.create(adminUser, adminRole, true)
			Address address = new Address(street:'arschstrasse')
			address.save(failOnError: true)
			adminUser.addresses.add(address)
			adminUser.save(flush: true)
			}
		
		
		def file = new File('web-app/sample_initial_dataset.csv')
		file.splitEachLine(',') { row ->
			def testUser = Person.findByUsername(row[4]) ?: new Person ( 
				username: row[4], 
				password: 'chill', 
				email:row[0], 
				enabled: true,
				firstName:row[1],
				lastName:row[2],
				sex:row[3]
				).save(failOnError: true)
			
			if (!testUser.authorities.contains(userRole)) {
			PersonRole.create(testUser, userRole, true)
			Address address1 = new Address(
					street:row[8],
					streetNumber:row[9],
					zipcode:row[10],
					city:row[5],
					country:row[6],
					extension:row[7]
					)
			Point location = geocodingService.getPoint(address1.street + " " + address1.streetNumber + ", " + address1.city + ", " + address1.zipcode + ", " + address1.country)
			if(location)
			{
				address1.longitude=location.longitude
				address1.latitude=location.latitude
			}
			address1.save(failOnError: true)
			testUser.addresses.add(address1)
			testUser.save(flush:true)
			}
		 }
		 
		
		/*// TestUser anlegen
		def testUser = Person.findByUsername('test') ?: new Person( username: 'test', password: 'chill', email:'admin@test.de', enabled: true).save(failOnError: true)
		if (!testUser.authorities.contains(userRole)) {
			PersonRole.create(testUser, userRole, true)
			Address address1 = new Address(
					street:'Christophstrasse',
					streetNumber:'15',
					zipcode:'54290',
					city:'Trier',
					country:'Deutschland'
					)
			address1.save(failOnError: true)
			testUser.addresses.add(address1)
			testUser.save(flush:true)
			}*/
    }
}
