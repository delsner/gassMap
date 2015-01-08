package gassmap
import gassMap.springSecurity.Person

class MapController {
	def springSecurityService
	
    def index() {
		Person per = springSecurityService.currentUser
		
		return [
			per:per
			]
	}
}
