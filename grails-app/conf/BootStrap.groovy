import gassmap.InitialDataService;

class BootStrap {
	
	def grailsApplication
	
	def init = { servletContext ->
		
		def app = grailsApplication.getMainContext()
		app.initialDataService.initialize()
		
		}
	
    def destroy = {
    }
}
