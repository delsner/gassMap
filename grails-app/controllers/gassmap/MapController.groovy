package gassmap
import gassMap.springSecurity.Person
import gassMap.db.Address


class MapController {
	def springSecurityService
	
    def index() {
		Person per = springSecurityService.currentUser
		Address address
		if(per){
			address = per.addresses[0]
		}
		else{
			address = null
		}
		return [
			per:per,
			address:address
			]
	}
}
