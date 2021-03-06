function Screen( o ){
    var userName = o.userName || "artista";
    var fullName = o.fullName || "artista";
    var profileImageUrl = o.profileImageUrl != null ? o.profileImageUrl : "images/clown.jpg" ;
    //if (o.activeProfileImage== null ){
      //  o.activeProfileImage = {};
     //   o.activeProfileImage.url = "images/clown.jpg";
   // }
    if (o.location == null ){
        o.location = {};
        o.location.city = "Agadir";
        o.location.country = "Morocco";
    }
   // this.wrapperOpener = "<a href='/profile/" +  userName +"'> <div class=' item wrapper'>";
    this.wrapperOpener = '<a href=' + "/profile/" + encodeURI( userName )  +'> <div class="item wrapper">';
    this.topBar = "<div class='row topBar'> <b>" +fullName +"</b></div>";
    this.secondaryBar = "<div class='row secondaryBar'> Secondary Bar</div>";
    this.body = '<div class="row screenBody"><div class="imgContainer"> <img class="" style="width: 100%" src= ' + encodeURI( o.profileImageUrl ) + '  ></div></div>';
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
    var artistaCat = "";
    if ( systemUser.location == null ){
        systemUser.location = {};
        systemUser.location.addressText = "no category";
    }

    if ( systemUser.person ){
        if ( systemUser.person.category == null ){
            systemUser.person.category = {};
            systemUser.person.category.label = " Person Category";

        }
        artistaCat =  systemUser.person.category.label;
    }
    else {
        if ( systemUser.organization.category == null ){
            systemUser.organization.category = {};
            systemUser.organization.category.label = " Organization Category";

        }
        artistaCat =  systemUser.organization.category.label;

    }

     this.userFullName = "<div> <h2 class='h'> " + systemUser.fullName + "</h2></div>";
     this.userTitle = " <div> <h4 class='h artista-type'> " + systemUser.userType.label + "</h4></div>";
     this.userCategory = " <div> <h4 class='h artista-cat'> " + artistaCat + "</h4></div>";
     this.userLocation = " <div> <h5 class='h'> " +  systemUser.location.addressText + "</h5></div>";
     this.html = this.userFullName;
     this.html += this.userTitle;
     this.html += this.userCategory;
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
        '<div class="frow"> <label class="flabel" for="photo-title" > Title</label>' +
        ' <input type="text" class="finputText" id="photo-title" name="photo-title"> </div> ' +
                         "<input type='file' name='myphotos-upload' >" +

        "<input type='submit' class='btn btn-primary' value='Upload'></form>";
    forms.uploadMyvideos = '<form action="/addVideo" method="post"> ' +
        '<div class="frow"> <label class="flabel" for="videoLink" > YouTube Link </label>' +
        ' <input type="text" class="finputText" id="videoLink" name="videoLink"> </div> ' +
        '<div class="frow"> <label class="flabel" for="video-title" > Title</label>' +
        ' <input type="text" class="finputText" id="video-title" name="video-title"> </div> ' +
        '<div class="frow"><label class="flabel" for="videoDescription" > Video Description</label>' +
        ' <textarea class="finputText" id="videoDescription" name="videoDescription" rows="5" ></textarea> </div>' +
        ' <div class="frow"> <input class="btn btn-primary" type="submit" id="saveVideo" value="Save"> </div> ' +
        '<div><span class="span-info">Currently we only support YouTube videos</span></div>' +
        '</form>';
    forms.submitMyphotoComment =  function( dataId, dataType){
        var f = '<div class="row-fluid relative"> ' +
                '<label class="flabel" for="t" > Comments goes here</label><textarea name="t" id="comment-text-area" class="span12" rows="2" cols="30"></textarea>  </div> <div>' +
                '<input id="do-comment" data-type="' + dataType + '" data-id="'+ dataId +'" type="button" class="btn btn-primary" value="Comment"> </div>';
        return f ;
    }

    forms.logInPopUp =  function( dataId, dataType){
        var f = '<div class="f2 "><form method="post" action="/loginToComment"><div class=" header"><div><h1 class="h1" style="padding: 0; color: white; text-shadow: 1px 1px 2px #222"> Sign in </h1></div>' +
            '</div><div class="frow"><div class="flabel " style="display: block;"> Email</div><input class="finputText" type="text" name="email"></div>' +
            '<div class="frow"><div class="flabel "> Password</div><input class="finputText" type="password" name="password"></div>' +
            '<div class="frow"><div class="flabel "> cuttentUrl</div><input class="finputText" type="hidden" name="cuttentUrl" value="'+ document.URL +'"></div>' +
            '<div class="frow"><input class="btn signup-btn" type="submit" value="Sign in to ArtistaOne"></div>' +
            '</form></div>';
        return f ;
    }

function DropDownWindow( f, title ){
    this.wrapper = "<div class='window-wrapper'></div>";
    this.header = "<div class='window-header'> <h4>"+ title +"</h4></div>";
    this.body = "<div class='window-body'> " + f + "</div>";
    this.footer = "<div class='window-footer'><span class='close-dropDown'>close</span></div>";
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
    var commenterImg = commenterObject.profileImageUrl;
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
    var profileImg = o.profileImageUrl || 'images/clown.jpg';
    var profileLink = "/profile/" + o.userName;
    this.rowWrapper = "<div class='row-fluid search-result-row'>";
    this.rowContent = " <a href='" + profileLink + "'><div class='search-result-img-div' > <img class='v-center' width='100%' src='" + profileImg + "'></div>" +
                    "<div class='span10'> <p class='title'> " +  o.fullName + "</p></div></a>";
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

function SignUpPersonForm( firstName, lastName, email, personCategories  ){
   // console.log( personCategories ) ;
    this.formFields = { firstName: { label: "First Name", value : firstName || "", tag : "input", type: "text", name : "firstName","data-req": 1 },
                    lastName : { label: "Last Name", value : lastName || "", tag : "input", type: "text", name: "lastName","data-req": 1 },
                    email : { label: "Email", value : email || "", tag : "input", type: "text", name: "email","data-req": 1 },
                    userName : { label: "User Name", value : firstName && lastName ? firstName + "." + lastName : "", tag : "input", type: "text",  name : "userName","data-req": 1 },
                    category : { label: "Category", value : "", tag : "select", type: "select",  name : "personCategory","data-req": 1 },
                    password: { label: "Password", value : "", tag : "input", type: "password", name: "password","data-req": 1 },
                    city : { label: "City", value : "", tag : "input", type: "text", name : "city" },
                    state : { label: "State", value : "", tag : "input", type: "text", name: "state" },
                    country : { label: "Country", value : "", tag : "input", type: "text", name: "country","data-req": 1 },
                    sex : { label: "Female", value : "female", tag : "input", type: "radio", name: "sex"},
                    sex2 : { label: "Male", value : "male", tag : "input", type: "radio", name: "sex"},
                    profilePhoto : { label: "", value : "", tag : "input", type: "file", name: "profileImage","data-req": 1, header: "Profile Photo"}
                   };
    this.htmlForm = function(){
        var $container = $("<div class='dynamic-create-form' id='person-signup-form'></div>");
        $.each( this.formFields , function( i, item){
            var $frow = $("<div class='frow'></div>");
            var $label = $("<div class='flabel'></div>").text( item.label );
            var $span = $("<span class='red'></span>");
            var req = item["data-req"];
            if ( item.value ){ $label.css("display","none")};
          //  var $input = $("<input class='finputText' type='text' name='" + i + "'></input>");
            if ( item.tag == "input" && item.type != "radio" ){
                var $input = $("<input class='finputText' data-req='"+ req +"'  value='"+ item.value +"' type='" + item.type + "' name='" + item.name + "'></input>");
            }
            else if (  item.type == "radio" ){
                var $input = $("<label class='radio inline'><input value='"+ item.label +"' class='finputText' data-req='"+ req +"' type='" + item.type + "' name='" + item.name + "'></input> "+item.label+"</label>");
                $label = "";
            }
            else if ( item.type == "select" ){
               var $input = $("<select class='finputText signup-select' data-req='"+ req +"' name='" + item.name + "'><option></option></select>");
                if ( personCategories ){
                    $.each( personCategories, function( i, cat ){
                        // console.log( ut );
                        var $e = $('<option>');
                        $e.attr({ 'value' : cat.name , 'data-ref' : cat.reference});
                        $e.text( ucFirstAllWords(cat.label) );

                        $input.append( $e );
                    });

                }



            }
            if (  item.header ){
                console.log( item.header );
                console.log( $input.parent() );
                $input.removeClass("finputText");
                $frow.append("<div><h4>"+ item.header +"</h4></div>");
            }
            $frow.append( $label, $input, $span );
            $container.append( $frow );
        });
        return $container;
       }
     this.footer = $('<div class="frow"><input class="btn signup-btn span12" type="button" value="Complete Registration" id="uploadProfileImage"></div>');
     this.renderHtml = function(){ return this.htmlForm().append(this.footer)};
}
function EditPersonProfileForm( firstName, lastName, email, personCategories  ){
    // console.log( personCategories ) ;
    this.formFields = { firstName: { label: "First Name", value : firstName || "", tag : "input", type: "text", name : "firstName","data-req": 1 },
        lastName : { label: "Last Name", value : lastName || "", tag : "input", type: "text", name: "lastName","data-req": 1 },
        userName : { label: "User Name", value : firstName && lastName ? firstName + "." + lastName : "", tag : "input", type: "text",  name : "userName","data-req": 1 },
        category : { label: "Category", value : "", tag : "select", type: "select",  name : "personCategory","data-req": 1 },
        city : { label: "City", value : "", tag : "input", type: "text", name : "city" },
        state : { label: "State", value : "", tag : "input", type: "text", name: "state" },
        country : { label: "Country", value : "", tag : "input", type: "text", name: "country","data-req": 1 },
        sex : { label: "Female", value : "female", tag : "input", type: "radio", name: "sex"},
        sex2 : { label: "Male", value : "male", tag : "input", type: "radio", name: "sex"},
        profilePhoto : { label: "", value : "", tag : "input", type: "file", name: "profileImage","data-req": 1, header: "Profile Photo"}
    };
    this.htmlForm = function(){
        var $container = $("<div class='dynamic-create-form' id='person-signup-form'></div>");
        $.each( this.formFields , function( i, item){
            var $frow = $("<div class='frow'></div>");
            var $label = $("<div class='flabel'></div>").text( item.label );
            var $span = $("<span class='red'></span>");
            var req = item["data-req"];
            if ( item.value ){ $label.css("display","none")};
            //  var $input = $("<input class='finputText' type='text' name='" + i + "'></input>");
            if ( item.tag == "input" && item.type != "radio" ){
                var $input = $("<input class='finputText' data-req='"+ req +"'  value='"+ item.value +"' type='" + item.type + "' name='" + item.name + "'></input>");
            }
            else if (  item.type == "radio" ){
                var $input = $("<label class='radio inline'><input value='"+ item.label +"' class='finputText' data-req='"+ req +"' type='" + item.type + "' name='" + item.name + "'></input> "+item.label+"</label>");
                $label = "";
            }
            else if ( item.type == "select" ){
                var $input = $("<select class='finputText signup-select' data-req='"+ req +"' name='" + item.name + "'><option></option></select>");
                if ( personCategories ){
                    $.each( personCategories, function( i, cat ){
                        // console.log( ut );
                        var $e = $('<option>');
                        $e.attr({ 'value' : cat.name , 'data-ref' : cat.reference});
                        $e.text( ucFirstAllWords(cat.label) );

                        $input.append( $e );
                    });

                }

            }
            if (  item.header ){
                console.log( item.header );
                console.log( $input.parent() );
                $input.removeClass("finputText");
                $frow.append("<div><h4>"+ item.header +"</h4></div>");
            }
            $frow.append( $label, $input, $span );
            $container.append( $frow );
        });
        return $container;
    }
    this.footer = $('<div class="frow"><input class="btn signup-btn span12" type="submit" value="Save" id="saveProfileDate"></div>');
    this.renderHtml = function(){ return this.htmlForm().append(this.footer)};
}

function SignUpOrganizationForm( name, email, orgCategories  ){
    this.formFields = { name: { label: "Name", value : name || "", tag : "input", type: "text", name : "businessName","data-req": 1 },
        email : { label: "Email", value : email || "", tag : "input", type: "text", name: "email","data-req": 1 },
        userName : { label: "User Name", value : name ? name : "", tag : "input", type: "text",  name : "userName","data-req": 1 },
        category : { label: "Category", value : "", tag : "select", type: "select",  name : "orgCategory","data-req": 1 },
        password: { label: "Password", value : "", tag : "input", type: "password", name: "password","data-req": 1 },
        city : { label: "City", value : "", tag : "input", type: "text", name : "city" },
        state : { label: "State", value : "", tag : "input", type: "text", name: "state" },
        country : { label: "Country", value : "", tag : "input", type: "text", name: "country","data-req": 1 },
        profilePhoto : { label: "", value : "", tag : "input", type: "file", name: "profileImage","data-req": 1, header: "Profile Photo"}
    };
    this.htmlForm = function(){
        var $container = $("<div class='dynamic-create-form' id='org-signup-form'></div>");
        $.each( this.formFields , function( i, item){
            console.log( "i: " + i);
            console.log( "item: " + item.label);
            var $frow = $("<div class='frow'></div>");
            var $label = $("<div class='flabel'></div>").text( item.label );
            var $span = $("<span class='red'></span>");
            var req = item["data-req"];
            if ( item.value ){ $label.css("display","none")};
            //  var $input = $("<input class='finputText' type='text' name='" + i + "'></input>");
            if ( item.tag == "input" && item.type != "radio" ){
                var $input = $("<input class='finputText' data-req='"+ req +"' value='"+ item.value +"' type='" + item.type + "' name='" + item.name + "'></input>");
            }
            else if (  item.type == "radio" ){
                var $input = $("<label class='radio inline'><input class='finputText' data-req='"+ req +"' type='" + item.type + "' name='" + item.name + "'></input> "+ item.label+"</label>");
                $label = "";
            }
            else if ( item.type == "select" ){
                var $input = $("<select class='finputText signup-select' data-req='"+ req +"' name='" + item.name + "'><option></option></select>");
                if ( orgCategories ){
                    $.each( orgCategories, function( i, cat ){
                        // console.log( ut );
                        var $e = $('<option>');
                        $e.attr({ 'value' : cat.name , 'data-ref' : cat.reference});
                        $e.text( ucFirstAllWords(cat.label) );

                        $input.append( $e );
                    });

                }

            }
            if (  item.header ){
               // console.log( item.header );
              //  console.log( $input.parent() );
                $input.removeClass("finputText");
                $frow.append("<div><h4>"+ item.header +"</h4></div>");
            }
            $frow.append( $label, $input, $span);
            $container.append( $frow );
        });
        return $container;
    }
    this.footer = $('<div class="frow"><input class="btn signup-btn span12" type="button" value="Complete Registration" id="uploadProfileImage"></div>');
    this.renderHtml = function(){ return this.htmlForm().append(this.footer)};
}
function EditOrganizationProfileForm( systemUser,  org,  orgCategories ){
    console.log( org);
    this.formFields = { name: { label: "Name", value : org.name || "", tag : "input", type: "text", name : "businessName","data-req": 1 },
        userName : { label: "User Name", value : systemUser.userName ? systemUser.userName : "", tag : "input", type: "text",  name : "userName","data-req": 1 },
        category : { label: "Category", value : org.category.name ? org.category.name : "", tag : "select", type: "select",  name : "orgCategory","data-req": 1 },
        city : { label: "City", value : org.address.city ? org.address.city : "", tag : "input", type: "text", name : "city" },
        state : { label: "State", value : org.address.state ? org.address.state : "", tag : "input", type: "text", name: "state" },
        country : { label: "Country", value : org.address.country ? org.address.country : "", tag : "input", type: "text", name: "country","data-req": 1 },
        profilePhoto : { label: "", value : "", tag : "input", type: "file", name: "profileImage","data-req": 1, header: "Profile Photo"}
    };
    this.htmlForm = function(){
        var $container = $("<div class='dynamic-create-form' id='org-signup-form'></div>");
        $.each( this.formFields , function( i, item){
            console.log( "i: " + i);
            console.log( "item: " + item.label);
            var $frow = $("<div class='frow'></div>");
            var $label = $("<div class='flabel'></div>").text( item.label );
            var $span = $("<span class='red'></span>");
            var req = item["data-req"];
            if ( item.value ){ $label.css("display","none")};
            //  var $input = $("<input class='finputText' type='text' name='" + i + "'></input>");
            if ( item.tag == "input" && item.type != "radio" ){
                var $input = $("<input class='finputText' data-req='"+ req +"' value='"+ item.value +"' type='" + item.type + "' name='" + item.name + "'></input>");
            }
            else if (  item.type == "radio" ){
                var $input = $("<label class='radio inline'><input class='finputText' data-req='"+ req +"' type='" + item.type + "' name='" + item.name + "'></input> "+ item.label+"</label>");
                $label = "";
            }
            else if ( item.type == "select" ){
                var $input = $("<select class='finputText signup-select' data-req='"+ req +"' name='" + item.name + "'><option> </option></select>");
                if ( orgCategories ){
                    $.each( orgCategories, function( i, cat ){
                        // console.log( ut );
                        var $e = $('<option>');
                        $e.attr({ 'value' : cat.name , 'data-ref' : cat.reference});
                        if ( cat.name == item.value ){
                           // alert( "set category selected");
                            $e.attr({ 'selected' : 1});
                        }
                        $e.text( ucFirstAllWords(cat.label) );

                        $input.append( $e );
                    });

                }

            }
            if (  item.header ){
                // console.log( item.header );
                //  console.log( $input.parent() );
                $input.removeClass("finputText");
                $frow.append("<div><h4>"+ item.header +"</h4></div>");
            }
            $frow.append( $label, $input, $span);
            $container.append( $frow );
        });
        return $container;
    }
    this.footer = $('<div class="frow"><input class="btn signup-btn span12" type="submit" value="Save" id="saveProfileDate"></div>');
    this.renderHtml = function(){ return this.htmlForm().append(this.footer)};
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


