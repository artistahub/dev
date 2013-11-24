
// wait for the document to be loaded
$( document).ready( function(){
    $( 'body').imagesLoaded( function() {
        grayScaleHtmlPage();
    });

    //unGrayScale(); // put the dom's into normal mode no black and white
});

$(function () {


    var $finputText = $('.finputText');
    var $frow = $('.frow');
    var $body = $('body');
        //$finputText.val("");
    $.each($finputText, function (i, element) {
        $(element).val().length > 0 ? $(element).siblings().hide() : $(element).siblings().fadeIn();
    });

    $body.on('click', '.flabel', function () {
        $.each($finputText, function (i, element) {
            $(element).val().length > 0 ? "" : $(element).siblings().fadeIn();
        });
        $(this).hide().siblings().focus();
    });

    $body.on('click', '.finputText', function () {
        $.each($finputText, function (i, element) {
            $(element).val().length > 0 ? "" : $(element).siblings().fadeIn();
        });
        $(this).focus().siblings().hide();
    });

    $body.on('focus', '.finputText', function () {
        $(this).siblings().hide();
    });

    //   @videos
   /* $(document).on('click', '#addVideo', function () {
        $("#addVideoScreen").slideDown();
    });*/

    $(document).on('click', '#savePersonalInfo', function () {
        //  alert( "Start api call" );
        var userName = $("#userName").val();
        var userCity = $("#city").val();
        var userState = $("#state").val();
        var userCountry = $("#country").val();
        var userVideoLink = $("#videoLink").val();
        var userVideoDescription = $("#videoDescription").val();
        var o = {};
        o.userName = userName;
        o.userCity = userCity;
        o.userState = userState;
        o.userCountry = userCountry;
        o.userVideoLink = userVideoLink;
        o.userVideoDescription = userVideoDescription;
       // alert( JSON.stringify( o ));
        ajaxHtml('/addPersonalInfo', "#stepsContentContainer", o);
    });

});



//
function getYouTubeVideoId ( url ){
    var videoId;
    if (url.indexOf('iframe') < 0 && url.indexOf('src') < 0){
        videoId = url.replace(/(?:https?:\/\/)?(?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com(?:\/embed\/|\/v\/|\/watch\?v=))([\w\-]{10,12})\b[?=&\w]*(?!['"][^<>]*>|<\/a>)/ig,'$1');
    }
    else if ( url.indexOf('iframe') < 0 && url.indexOf('src') > 0){
        return; // to be completed
    }
    else {
        var e = $( url );
        videoId =  e.attr('src').replace(/(?:https?:\/\/)?(?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com(?:\/embed\/|\/v\/|\/watch\?v=))([\w\-]{10,12})\b[?=&\w]*(?!['"][^<>]*>|<\/a>)/ig,'$1');
    }
    return videoId;
}

// time ago function
function timeAgo(time){

    switch (typeof time) {
        case 'number': break;
        case 'string': time = +new Date(time); break;
        case 'object': if (time.constructor === Date) time = time.getTime(); break;
        default: time = +new Date();
    }
    var time_formats = [
        [60, 'seconds', 1], // 60
        [120, '1 minute ago', '1 minute from now'], // 60*2
        [3600, 'minutes', 60], // 60*60, 60
        [7200, '1 hour ago', '1 hour from now'], // 60*60*2
        [86400, 'hours', 3600], // 60*60*24, 60*60
        [172800, 'Yesterday', 'Tomorrow'], // 60*60*24*2
        [604800, 'days', 86400], // 60*60*24*7, 60*60*24
        [1209600, 'Last week', 'Next week'], // 60*60*24*7*4*2
        [2419200, 'weeks', 604800], // 60*60*24*7*4, 60*60*24*7
        [4838400, 'Last month', 'Next month'], // 60*60*24*7*4*2
        [29030400, 'months', 2419200], // 60*60*24*7*4*12, 60*60*24*7*4
        [58060800, 'Last year', 'Next year'], // 60*60*24*7*4*12*2
        [2903040000, 'years', 29030400], // 60*60*24*7*4*12*100, 60*60*24*7*4*12
        [5806080000, 'Last century', 'Next century'], // 60*60*24*7*4*12*100*2
        [58060800000, 'centuries', 2903040000] // 60*60*24*7*4*12*100*20, 60*60*24*7*4*12*100
    ];
    var seconds = (+new Date() - time) / 1000,
        token = 'ago', list_choice = 1;

    if (seconds == 0) {
        return 'Just now'
    }
    if (seconds < 0) {
        seconds = Math.abs(seconds);
        token = 'from now';
        list_choice = 2;
    }
    var i = 0, format;
    while (format = time_formats[i++])
        if (seconds < format[0]) {
            if (typeof format[2] == 'string')
                return format[list_choice];
            else
                return Math.floor(seconds / format[2]) + ' ' + format[1] + ' ' + token;
        }
    return time;
}


function grayScaleHtmlPage(){
    $('img').addClass( 'grayscale').css('visibility', 'visible').hover(
        function() {
            $( this ).removeClass( 'grayscale' );
        }, function() {
            $( this ).addClass( 'grayscale');
        }
    );
}
function unGrayScale( ){
    var s = 1000; // 1 second
    $( 'html' ).animate({
        opacity: 1
    }, 5 * s , function(){

        $( 'html').removeClass( 'grayscale' );

    });
}

// set animation - margin top for the main content

function slideDownMainContent(){
    var $topHeaderBar = $('#header-top-bar');
    var $contentWrapper = $('#content-container');
    var marginTop =  $topHeaderBar.height() + 10;
    //alert( marginTop)

    //console.log( $contentWrapper );
    $contentWrapper.css({opacity:0});
    $contentWrapper.animate({
        'margin-top' : marginTop + "px",
        'opacity': 1
    }, 'slow', function(){
        //alert(' done ');
    });

}

function stripTagsFromHtml( input ){
    var $d = $("<div></div>");
    $d.text(input.replace(/(<([^>]+)>)/ig,""))  ;
   return $d.text();
}

