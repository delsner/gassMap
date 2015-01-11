package gassmap
import gassMap.springSecurity.Person
import gassMap.db.Address


class MapController {
	def springSecurityService
	
    def index() {
		Person per = springSecurityService.currentUser
		Address address
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
			strAddress:strAddress
			]
	}
}
