package controllers;

import dataHelpers.SessionUser;
import models.User;
import play.api.libs.json.Json;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;

import static play.data.Form.form;

/**
 * Created with IntelliJ IDEA.
 * User: derectionfree
 * Date: 6/24/13
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogIn extends Controller {

    public static Result index(){
        DynamicForm requestData = form().bindFromRequest();
        String email = requestData.get("email");
        String password = requestData.get( "password" );
        System.out.println( "1 Email: " + email + " Password: " + password );
        //return ok( views.html.login.render( "test" ));
        if ( session("user") == null ){
            if ( email == null || email.isEmpty() || password == null || password.isEmpty() ){
                System.out.println( "************ email and pass are null *************");
                return  ok( views.html.login.render( "test" ));
            }
            else{
                User user = User.findUserByEmailAndPass( email, password);
                if ( user == null ){
                    return ok( views.html.login.render( " Wrong user name or password" ));
                }
                else {
                    SessionUser sessionUser = new SessionUser( user );
                    String s = play.libs.Json.toJson( sessionUser).toString();
                    session("sessionUser" , s);
                    session("user", user.getId().toString());
                    session("userEmail", user.getEmail());
                    session("userName", user.getUserName());
                    System.out.println( " Logged in user: " + user );
                    System.out.println( "2 else Email: " + email + " Password: " + password );

                    return redirect( routes.Application.home() );
                }
            }
        }
        return redirect( "/" );

    }



}
