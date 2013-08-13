$(function () {
    var $finputText = $('.finputText');
    var $frow = $('.frow');
    var $body = $('body');
        $finputText.val("");

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


