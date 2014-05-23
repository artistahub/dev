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
    private SystemUser1 systemUser1;
    private String url;
    private String description;
    @Column(columnDefinition = "timestamp")
    private Date dateCreated;

    public Feed( SystemUser1 systemUser1, String url, String description){
        this.setUrl(url);
        this.setDescription(description);
        this.setDateCreated(new Date());
        this.setSystemUser1(systemUser1);
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

    public SystemUser1 getSystemUser1() {
        return systemUser1;
    }

    public void setSystemUser1(SystemUser1 systemUser1) {
        this.systemUser1 = systemUser1;
    }
}
