package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "feeds")
public class Feed extends Model {

    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private String url;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public Feed( User user, String url, String description){
        this.setUrl(url);
        this.setDescription(description);
        this.setDateCreated(new Date());
        this.setUser( user );
    }

    private static Finder<Long, Feed> find = new Finder<Long, Feed>(Long.class, Feed.class);

    public static List<Feed> getFeeds() {
        List<Feed> feeds = Ebean.find(Feed.class).findList();
        System.out.print(" feeds >>>>>>> " + feeds);
        return feeds;
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
