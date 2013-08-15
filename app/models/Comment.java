package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment extends Model {

    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    @OneToOne(cascade = CascadeType.ALL)
    private User commenter;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private MyPhoto myphoto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;


    public Comment( User commenter, String comment){
       setCommenter( commenter );
       setDescription( comment );
       setDateCreated( new Date());
    }

    private static Finder<Long, Comment> find = new Finder<Long, Comment>(Long.class, Comment.class);

     public static List<Comment> getCommentsByMyPhoto( String myphotoId ) {
        List<Comment> comments = Ebean.find(Comment.class).where().ilike("myphoto_id", myphotoId).orderBy(" dateCreated asc").findList();
        return comments;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
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

    public MyPhoto getMyphoto() {
        return myphoto;
    }

    public void setMyphoto(MyPhoto myphoto) {
        this.myphoto = myphoto;
    }
}
