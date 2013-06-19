function Screen(){
    this.wrapperOpener = "<div class=' item wrapper'>";
    this.topBar = "<div class='row topBar'> top Bar</div>";
    this.secondaryBar = "<div class='row secondaryBar'> Secondary Bar</div>";
    this.body = "<div class='row screenBody'> screen body</div>";
    this.footer = "<div class='row screenFooter'> screen footer</div>";
    this.wrapperCloser = "</div>";
    this.screenHtml = this.wrapperOpener + this.topBar + this.secondaryBar + this.body + this.footer; + this.wrapperCloser;
}
Screen.prototype.render = function(){
    return  this.screenHtml;
};
