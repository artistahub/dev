$(function () {
    var $finputText = $('.finputText');
    var $frow = $('.frow');
    $finputText.val("");

    $frow.on('click', '.flabel', function () {
        $.each($finputText, function (i, element) {
            $(element).val().length > 0 ? "" : $(element).siblings().fadeIn();
        });
        $(this).hide().siblings().focus();
    });

    $frow.on('click', '.finputText', function () {
        $.each($finputText, function (i, element) {
            $(element).val().length > 0 ? "" : $(element).siblings().fadeIn();
        });
        $(this).focus().siblings().hide();
    });

    $frow.on('focus', '.finputText', function () {
        $(this).siblings().hide();
    });

    //   @videos
    $(document).on('click', '#addVideo', function () {
        $("#addVideoScreen").slideDown();
    });
    $(document).on('click', '#saveVideo', function () {
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
        ajaxHtml('/addVideo', "#stepsContentContainer", o);
    });
    $(document).on('click', '#uploadProfileImage', function () {
        // alert( "Start api call" );
        /// ajaxImageUplaod('/addProfileImage', "#stepsContentContainer");
    });

});
