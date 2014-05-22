package models;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo extends Document {

    public Photo(SystemUser owner, String title, String url) {
        super(owner, title, url);
    }
}
