package models;


import com.avaje.ebean.Ebean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "profilealbum")
public class ProfileAlbum extends Album {

    public ProfileAlbum(SystemUser owner, String name, String description) {
        super(owner, name, description);
    }

    private static Finder<Long, ProfileAlbum> find = new Finder<Long, ProfileAlbum>(Long.class, ProfileAlbum.class);

    public static ProfileAlbum findProfileAlbumById( String id){
        return  Ebean.find(ProfileAlbum.class).where().like( "id", id).findUnique();
    }


}
