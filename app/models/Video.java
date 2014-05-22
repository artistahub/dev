package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
public class Video extends Document {
    public Video( SystemUser owner, String title, String url ){
       super(owner, title, url);
    }
}
