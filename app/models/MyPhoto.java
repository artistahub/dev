package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.Date;
import java.util.*;
import java.util.UUID;

@Entity
@Table(name = "myphoto")
public class MyPhoto extends Model {

    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private String url;
    private String description;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Comment> comments;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private String tag;


    public MyPhoto(String url, String description, User u){
        this.setUser(u);
        this.setUrl(url);
        this.setDescription(description);
        this.setDateCreated(new Date());
    }

    private static Finder<Long, MyPhoto> find = new Finder<Long, MyPhoto>(Long.class, MyPhoto.class);

    public static MyPhoto findMyPhotoById( String id){
        return  Ebean.find( MyPhoto.class).where().like( "id", id).findUnique();
    }

    public static List<MyPhoto> getMyPhotos( String id ) {
       // List<MyPhoto> myphotos = Ebean.find(MyPhoto.class).findList();
        List<MyPhoto> myphotos = Ebean.find(MyPhoto.class).where().ilike("user_id", id).findList();
        System.out.print(">>>>>>> " + Json.toJson( myphotos ).toString());
        return myphotos;
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

    public String toString(){
        return "myphoto: " + " Url : " + getUrl() + " Description: " + getDescription() + "\n";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments( String id ) {
        List<Comment> comments = Ebean.find(Comment.class).where().ilike("myphoto_id", id).findList();
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
