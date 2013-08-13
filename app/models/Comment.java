package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.Date;
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
