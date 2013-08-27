package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.EnumMapping;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profileImages")
public class ProfileImage extends Model {

    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    private String url;
   // @OneToOne(cascade = CascadeType.ALL)
    //private User user;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

   /** public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    **/

    @EnumMapping(nameValuePairs="active = a, inactive = i")
    public enum Status {
        active, inactive
    }

    public ProfileImage(String url, String description){
        this.setUrl(url);
        this.setDescription(description);
        this.setDateCreated(new Date());
    }

    @Enumerated(value=EnumType.ORDINAL)
    Status status = Status.active;

    /**
     * Return status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    private static Finder<Long, ProfileImage> find = new Finder<Long, ProfileImage>(Long.class, ProfileImage.class);

    public static List<ProfileImage> getProfileImages() {
        List<ProfileImage> profileImages = Ebean.find(ProfileImage.class).findList();
        System.out.print(">>>>>>> " + profileImages);
        return profileImages;
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
        return "Profileimage: " + " Url : " + getUrl() + " Description: " + getDescription();
    }

}
