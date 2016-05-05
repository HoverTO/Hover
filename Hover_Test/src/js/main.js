

var main = function() {
    $('a[href*=#]').click(function () {
        var threeEms = Number(getComputedStyle(document.body, "").fontSize.match(/(\d*(\.\d*)?)px/)[1]) * 3;

        $('html, body').animate({
            scrollTop: $($(this).attr('href')).offset().top - threeEms
        }, 500);
        return false;

    });

};






$(document).ready(main);
