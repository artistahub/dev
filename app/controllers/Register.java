package controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import dataHelpers.SessionUser;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import java.io.IOException;
import java.util.List;

import views.html.*;

import static play.data.Form.*;

public class Register extends Controller {
    public static Result index() {
        DynamicForm requestData = form().bindFromRequest();
        String firstName = requestData.get( "firstName" );
        String lastName = requestData.get( "lastName" );
        String email = requestData.get( "email" );
        String userType = requestData.get( "userType" );
        List< UserType > userTypes = UserType.getUserTypes();
        String userTypesAsJson = Json.toJson( userTypes ).toString();
        return ok( register.render( firstName, lastName, email, userType, userTypesAsJson ) );
    }




    public static Result completeRegistration() throws IOException {
        DynamicForm requestData = form().bindFromRequest();
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");
        String email = requestData.get("email");
        String password = requestData.get("password");
        String userName = requestData.get("userName");
        String userType = requestData.get( "userType" );
        Person p = new Person(  firstName, lastName, email );
        UserType systemUserType = UserType.findUserTypeByName( userType );
        SystemUser u = new SystemUser( p, password, systemUserType );
        u.setUserName( userName );
        u.save();

        // Save the file in AWS
        MultipartFormData b = request().body().asMultipartFormData();
       // System.out.print(b);
        FilePart picture = b.getFile("profileImage");
        if (picture != null) {
            S3File s3File = new S3File();
            s3File.name = picture.getFilename();
            s3File.file = picture.getFile();
            s3File.save();

            //ProfileImage profileImage = new ProfileImage( s3File.getUrl().toString(), s3File.name);
            Album profileAlbum = new Album( u, "Profile album ", " Profile album description ", Album.AlbumType.profile );
            Photo profilePhoto = new Photo( u, "profile photo", s3File.getUrl().toString(), profileAlbum );
            u.setProfilePhoto( profilePhoto );
            Feed feed = new Feed( u, s3File.getUrl().toString() , " Text text...") ;
            feed.save();
            profilePhoto.save();
            //u.setProfileImage( profilePhoto );
            //System.out.println( "-----------++++++++++--------- " + profileImage.getId());
           // u.setActiveProfileImage(profileImage);
            //u.update();
            SessionUser sessionUser = new SessionUser( u );
            //ObjectMapper om = new ObjectMapper();
            //String json = om.writeValueAsString( sessionUser );
            //ObjectNode artistas = Json.newObject();
           // su.put("sessionUsr", Json.toJson( sessionUser ));
            String currentUser = Json.toJson( sessionUser ).toString();
            System.out.println( "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Session user: \n" + currentUser );
            session("sessionUser" , currentUser);
            session("currentUserId" , u.getId());
            return redirect(routes.Application.home());
        } else {
            System.out.print("Missing file");
            flash("error", "Missing file");
            return redirect(routes.Application.index());
        }
    }

    public static Result addVideo() {
        DynamicForm requestData = form().bindFromRequest();
        SystemUser u = SystemUser.findUserById(session("currentUserId"));
        String videoLink = requestData.get("videoLink");
        String videoTitle= requestData.get("videoDescription");
        Video video = new Video( u, videoTitle, videoLink );
        video.setOwner(u);
        video.save();
        return redirect( routes.Application.myVideos());

    }

}
