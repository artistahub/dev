package controllers;

import dataHelpers.ProfileData;
import models.Feed;
import models.MyPhoto;
import models.User;
import org.codehaus.jackson.node.ObjectNode;
import play.*;
import play.api.mvc.*;
import play.mvc.*;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import org.codehaus.jackson.*;
import play.mvc.BodyParser;
import play.libs.Json;

import java.io.File;
import java.util.List;



public class Application extends Controller {

    public static Result index() {
        System.out.print( request().host());
        //File file = Play.application().getFile("/public/index.html");
        //File file2 = Play.application().getFile("/public/@videos.html");
        System.out.println( session("user") );
        if ( session("user") != null){
            return redirect( routes.Application.home() );
        }
        return ok(views.html.index.render(" Professional performers platform "));
    }

    public static Result home(){
        if ( session("user") == null){

            return  redirect( routes.Application.index());
        }
        List<Feed> feeds = Feed.getFeeds();
        String feedsAsJson =  Json.toJson( feeds ).toString();
        return ok( views.html.home.home.render(  feedsAsJson ));
    }

    public static Result signOut(){
        session().remove("user");
        session().remove("userEmail");
        return redirect("/");

    }
   public static Result artistas() {
       List<User> artistas = User.getArtistas();
       ObjectNode allArtistas = Json.newObject();
       allArtistas.put("allArtistas", Json.toJson( artistas ));
       //System.out.print(allArtistas);
       String artistasAsJson = allArtistas.toString();
       //return ok( Json.toJson( allArtistas ));
       String user = session("user");
       //return ok( allArtistas );
       return ok( views.html.artistas.render( artistasAsJson ) );
    }

    public static Result deleteArtista( Long id){
        System.out.println( "User Id: " + id);
         User.deleteArtista( id );
        return redirect( routes.Application.artistas() );
    }

    public static Result newArtista() {
        User.createArtista();
        return redirect( routes.Application.artistas() );
    }
    public static Result byName( String name){
        System.out.print( "Name: ***********" + name );
         List<User> artistas = User.findByName( name );
        ObjectNode allArtistas = Json.newObject();
        allArtistas.put("all artistas Found with Name: " + name, Json.toJson( artistas ));
        System.out.print(allArtistas);
        return ok( Json.toJson( allArtistas ));
    }

   public static Result profile( String userName ){
       User user = User.findUerByUserName( userName );
       ProfileData profileData = new ProfileData( user, user.getProfileImage() );
       //return ok( views.html.profile.profile.render( profileData.toString() ));
       return ok( views.html.profile.profile.render( Json.toJson( profileData ).toString() ));
   }

    //
    public static Result myProfile(){

        if ( session("user") != null){

           return  ok( session("user"));
        }
        else {
           return  ok(" no session -  not logged in");
        }
    }
    //
    public static Result myPhotos(){
        User u = User.findUserById(session("user"));
        if ( session("user") != null){
            List<MyPhoto> myphotos = MyPhoto.getMyPhotos( u.getId());
            return  ok( views.html.profile.myphotos.render( Json.toJson( myphotos ).toString() ));
            //return  ok( session("user"));
        }
        else {
            return  ok(" no session -  not logged in");
        }
    }

    public static Result myVideos(){
        User u = User.findUserById(session("user"));
        if ( session("user") != null){
            List<MyPhoto> myphotos = MyPhoto.getMyPhotos( u.getId());
            return  ok( views.html.profile.myvideos.render( Json.toJson( myphotos ).toString() ));
            //return  ok( session("user"));
        }
        else {
            return  ok(" no session -  not logged in");
        }
    }



    @BodyParser.Of(BodyParser.Json.class)
    public static Result artistasss(){
        ObjectNode result = Json.newObject();
        String name = "Hassan";
        result.put("status", "OK");
        result.put("message", "Hello " + name);
        return ok(result);

    }

}
  

