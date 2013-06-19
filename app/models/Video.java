package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "videos")
public class Video extends Model {

    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private String url;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public  Video( String url, String description){
        this.setUrl(url);
        this.setDescription(description);
        this.setDateCreated(new Date());
    }

    private static Finder<Long, Video> find = new Finder<Long, Video>(Long.class, Video.class);

    public static List<Video> getVideos() {
        List<Video> videos = Ebean.find(Video.class).findList();
        System.out.print(">>>>>>> " + videos);
        return videos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
