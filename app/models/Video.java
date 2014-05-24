package models;

import com.avaje.ebean.Ebean;
import play.libs.Json;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "videos")
public class Video extends Document {
    public Video( SystemUser owner, String title, String url ){
       super(owner, title, url);
    }

    private static Finder<Long, Video> find = new Finder<Long, Video>(Long.class, Video.class);

    public static Video findVideoById( String id){
        return  Ebean.find(Video.class).where().like( "id", id).findUnique();
    }

    public static List<Video> getVideosByOwnerId( String id ) {
        // List<MyVideo> myVideos = Ebean.find(MyVideo.class).findList();
        List<Video> Videos = Ebean.find(Video.class).where().ilike("system_user_id", id).findList();
        System.out.print(">>>>>>> " + Json.toJson(Videos).toString());
        return Videos;
    }
}
