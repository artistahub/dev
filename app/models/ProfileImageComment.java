package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profileimagecomments")
public class ProfileImageComment extends Model {

    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    @OneToOne(cascade = CascadeType.ALL)
    private SystemUser commenter;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private ProfileImage myProfilePhoto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;


    public ProfileImageComment(SystemUser commenter, String comment){
       setCommenter( commenter );
       setDescription( comment );
       setDateCreated( new Date());
    }

    private static Finder<Long, ProfileImageComment> find = new Finder<Long, ProfileImageComment>(Long.class, ProfileImageComment.class);

     public static List<ProfileImageComment> getCommentsByMyProfilePhoto( String myProfilePhotoId ) {
        List<ProfileImageComment> comments = Ebean.find(ProfileImageComment.class).where().ilike("my_profile_photo_id", myProfilePhotoId).orderBy(" dateCreated asc").findList();
        return comments;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SystemUser getCommenter() {
        return commenter;
    }

    public void setCommenter(SystemUser commenter) {
        this.commenter = commenter;
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

    public ProfileImage getMyProfilePhoto() {
        return myProfilePhoto;
    }

    public void setMyphoto(ProfileImage myProfilePhoto) {
        this.myProfilePhoto = myProfilePhoto;
    }
}
