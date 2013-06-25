package controllers;

import models.User;
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
                    session("user", user.getId().toString());
                    session("userEmail", user.getEmail());
                    System.out.println( " Logged in user: " + user );
                    System.out.println( "2 else Email: " + email + " Password: " + password );
                    return redirect( "/artistas" );
                }
            }
        }
        return redirect( "/" );

    }



}
