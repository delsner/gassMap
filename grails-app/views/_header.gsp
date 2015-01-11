<nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" style="color:#B40404;">GASS Map</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <!-- NAVIGATION ITEMS LEFT -->
          <ul class="nav navbar-nav">
            <li <g:if test="${controllerName == 'map'}">class="active"</g:if>><g:link controller="map">Home</g:link></li>
          </ul>
          <!-- END NAVIGATION ITEMS LEFT -->
          <!-- NAVIGATION ITEMS RIGHT -->
          <ul class="nav navbar-nav navbar-right">
            <sec:ifLoggedIn>
            <li <g:if test="${controllerName == 'manage'}">class="active"</g:if>><g:link controller="manage">Mein Konto</g:link></li>
            <li><g:link controller="logout">Logout</g:link></li>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
            <li><g:link controller="login">Login</g:link></li>
            </sec:ifNotLoggedIn>
            
          </ul>
          <!-- END NAVIGATION ITEMS RIGHT -->
        </div><!--/.nav-collapse -->
      </div>
    </nav>
