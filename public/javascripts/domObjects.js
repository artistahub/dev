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

