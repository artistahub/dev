package dataHelpers;

import models.MyPhoto;
import models.ProfileImage;
import models.User;

import java.util.List;

public class ProfileData {
    private User user;
    private ProfileImage profileImage;
    private List<MyPhoto> myphotos;

    public ProfileData( User user, List<ProfileImage> profileImages){
          ProfileImage p =  getActiveProfileImage( profileImages );
          setUser( user );
          setProfileImage( profileImage );
    }


    public ProfileImage getActiveProfileImage( List<ProfileImage> profileImages){
        ProfileImage pImg = new ProfileImage("","");
        for (ProfileImage profileImage: profileImages){
             if ( profileImage.getStatus() == ProfileImage.Status.active){
                pImg = profileImage;
             }
        }
        return  pImg;
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

    public List<MyPhoto> getMyphotos() {
        this.myphotos = MyPhoto.getMyPhotos( this.user.getId());
        return myphotos;
    }

    public void setMyphotos(List<MyPhoto> myphotos) {
        this.myphotos = myphotos;
    }
}
