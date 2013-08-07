package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "myphoto")
public class MyPhoto extends Model {

    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    @OneToOne(cascade = CascadeType.ALL)
    private User u;
    private String url;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public MyPhoto(String url, String description, User u){
        this.setU( u );
        this.setUrl(url);
        this.setDescription(description);
        this.setDateCreated(new Date());
    }

    private static Finder<Long, MyPhoto> find = new Finder<Long, MyPhoto>(Long.class, MyPhoto.class);

    public static List<MyPhoto> getMyPhotos( String id ) {
       // List<MyPhoto> myphotos = Ebean.find(MyPhoto.class).findList();
        List<MyPhoto> myphotos = Ebean.find(MyPhoto.class).where().ilike("u_id", id).findList();
        System.out.print(">>>>>>> " + myphotos);
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
        return "myphoto: " + " Url : " + getUrl() + " Description: " + getDescription();
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
}
