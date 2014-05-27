package controllers;
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


    public static Result completeRegistration() throws IOException {
        DynamicForm requestData = form().bindFromRequest();
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");
        String email = requestData.get("email");
        String password = requestData.get("password");
        String userName = requestData.get("userName");
        String userType = requestData.get( "userType" );
        Person p = new Person(  firstName, lastName, email );
        UserType systemUsertype = UserType.findUserTypeByName( userType );
        SystemUser u = new SystemUser( p, password );
       // u.setUserType(SystemUser1.UserType.ARTIST);
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

            ProfileImage profileImage = new ProfileImage( s3File.getUrl().toString(), s3File.name);
            Feed f = new Feed( u, s3File.getUrl().toString() , " Text text...") ;
            f.save();
            //profileImage.setSystemUser1( u );
            profileImage.save();
            //System.out.println( "-----------++++++++++--------- " + profileImage.getId());
           // u.setActiveProfileImage(profileImage);
            u.update();
            SessionUser sessionUser = new SessionUser( u );
            String s = play.libs.Json.toJson( sessionUser).toString();
           // System.out.println( " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Session user: " +  s );
            session("sessionUser" , s);
            session("currentUserId" , u.getId());
            return redirect(routes.Application.index());
        } else {
            System.out.print("Missing file");
            flash("error", "Missing file");
            return redirect(routes.Application.index());
        }
    }
}
