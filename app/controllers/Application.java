package controllers;

import dataHelpers.ProfileData;
import models.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import java.util.List;

import static play.data.Form.*;


public class Application extends Controller {

    public static Result index() {

        if ( session("sessionUser") != null){
            return redirect( controllers.routes.Application.home() );
        }

        List< UserType > userTypes = UserType.getUserTypes();
        List< AccountType > accountTypes = AccountType.getAccountTypes();
        if ( userTypes.size() <=0 ){
          createUserTypeIfNotExist();
            userTypes = UserType.getUserTypes();
        }
        if ( accountTypes.size() <= 0 ){
           createAccountTypeIfNotExist();
            accountTypes = AccountType.getAccountTypes();
        }

        System.out.println( " User Types ------ " + Json.toJson( UserType.getUserTypes()).toString() );
        System.out.println(" Account Types ------ " + Json.toJson(AccountType.getAccountTypes()).toString());

        List<SystemUser> artistas = SystemUser.getArtistas();
        ObjectNode allArtistas = Json.newObject();
        allArtistas.put("allArtistas", Json.toJson( artistas ));
        String userTypesAsJson = Json.toJson( userTypes ).toString();
        //System.out.print(allArtistas);
        String artistasAsJson = allArtistas.toString();
       // return ok(views.html.index.render( artistasAsJson, userTypes ));
        return ok(views.html.index.render( artistasAsJson, userTypesAsJson));
    }

    public static Result home(){
        if ( session("sessionUser") == null){

            return  redirect( controllers.routes.Application.index());
        }
        List<Feed> feeds = Feed.getFeeds();
        String feedsAsJson =  Json.toJson( feeds ).toString();
        return ok( views.html.home.home.render(  feedsAsJson ));
    }

    public static Result signOut(){
        session().remove("sessionUser");
        session().remove("currentUserId");
        return redirect("/");

    }
   public static Result artistas() {
       List<SystemUser> artistas = SystemUser.getArtistas();
       ObjectNode allArtistas = Json.newObject();
       allArtistas.put("allArtistas", Json.toJson( artistas ));
       //System.out.print(allArtistas);
       String artistasAsJson = allArtistas.toString();
       //return ok( Json.toJson( allArtistas ));
       //return ok( allArtistas );
       return ok( views.html.artistas.render( artistasAsJson ) );
    }

    public static Result deleteArtista( Long id){
        System.out.println( "SystemUser Id: " + id);
         SystemUser.deleteArtista(id);
        return redirect( routes.Application.artistas() );
    }

    /*
    public static Result newArtista() {
        SystemUser.createArtista();
        return redirect( routes.Application.artistas() );
    }
    */
    public static Result byName( String name){
        List<SystemUser> artistas = SystemUser.findByName(name);
        ObjectNode allArtistas = Json.newObject();
        allArtistas.put("all artistas Found with Name: " + name, Json.toJson( artistas ));
        return ok( Json.toJson( allArtistas ));
    }

    public static Result searchArtistas( String q ){
        List<SystemUser> artistas = SystemUser.findByName(q);
        ObjectNode searchResult = Json.newObject();
        searchResult.put( "searchResult", Json.toJson( artistas ));
        return ok( Json.toJson( searchResult ));
    }

   public static Result profile( String userName ){
       SystemUser systemUser = SystemUser.findUerByUserName(userName);
       ProfileData profileData = new ProfileData(systemUser);
       //return ok( views.html.profile.profile.render( profileData.toString() ));
       return ok( views.html.profile.profile.render( Json.toJson( profileData ).toString() ));
   }

    //
    public static Result myProfile(){

        if ( session("sessionUser") != null){

           return  ok( session("user"));
        }
        else {
           return  ok(" no session -  not logged in");
        }
    }
    //
    public static Result myPhotos(){
        SystemUser u = SystemUser.findUserById(session("currentUserId"));
        if ( session("sessionUser") != null){
            List<MyPhoto> myphotos = MyPhoto.getMyPhotos( u.getId());
            return  ok( views.html.profile.myphotos.render( Json.toJson( myphotos ).toString() ));
            //return  ok( session("user"));
        }
        else {
            return  ok(" no session -  not logged in");
        }
    }

    public static Result myWidget( String userName){
        SystemUser u = SystemUser.findUerByUserName(userName);
        ProfileData profileData = new ProfileData( u );
        //return ok( views.html.profile.profile.render( profileData.toString() ));

        return  ok( views.html.widget.mywidget.render( Json.toJson( profileData ).toString() ));

    }

    public static Result myVideos(){
        SystemUser u = SystemUser.findUserById(session("currentUserId"));
        if ( session("sessionUser") != null){
            List<Video> myvideos = Video.getMyVideos( u.getId());
            return  ok( views.html.profile.myvideos.render( Json.toJson( myvideos ).toString() ));
            //return  ok( session("user"));
        }
        else {
            return  ok(" no session -  not logged in");
        }
    }

    public static Result addComment(){
        DynamicForm requestData = form().bindFromRequest();
        SystemUser u = SystemUser.findUserById(session("currentUserId"));
        String dataType = requestData.get("dataType");
        String photoId = requestData.get( "dataId");
        String comment = requestData.get("comment");
        System.out.println(" *******  Add Coment");

        if ( dataType.equals("profileImage")){
             System.out.println(" It is profile Image ");
             ProfileImageComment profileImagecomment = new ProfileImageComment( u, comment);
             ProfileImage profileImage = ProfileImage.findMyProfilePhotoById( photoId );
             profileImagecomment.setMyphoto( profileImage );
             profileImagecomment.save();
            return ok( Json.toJson( profileImagecomment ));
        }
        else {
             Comment myPhotoComment = new Comment( u, comment );
             MyPhoto myphoto = MyPhoto.findMyPhotoById( photoId );
             myPhotoComment.setMyphoto( myphoto );
             myPhotoComment.save();
             return ok( Json.toJson( myPhotoComment));
        }

        //return ok( Json.toJson( Comment.getCommentsByMyPhoto( myphotoId )));
        //return ok( Json.toJson( myComment));
    }

    public static Result getComments( String myphotoId){
        MyPhoto myphoto = MyPhoto.findMyPhotoById( myphotoId );
        List <Comment> comments = Comment.getCommentsByMyPhoto( myphotoId);
        return ok( Json.toJson( comments ));

    }
    // Get Comments for profile Images
    public static Result getProfileImageComment( String imageId){
        ProfileImage profileImage = ProfileImage.findMyProfilePhotoById( imageId );
        List <ProfileImageComment> comments = ProfileImageComment.getCommentsByMyProfilePhoto( imageId );
        return ok( Json.toJson( comments ));

    }

    public static void createAccountTypeIfNotExist(){
        new AccountType( "free", "at-00001", "free" ).save();
        new AccountType( "silver", "at-00002", "silver").save();
        new AccountType( "gold", "at-00003", "gold" ).save();
        new AccountType( "platinum", "at-00004", "platinum" ).save();

    }
    public static void createUserTypeIfNotExist(){
        new UserType( "artist", "ut-00001", "an artist / performer" ).save();
        new UserType( "performance", "ut-00002", " a performance / show" ).save();
        new UserType( "festival", "ut-00003", "a festival" ).save();
        new UserType( "theater", "ut-00004", "a theater" ).save();

    }


}
  

