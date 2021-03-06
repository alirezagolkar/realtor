/**
 * Handles map operations
 */

// necessary variables
var map;
var infoWindow;


function initialize() {
    var mapCanvas = document.getElementById("mainMap-canvas");
    var myCenter = new google.maps.LatLng(43.746491, -79.415780);
    var mapOptions = {center: myCenter, zoom: 10};
    map = new google.maps.Map(mapCanvas, mapOptions);

    // a new Info Window is created
    infoWindow = new google.maps.InfoWindow();

    // Event that closes the Info Window with a click on the map
    google.maps.event.addListener(map, 'click', function () {
        infoWindow.close();
    });

    // Finally displayMarkers() function is called to begin the markers creation
    displayMarkers();
}
google.maps.event.addDomListener(window, 'load', initialize);


// creating markers with createMarker function
function displayMarkers() {

    // this variable sets the map bounds according to markers position
    var bounds = new google.maps.LatLngBounds();

    var hosts = document.getElementById('hosts').value;
    var hostObjects = JSON.parse(hosts);

    for (var i = 0; i < hostObjects.length; i++) {
        var hostObj = hostObjects[i];
        var latlng = new google.maps.LatLng(hostObj.latitude, hostObj.longitude);
        createMarker(latlng, hostObj);
        // marker position is added to bounds variable
        bounds.extend(latlng);
    }
}

// This function creates each marker and it sets their Info Window content
function createMarker(latlng, hostObj) {
    var marker = new google.maps.Marker({
        map: map,
        position: latlng,
        hostId: hostObj.hostId,
        hostName: hostObj.hostName,
        hostAddress: hostObj.hostAddress,
        hostPhoneNumber: hostObj.hostPhoneNumber
    });


    // This event expects a click on a marker
    // When this event is fired the Info Window content is created
    // and the Info Window is opened.
    google.maps.event.addListener(marker, 'click', function () {
        var link = "/host/hostDetail-" + hostObj.hostId;
        // Creating the content to be inserted in the infowindow
        var iwContent = '<div id="iw_container">' +
            '<div class="iw_title">Name: ' + hostObj.hostName + '</div>' +
            '<div class="iw_content">Address: ' + hostObj.hostAddress + '<br />Telephone: ' +
            hostObj.hostPhoneNumber + '<br /><br />' +
            '<a id="linkUrl" target="_blank" href="' + link + '"/>View</a>' + '</div></div>';

        // including content to the Info Window.
        infoWindow.setContent(iwContent);

        // opening the Info Window in the current map and at the current marker location.
        infoWindow.open(map, marker);
    });

}

function jumpToLocation(hostId, latitude, longitude) {
    var latlng = new google.maps.LatLng(latitude, longitude);
    map.setCenter(latlng);
    infoWindow.close();
}