package controllers;

import models.*;
import dataHelpers.SessionUser;
import org.apache.commons.mail.*;
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
        String firstName = requestData.get("firstName");
        String lastName = requestData.get( "lastName" );
        String name = requestData.get( "businessName" );
        String email = requestData.get( "email" );
        String userType = requestData.get( "userType" );
        List< UserType > userTypes = UserType.getUserTypes();
        String userTypesAsJson = Json.toJson( userTypes ).toString();
        return ok( register.render( name, firstName, lastName, email, userType, userTypesAsJson ) );
    }




    public static Result completeRegistration() throws IOException, EmailException {
        DynamicForm requestData = form().bindFromRequest();
        String name = requestData.get("businessName");
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");
        String accountEmail = requestData.get("email");
        String password = requestData.get("password");
        String userName = requestData.get("userName");
        String sex = requestData.get("sex");
        System.out.println(" Gender: ---> " + sex.trim() );
        String userType = requestData.get( "userType" );
        String city = requestData.get( "city" );
        String state = requestData.get( "state" );
        String country = requestData.get( "country" );

        Address address = new Address( city ,state, country );
        UserType systemUserType = UserType.findUserTypeByName( userType );
        SystemUser u = null;
        System.out.println( "Name: " + name);
        if ( name != null  ){
             Organization o = new Organization( name, accountEmail );
             o.setAddress( address );
             u = new SystemUser( o, password, systemUserType );
        }
        else {
            Person p = new Person(  firstName, lastName, accountEmail );
            p.setAddressId( address );
            if ( sex.trim().equalsIgnoreCase("female")){
                p.setGender( Person.Sex.Female );
            }
            else if ( sex.trim().equalsIgnoreCase("Male")){
                p.setGender( Person.Sex.Male );
            }
            else {
                p.setGender( Person.Sex.Other );
            }
            u = new SystemUser( p, password, systemUserType );
        }

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
            u.setActiveProfileImage(profilePhoto);
            Feed feed = new Feed( u, s3File.getUrl().toString() , " Text text...") ;
            feed.save();
            profilePhoto.save();

            SessionUser sessionUser = new SessionUser( u );
            System.out.println( "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Session user: \n" + Json.toJson( sessionUser ).toString() );
            session("sessionUser",Json.toJson( sessionUser ).toString());
            session("currentUserId" , u.getId());

            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("artistahub@gmail.com", "acrobat8"));
            email.setSSLOnConnect(true);
            email.setFrom("artistahub@gmail.com");
            email.setSubject("Welcome to ArtistaOne");
            email.setMsg(" Hello " + u.getFullName());
            email.addTo( accountEmail );
            email.send();

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
        String videoTitle= requestData.get("video-title");
        String videoDescription= requestData.get("videoDescription");
        Video video = new Video( u, videoTitle, videoLink );
       // video.setOwner(u);
        video.setDescription( videoDescription );
        video.save();
        return redirect( routes.Application.myVideos());

    }

}
