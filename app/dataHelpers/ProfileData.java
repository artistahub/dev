package dataHelpers;

import models.MyPhoto;
import models.ProfileImage;
import models.SystemUser1;
import models.Video;

import java.util.List;

public class ProfileData {
    private SystemUser1 systemUser1;
    private ProfileImage profileImage;
    private List<MyPhoto> myphotos;
    private List<Video> myvideos;

    public ProfileData( SystemUser1 systemUser1){
          setSystemUser1(systemUser1);
          setProfileImage( systemUser1.getActiveProfileImage() );
    }


    public ProfileImage getActiveProfileImage( ){

        return  getSystemUser1().getActiveProfileImage();
    }
    public SystemUser1 getSystemUser1() {
        return systemUser1;
    }

    public void setSystemUser1(SystemUser1 systemUser1) {
        this.systemUser1 = systemUser1;
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
        this.myphotos = MyPhoto.getMyPhotos( this.systemUser1.getId());
        return myphotos;
    }

    public void setMyphotos(List<MyPhoto> myphotos) {
        this.myphotos = myphotos;
    }

    public List<Video> getMyvideos() {
        this.myvideos = Video.getMyVideos( this.systemUser1.getId());
        return myvideos;
    }

    public void setMyvideos(List<Video> myvideos) {
        this.myvideos = myvideos;
    }
}
