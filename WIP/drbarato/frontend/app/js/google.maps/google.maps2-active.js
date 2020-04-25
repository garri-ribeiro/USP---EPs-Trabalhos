
	
function initMap() {
    var geocoder1 = new google.maps.Geocoder();
    setCenter(geocoder1, '04055-110');       
        
}
	  
     
function setCenter(geocoder, address) {
    geocoder.geocode({
        'address': address
    }, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            var map = new google.maps.Map(document.getElementById('map2'), {
                zoom: 16,
                center: results[0].geometry.location
            });
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
            // return null;
        }
    });
}
 

