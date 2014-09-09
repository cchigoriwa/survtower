$(function(){
    $('input[type="text"]').focus(function() {
        $(this).addClass("focus");
    });
 
    $('input[type="text"]').blur(function() {
        $(this).removeClass("focus");
    });
});