

var main = function() {
    $('a[href*=#]').click(function () {
        var threeEms = Number(getComputedStyle(document.body, "").fontSize.match(/(\d*(\.\d*)?)px/)[1]) * 3.5;

        $('html, body').animate({scrollTop: $(this.href).offset().top -100 }, 'slow');

      /*  $('html, body').animate({
            scrollTop: $($(this).attr('href')).offset().top
        }, 500);
        return false;
        */
    });

};






$(document).ready(main);
