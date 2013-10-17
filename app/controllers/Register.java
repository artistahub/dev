package controllers;

import dataHelpers.SessionUser;
import models.*;
import org.codehaus.jackson.node.ObjectNode;
import org.h2.util.IOUtils;
import play.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.*;
import play.data.DynamicForm;
import play.mvc.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import org.codehaus.jackson.*;
import play.mvc.BodyParser;
import play.libs.Json;


import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import static play.data.Form.form;

public class Register extends Controller {
    public static Result index() {
        DynamicForm requestData = form().bindFromRequest();
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");
        String email = requestData.get("email");
       // String password = requestData.get("password");
       // User u = new User(firstName, lastName, email,  password);
        //u.setLocation(Address.createAddress());
       // u.save();
       // session("user", u.getId().toString());
        //session("userEmail", email);

        // return redirect( routes.Application.artistas() );
        return ok( register.render( firstName, lastName, email ) );
    }

 public static Result indexOld() {
        //File file = Play.application().getFile("/public/@videos.html");
        DynamicForm requestData = form().bindFromRequest();
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");
        String email = requestData.get("email");
        String password = requestData.get("password");
        User u = new User(firstName, lastName, email,  password);
        //u.setLocation(Address.createAddress());
        u.save();
        session("user", u.getId().toString());
        session("userEmail", email);

        // return redirect( routes.Application.artistas() );
        return ok(views.html.steps.render(session("user")));
    }

    public static Result addVideo() {
        DynamicForm requestData = form().bindFromRequest();
        User u = User.findUserById(session("user"));
        String videoLink = requestData.get("videoLink");
        String videoDescription = requestData.get("videoDescription");
        Video video = new Video(videoLink, videoDescription);
        video.setUser( u );
        video.save();
        //return ok( Json.toJson( video ) );
        //return ok( json + " Name: " + name + " age: " + age );
        //return ok( views.html.f.aza.render( "aza" ) );

       return redirect( routes.Application.myVideos());

    }

    public static Result addPersonalInfo(){
        DynamicForm requestData = form().bindFromRequest();
               String userName = requestData.get("userName");
               String userCity = requestData.get("userCity");
               String userState = requestData.get("userState");
               String userCountry = requestData.get("userCountry");
               User u = User.findUserById(session("user"));
               System.out.println("*******************");
               System.out.println( session("sessionUser"));

               u.setUserName(userName);
               u.setLocation(new Address(userCity, userState, "****", userCountry));
               u.update();
        //return ok( Json.toJson( video ) );
        //return ok( json + " Name: " + name + " age: " + age );
        //return ok( views.html.f.aza.render( "aza" ) );
               //return ok(views.html.f.uplaodProfileImage.render());
        return ok(uplaodProfileImage.render());
    }

    public static Result completeRegistration() throws IOException {
        DynamicForm requestData = form().bindFromRequest();
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");
        String email = requestData.get("email");
        String password = requestData.get("password");
        String userName = requestData.get("userName");
        User u = new User(firstName, lastName, email,  password);
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
            //profileImage.setUser( u );
            profileImage.save();
            //System.out.println( "-----------++++++++++--------- " + profileImage.getId());
            u.setActiveProfileImage(profileImage);
            u.update();
            SessionUser sessionUser = new SessionUser( u );
            String s = play.libs.Json.toJson( sessionUser).toString();
           // System.out.println( " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Session user: " +  s );
            session("sessionUser" , s);
           // IOUtils.copy(is, new FileOutputStream(Play.application().getFile(original)));
            //System.out.print("Logged in User: " +  Json.toJson( u ));
            return redirect(routes.Application.index());
        } else {
            System.out.print("Missing file");
            flash("error", "Missing file");
            return redirect(routes.Application.index());
        }
    }
}
