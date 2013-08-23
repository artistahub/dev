package dataHelpers;

import models.MyPhoto;
import models.ProfileImage;
import models.User;
import models.Video;

import java.util.List;

public class ProfileData {
    private User user;
    private ProfileImage profileImage;
    private List<MyPhoto> myphotos;
    private List<Video> myvideos;

    public ProfileData( User user){
          setUser( user );
          setProfileImage( user.getActiveProfileImage() );
    }


    public ProfileImage getActiveProfileImage( ){

        return  getUser().getActiveProfileImage();
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

    public List<Video> getMyvideos() {
        this.myvideos = Video.getMyVideos( this.user.getId());
        return myvideos;
    }

    public void setMyvideos(List<Video> myvideos) {
        this.myvideos = myvideos;
    }
}
