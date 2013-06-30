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

