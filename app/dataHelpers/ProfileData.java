package dataHelpers;

import models.Address;
import models.Photo;
import models.SystemUser;
import models.Video;

import java.util.List;

public class ProfileData {
    private SystemUser systemUser;
    private Photo profileImage;
    private List<Photo> photos;
    private List<Video> videos;
    private Address location;

    public ProfileData( SystemUser systemUser){
        setSystemUser(systemUser);
        setProfileImage( systemUser.getActiveProfileImage() );
        setLocation( systemUser.getLocation() );
    }


    //public ProfileImage getActiveProfileImage( ){

        //return  getSystemUser().getActiveProfileImage();
  //  }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }


    public Photo getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Photo profileImage) {
        this.profileImage = profileImage;
    }

    public String toString(){
        return "Profile Data: " + getSystemUser() + " - "+ getProfileImage();
    }

    public List<Photo> getPhotos() {
        this.photos = Photo.getPhotosByOwnerId(this.systemUser.getId());
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Video> getVideos() {
        this.videos = Video.getVideosByOwnerId( this.systemUser.getId());
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }
}
