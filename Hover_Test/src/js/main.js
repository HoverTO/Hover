

var main = function() {

    $('a[href*=#]').click(function () {
        var threeEms = Number(getComputedStyle(document.body, "").fontSize.match(/(\d*(\.\d*)?)px/)[1]) * 3;

        $('html, body').animate({
            scrollTop: $($(this).attr('href')).offset().top - threeEms
        }, 500);
        return false;

    });

    $("#close_button").click(function(){
        $("#message_form").hide();
    });

    $("#contact_user").click(function(){
       $("#message_form").show(500);

    });

    $("#back_button").click(function(){
       $(".match_box").hide();

    });

    $("#profile_button").click(function(){
        $(".main_page").show(500);
    });

    $("#Matches_button").click(function(){
        $(".main_page").hide();
        $(".match_box").show(500);

        var locations = [
            ['Home', -33.890542, 151.274856, 4],
            ['Coogee Beach', -33.923036, 151.259052, 5],
            ['Cronulla Beach', -34.028249, 151.157507, 3],
            ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
            ['Maroubra Beach', -33.950198, 151.259302, 1]
        ];

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 11,
            center: new google.maps.LatLng(-33.92, 151.25),
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            zoomControl: true
        });

        var infowindow = new google.maps.InfoWindow();

        var marker, i;

        for (i = 0; i < locations.length; i++) {
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                map: map
            });

            google.maps.event.addListener(marker, 'click', (function(marker, i) {
                return function() {
                    infowindow.setContent(locations[i][0]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
        }
    });

    $(".user").click(function(){
       $(".profile_details").toggleClass("profile_details_show");
        $("#back_button").toggle();

    });

};






$(document).ready(main);
