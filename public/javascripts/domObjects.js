function Screen( o ){
    var userName = o.userName || "artista";
    if (o.profileImage == null ){
        o.profileImage = {};
        o.profileImage.url = "images/clown.jpg";
    }
    if (o.location == null ){
        o.location = {};
        o.location.city = "Agadir";
        o.location.country = "Morocco";
    }
    this.wrapperOpener = "<a href='/profile/" + userName +"'> <div class=' item wrapper'>";
    this.topBar = "<div class='row topBar'> <b>" + o.firstName + " " + o.lastName +"</b></div>";
    this.secondaryBar = "<div class='row secondaryBar'> Secondary Bar</div>";
    this.body = "<div class='row screenBody'><div class='imgContainer'> <img style='width: 100%' src='" + o.profileImage.url +"' alt=''></div></div>";
    this.footer = "<div class='row screenFooter'>  <b>" + o.location.city + ", " + o.location.country +  "</b></div>";
    this.wrapperCloser = "</div></a>";
   // this.screenHtml = this.wrapperOpener + this.topBar + this.secondaryBar + this.body + this.footer; + this.wrapperCloser;
    this.screenHtml = this.wrapperOpener + this.topBar + this.body + this.footer; + this.wrapperCloser;
}
Screen.prototype.render = function(){
    return  this.screenHtml;
};

function ProfilePersonalInfo( user ){
   // alert( " from profile p i function" );
     this.userFullName = "<div> <h2 class='h'> " + user.firstName + " " + user.lastName + "</h2></div>";
     this.userTitle = " <div> <h4 class='h'> Professional Acrobat</h4></div>";
     this.userLocation = " <div> <h5 class='h'> " +  user.location.city + ", " + user.location.state + "</h5></div>";
     this.html = this.userFullName;
     this.html += this.userTitle;
     this.html += this.userLocation;
     this.html += "<div><p> <strong> Profession:</strong> Human pyramids, Moroccan Tumbling, Chinese pole and Hnd balancing </p></div>";
     return this.html;
}


// Feeds screen
  function FeedsScreen( feed ){
     this.wrapperOpener = '<a href="/profile/' + feed.user.userName + '" >';
     this.topBar = ' <div class="fc"><div class="padding5px"><div class="row-fluid"><h2 class="h1"> ' + feed.user.firstName + " " + feed.user.lastName + '</h2></div>';
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
                     {name: "upload Videos", id : "myvideos-upload", link : "",class: "btn btn-success"}]};

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

function DropDownWindow(){
    this.wrapper = "<div class='window-wrapper'></div>";
    this.header = "<div class='window-header'> Header</div>";
    this.body = "<div class='window-body'> <form method='post' action='/addMyPhotos' enctype='multipart/form-data'><input type='file' name='myphotos-upload' ><input type='submit' class='btn btn-primary' value='Upload'></form></div>";
    this.footer = "<div class='window-footer'><span class='close-dropDown btn btn-primary'>Close</span></div>";
    this.html = function(){
        return this.header + this.body + this.footer;
    }
}
DropDownWindow.prototype.render = function(){
    return this.html();
}
