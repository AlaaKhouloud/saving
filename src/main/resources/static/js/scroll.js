/**
 * 
 */

$("button").click(function() {
    $('html,body').animate({
        scrollTop: $(".conn").offset().top},
        'slow');
});