package controllers;
import models.*;
import dataHelpers.SessionUser;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import java.io.IOException;
import views.html.*;

import static play.data.Form.*;

public class Register extends Controller {
    public static Result index() {
        DynamicForm requestData = form().bindFromRequest();
        String firstName = requestData.get( "firstName" );
        String lastName = requestData.get( "lastName" );
        String email = requestData.get( "email" );
        String userType = requestData.get( "userType" );
        return ok( register.render( firstName, lastName, email, userType ) );
    }


    public static Result addVideo() {
        DynamicForm requestData = form().bindFromRequest();
        SystemUser u = SystemUser.findUserById(session("currentUserId"));
        String videoLink = requestData.get("videoLink");
        String videoDescription = requestData.get("videoDescription");
        Video video = new Video(videoLink, videoDescription);
        video.setSystemUser(u);
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
        UserType systemUsertype = UserType.findUserTypeByName( userType );
        SystemUser u = new SystemUser(firstName, lastName, email,  password, systemUsertype);
       // u.setUserType(SystemUser.UserType.ARTIST);
        u.setUserName( firstName + "." + lastName );
        u.setLocation(Address.createAddress());
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
            //profileImage.setSystemUser( u );
            profileImage.save();
            //System.out.println( "-----------++++++++++--------- " + profileImage.getId());
            u.setActiveProfileImage(profileImage);
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
