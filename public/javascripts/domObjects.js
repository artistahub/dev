function Screen( o ){
    var userName = o.userName || "artista";
    var fullName = o.fullName || "artista";
    if (o.activeProfileImage== null ){
        o.activeProfileImage = {};
        o.activeProfileImage.url = "images/clown.jpg";
    }
    if (o.location == null ){
        o.location = {};
        o.location.city = "Agadir";
        o.location.country = "Morocco";
    }
   // this.wrapperOpener = "<a href='/profile/" +  userName +"'> <div class=' item wrapper'>";
    this.wrapperOpener = '<a href=' + "/profile/" + encodeURI( userName )  +'> <div class="item wrapper">';
    this.topBar = "<div class='row topBar'> <b>" +fullName +"</b></div>";
    this.secondaryBar = "<div class='row secondaryBar'> Secondary Bar</div>";
    this.body = '<div class="row screenBody"><div class="imgContainer"> <img class="" style="width: 100%" src= ' + encodeURI(o.activeProfileImage.url) + '  ></div></div>';
    this.footer = "<div class='row screenFooter'>  <b>" + o.location.city + ", " + o.location.country +  "</b></div>";
    this.wrapperCloser = "</div></a>";
   // this.screenHtml = this.wrapperOpener + this.topBar + this.secondaryBar + this.body + this.footer; + this.wrapperCloser;
    this.screenHtml = this.wrapperOpener + this.topBar + this.body + this.footer; + this.wrapperCloser;
}
Screen.prototype.render = function(){
    return  this.screenHtml;
};

function ProfilePersonalInfo( systemUser ){
   // alert( " from profile p i function" );
    if ( systemUser.location == null ){
        systemUser.location = {};
        systemUser.location.addressText = " not provided";
    }
     this.userFullName = "<div> <h2 class='h'> " + systemUser.fullName + "</h2></div>";
     this.userTitle = " <div> <h4 class='h'> " + systemUser.userType.label + "</h4></div>";
     this.userLocation = " <div> <h5 class='h'> " +  systemUser.location.addressText + "</h5></div>";
     this.html = this.userFullName;
     this.html += this.userTitle;
     this.html += this.userLocation;
     this.html += "<div><p> <strong> Profession:</strong> Human pyramids, Moroccan Tumbling, Chinese pole and Hnd balancing </p></div>";
     return this.html;
}


// Feeds screen
  function FeedsScreen( feed ){
     this.wrapperOpener = '<a class="feed-item" href="/profile/' + feed.systemUser.userName + '" >';
     this.topBar = ' <div class="fc"><div class="padding5px"><div class="row-fluid"><h2 class="h1"> ' + feed.systemUser.fullName + '</h2></div>';
     this.body = '<div class="row-fluid"> <div class="span12"><img style="width: 100%" src="' + feed.url  +'"></div></div></div>';
     this.footer ='<div class="fc-fotter padding5px"> Fotter</div> ';
     this.comment='<div class="fc-comments padding5px"> Comments</div>';
     this.wrapperCloser = "</div></a>";
     this.screenHtml =  this.wrapperOpener + this.topBar + this.body + this.footer + this.comment + this.wrapperCloser;
  }

FeedsScreen.prototype.render = function (){
    return this.screenHtml;
};

var toolBarObjects = { myphotosToolBar : [{ name: "Photos", id : "myvideos-btn", link : "", class: "btn"},
                     {name: "Albums", id : "", link : "",class: "btn"},
                     {name: "upload Photos", id : "myphotos-upload", link : "",class: "btn btn-success"}],
                      myvideosToolBar:  [{ name: "Videos", id : "myvideos-btn", link : "", class: "btn"},
                     {name: "Albums", id : "", link : "",class: "btn"},
                     {name: "upload Videos", id : "myvideos-upload", link : "",class: "btn btn-success"}],
                      editProfileToolBar: [{ name: "Profile", id : "edit-myprofile-btn",link : "",class: "btn btn-success" },
                      { name: "Profession", id : "edit-profession-btn",link : "",class: "btn" },
                      { name: "Preference", id : "edit-profession-btn",link : "",class: "btn" },
                      {name: "Availability", id : "edit-availability-btn",link : "",class: "btn"}]};

var widgetMenuIcons = function( userName ){
       var iconItems = [{ name: 'myphotos',id:'widget-myphotos-btn', icon:'image-icon my-profile-icon', action: '/widget/' + userName, cssClass: 'widget-menu-icon '},
           { name: 'myvideos',id:'widget-myvideos-btn', icon:'video-icon my-profile-icon', action: '/widget/' + userName + '/myvideos/', cssClass: 'widget-menu-icon'}];
     return iconItems;
}

function ToolBar( o ){
    console.log( JSON.stringify( o ));
    this.outer = "<div class='toolbar-outer'>";
    this.inner = "<div class='toolbar-inner'>";
    this.content = "<div class='toolbar-html'>";
    var thisContent = this.content;
    $.each ( o, function( i, e){
         //console.log( " e " + e.name );
         thisContent +=  '<span id="'+ e.id +'" class= "'+ e.class +'"> ' + e.name + '</span>';
    });
    this.content = thisContent + "</div>";
    this.innerCLose = "</div>";
    this.outerCLose = "</div>";
    this.html = function (){
        return this.outer + this.inner + this.content + this.innerCLose + this.outerCLose;
    }

}



ToolBar.prototype.render = function(){
    return this.html();
}
var forms = {};
    forms.uploadMyphotos = "<form method='post' action='/addMyPhotos' enctype='multipart/form-data'>" +
                         "<input type='file' name='myphotos-upload' ><input type='submit' class='btn btn-primary' value='Upload'></form>";
    forms.uploadMyvideos = '<form action="/addVideo" method="post"> <div class="frow"> <label class="flabel" for="videoLink" > Youtube video</label>' +
        ' <input type="text" class="finputText" id="videoLink" name="videoLink"> </div> <div class="frow"> ' +
        '<label class="flabel" for="videoDescription" > Video Description</label>' +
        ' <textarea class="finputText" id="videoDescription" name="videoDescription" rows="5" ></textarea> </div>' +
        ' <div class="frow"> <input class="btn" type="submit" id="saveVideo" value="Save"> </div> </form>';
    forms.submitMyphotoComment =  function( dataId, dataType){
        var f = '<div class="row-fluid relative"> ' +
                '<label class="flabel" for="t" > Comments goes here</label><textarea name="t" id="comment-text-area" class="span12" rows="2" cols="30"></textarea>  </div> <div>' +
                '<input id="do-comment" data-type="' + dataType + '" data-id="'+ dataId +'" type="button" class="btn btn-primary" value="Comment"> </div>';
        return f ;
    }

function DropDownWindow( f ){
    this.wrapper = "<div class='window-wrapper'></div>";
    this.header = "<div class='window-header'> Header</div>";
    this.body = "<div class='window-body'> " + f + "</div>";
    this.footer = "<div class='window-footer'><span class='close-dropDown btn btn-primary'>Close</span></div>";
    this.html = function(){
        return this.header + this.body + this.footer;
    }
}
DropDownWindow.prototype.render = function(){
    return this.html();
}

function CommentObject( o ){
    var commenterObject = o.commenter;
    var commenterUserName = o.commenter.userName;
    var commenterFullName = o.commenter.fullName;
    var comment = o.description;
    var commenterImg = commenterObject.activeProfileImage.url;
   // alert(o.dateCreated);
    //var dateCreated = new Date(o.dateCreated).toString("MM/dd/yyyy HH:mm tt");
    var dateCreated = new Date(o.dateCreated);
   // alert( dateCreated);
   // alert(timeAgo(dateCreated));

    this.wrapper = '<div class="row-fluid comment-row"> <div class="padding5px">';
    this.commenter = '<div class="commenterAvatar"><a target="_parent" href="/profile/' + commenterUserName + '"> <img class="v-center" src="' + commenterImg + '"></div> ';
    this.comment = '<div class="comment span10 "><h5 class="commenter-name">' + commenterFullName + '</h5></a><p>' + comment  + ' </p> </div>';
    this.commnetDate = '<div class="comment-date"> ' + timeAgo(dateCreated) + '</div>';
    this.wrapperCloser = '</div></div>';
    this.html = function(){
        return this.wrapper + this.commenter + this.comment + this.commnetDate + this.wrapperCloser;
    }
}
CommentObject.prototype.render = function(){ return this.html()};

function SearchResultObject( o ){
    var profileImg = o.activeProfileImage.url || 'images/clown.jpg';
    var profileLink = "/profile/" + o.userName;
    this.rowWrapper = "<div class='row-fluid search-result-row'>";
    this.rowContent = " <a href='" + profileLink + "'><div class='search-result-img-div' > <img class='v-center' width='100%' src='" + profileImg + "'></div>" +
                    "<div class='span10'> <p class='title'> " +  o.firstName + " " + o.lastName + "</p></div></a>";
    this.rowWrapperCloser = "</div>";
    this.html = function(){
        return this.rowWrapper + this.rowContent + this.rowWrapperCloser;
    }

}
SearchResultObject.prototype.render = function(){
    return this.html();
}

var showShadow = function(){
    var $document = $(document);
    var $photoMediaFrame = $('#photo-media-frame');
    var $videoMediaFrame = $('#video-media-frame');
    var documentHeight = $document.height();
    var div =  $('<div>').attr('id', 'shadow-box').css({ right: '13px',opacity:.7,'background-color': '#000', width: '100%', height: '100%', position:'fixed',top:0, bottom: 0,'z-index': 2});
    $photoMediaFrame.append( div );
    $videoMediaFrame.append( div );
    $('#shadow-box').click( function(){
        $( this).remove();
        $('body').removeClass('overflow-hidden');
        $photoMediaFrame.remove();
        $videoMediaFrame.remove();
    });
}


var showVideoShadow = function(){
    var $document = $(document);
    var documentHeight = $document.height();
    var div =  $('<div>').attr('id', 'shadow-box').css({'background-color': '#000', width: '100%', height: documentHeight+"px", position:'fixed',top:0, bottom: 0,'z-index':1});
    $('body').append( div );
    $('#shadow-box').click( function(){
        $( this).remove();
        $( "#video-preview").hide();
    });
}

function ucFirstAllWords( str )
{
    var pieces = str.split(" ");
    for ( var i = 0; i < pieces.length; i++ )
    {
        var j = pieces[i].charAt(0).toUpperCase();
        pieces[i] = j + pieces[i].substr(1);
    }
    return pieces.join(" ");
}

function ArtistaForm(){
    this.formFields = { firstName: "First Name", lastName : "Last Name", email : "Email"};
    this.htmlForm = function(){
        var $container = $("<div id='dynamic-create-form'></div>");
        $.each( this.formFields , function( i, item){
            var $frow = $("<div class='frow'></div>");
            var $label = $("<div class='flabel'></div>").text( item );
            var $input = $("<input class='finputText' type='text' name='" + i + "'></input>");
            $frow.append( $label, $input);
            $container.append( $frow );
         });
      return $container;
     }
    this.renderHtml = function(){ return this.htmlForm()};
}
function PerformanceForm( type ){
    this.formFields = { businessName: type + "'s Name" , email : "Email"};
    this.htmlForm = function(){
        var $container = $("<div id='dynamic-create-form'></div>");
        $.each( this.formFields , function( i, item){
            var $frow = $("<div class='frow'></div>");
            var $label = $("<div class='flabel'></div>").text( ucFirstAllWords( item ) );
            var $input = $("<input class='finputText' type='text' name='" + i + "'></input>");
            $frow.append( $label, $input);
            $container.append( $frow );
         });
      return $container;
     }
    this.renderHtml = function(){ return this.htmlForm()};
}


