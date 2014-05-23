package dataHelpers;

import models.*;

import java.util.List;

public class ProfileData {
    private SystemUser systemUser;
    private ProfileImage profileImage;
    private List<MyPhoto> myphotos;
    private List<Video> myvideos;

    public ProfileData( SystemUser systemUser){
          setSystemUser(systemUser);
          //setProfileImage( systemUser.getActiveProfileImage() );
    }


    //public ProfileImage getActiveProfileImage( ){

        //return  getSystemUser().getActiveProfileImage();
  //  }
    public SystemUser getSystemUser1() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public String toString(){
        return "Profile Data: " + getSystemUser1() + getProfileImage();
    }

    public List<MyPhoto> getMyphotos() {
        this.myphotos = MyPhoto.getMyPhotos( this.systemUser.getId());
        return myphotos;
    }

    public void setMyphotos(List<MyPhoto> myphotos) {
        this.myphotos = myphotos;
    }

    public List<Video> getMyvideos() {
        this.myvideos = Video.getVideosByOwnerId( this.systemUser.getId());
        return myvideos;
    }

    public void setMyvideos(List<Video> myvideos) {
        this.myvideos = myvideos;
    }
}
