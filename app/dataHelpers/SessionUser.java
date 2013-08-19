package dataHelpers;

import models.Address;
import models.ProfileImage;
import models.User;

/**
 * Created with IntelliJ IDEA.
 * User: derectionfree
 * Date: 8/18/13
 * Time: 12:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class SessionUser {

    private String userName ;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private ProfileImage activeProfileImage;
    private Address location;

    public SessionUser( User user){
       setUserName( user.getUserName());
       setFirstName( user.getFirstName());
       setLastName( user.getLastName());
       setFullName( getFirstName() + " " + getLastName());
       setEmail( user.getEmail());
       setActiveProfileImage( user.getActiveProfileImage());
       setLocation( user.getLocation());
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfileImage getActiveProfileImage() {
        return activeProfileImage;
    }

    public void setActiveProfileImage(ProfileImage activeProfileImage) {
        this.activeProfileImage = activeProfileImage;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }
}
