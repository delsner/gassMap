<!DOCTYPE html>
<html>
	<head>
		<title>MapPage</title>
	    <asset:stylesheet src="application.css"/>
	    <asset:javascript src="application.js"/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script type="text/javascript"
	      src="http://maps.googleapis.com/maps/api/js?v=3">
	    </script>
	    <g:javascript> 
		function initialize() {
	   	// MapOptions   
		var mapOptions = {
		  zoom:10,
	        mapTypeId: google.maps.MapTypeId.ROADMAP
	      };
	
		// MapInitialize
	      var map = new google.maps.Map(document.getElementById("map_canvas"),
	          mapOptions);
	      var bounds = new google.maps.LatLngBounds();
	      bounds.extend(new google.maps.LatLng(55.058347,15.0418962));
	      bounds.extend(new google.maps.LatLng(47.2701115,5.8663425));
	      map.fitBounds(bounds);
	      
	      // GeoCode
	      var address = "${strAddress}";
	      
	$.ajax({
		url: "http://maps.google.com/maps/api/geocode/json?address=" + address, 
		dataType: "text",
		success: function(data) {
			var json = $.parseJSON(data);
			var lat = json.results[0].geometry.location.lat;
			var lng = json.results[0].geometry.location.lng;
			createMarkers(lat, lng, map);
		}
	});
	
	    }
	    function createMarkers(lat, lng, map) {
	      var markerPosition = new google.maps.LatLng(lat, lng);
		  	var firstMarker = new google.maps.Marker({
			  position: markerPosition,
			  map: map
		  	});
	    }
 		</g:javascript>
	</head>
	<body onload="initialize()">	
		<div id="header">
			<g:render template="/header"/>
		</div>
		<div id="map_canvas" style="width:100%;height:100%;"></div>
	</body>
</html>
