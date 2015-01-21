package gassmap

import gassMap.springSecurity.Person
import gassMap.db.Address

class MapController {
	def springSecurityService
	def grailsApplication
	
    def index() {
		Person per = springSecurityService.currentUser
		Address address
		def x 
		/*//def webRootDir = servletContext.getRealPath("/")
		def file = new File('web-app/sample_initial_dataset.csv')
		println(file)
		List code1 = []
		List code2 = []
		file.splitEachLine(',') { row ->
		   code1.add(row[0])
		   code2.add(row[1])
		}*/
		
		String strAddress = null
		if(per){
			address = per.addresses[0]
			strAddress = address
		}
		else{
			address = null
		}
		
		
		return [
			per:per,
			address:address,
			strAddress:strAddress,
			/*code1:code1,
			code2:code2*/
			]
	}
}
