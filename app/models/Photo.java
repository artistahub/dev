package models;


import com.avaje.ebean.Ebean;
import play.libs.Json;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo extends Document {

    public Photo(SystemUser owner, String title, String url) {
        super(owner, title, url);
    }

    private static Finder<Long, Photo> find = new Finder<Long, Photo>(Long.class, Photo.class);

    public static Photo findPhotoById( String id){
        return  Ebean.find(Photo.class).where().like( "id", id).findUnique();
    }

    public static List<Photo> getPhotosByOwnerId( String id ) {
        // List<MyPhoto> myphotos = Ebean.find(MyPhoto.class).findList();
        List<Photo> photos = Ebean.find(Photo.class).where().ilike("system_user_id", id).findList();
        System.out.print(">>>>>>> " + Json.toJson(photos).toString());
        return photos;
    }
}
