/**
 * Created by Malik on 2016-05-23.
 */

window.onload = function() {
    if (window.jQuery) {
        // jQuery is loaded
        alert("Yeah!");
    } else {
        // jQuery is not loaded
        alert("Doesn't Work");
    }
};

var main_2 = function(){

    $('#test_button').click(function(){
       $(this).hide();


    });

};

$(document).ready(main_2);


