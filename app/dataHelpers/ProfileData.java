package dataHelpers;

import models.ProfileImage;
import models.User;

/**
 * Created with IntelliJ IDEA.
 * User: derectionfree
 * Date: 6/26/13
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileData {
    private User user;
    private ProfileImage profileImage;

    public ProfileData( User user, ProfileImage profileImage){
          setUser( user );
          setProfileImage( profileImage );
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public String toString(){
        return "Profile Data: " + getUser() + getProfileImage();
    }
}
