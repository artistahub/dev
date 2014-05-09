package controllers;

import dataHelpers.SessionUser;
import models.SystemUser1;
import play.data.DynamicForm;
import play.mvc.Result;
import play.mvc.Controller;

import static play.data.Form.form;

/**
 * Created with IntelliJ IDEA.
 * SystemUser1: derectionfree
 * Date: 6/24/13
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogIn extends Controller {

    public static Result index(){
        DynamicForm requestData = form().bindFromRequest();
        String email = requestData.get("email");
        String password = requestData.get( "password" );
        if ( session("currentUserId") == null ){
            if ( email == null || email.isEmpty() || password == null || password.isEmpty() ){
                return  ok( views.html.login.render( "test" ));
            }
            else{
                SystemUser1 systemUser1 = SystemUser1.findUserByEmailAndPass(email, password);
                if ( systemUser1 == null ){
                    return ok( views.html.login.render( " Wrong systemUser1 name or password" ));
                }
                else {
                    SessionUser sessionUser = new SessionUser(systemUser1);
                    String s = play.libs.Json.toJson( sessionUser).toString();
                    session("sessionUser" , s);
                    session("currentUserId", systemUser1.getId());
                    return redirect( routes.Application.home() );
                }
            }
        }
        return redirect( "/" );

    }



}
