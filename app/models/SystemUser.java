package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "systemusers")
public class SystemUser extends Model {
    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;
    private String userName;
    @OneToOne(cascade = CascadeType.ALL)
    private Organization organization;
    private Date createTime;
    @Column(columnDefinition = "timestamp")
    private Date updateTime;

    public SystemUser( Person person, String password ){
          setOrganization( null );
          setPerson( person );
          setCreateTime( new Date());
          createSystemAccount( person.getEmail(), password);
    }

    public SystemUser( Organization organization, String password ){
          setPerson( null );
          setOrganization( organization );
          setCreateTime( new Date() );
          createSystemAccount( organization.getEmail(), password );
    }

    private static Finder<Long, SystemUser> find = new Finder<Long, SystemUser>(Long.class, SystemUser.class);

    public static List<SystemUser> getArtistas() {
        List<SystemUser> artistas = Ebean.find(SystemUser.class).findList();
        System.out.print(">>>>>>> " + artistas);
        return artistas;
    }

    public static SystemUser findSystemUserByUserName ( String userName){
        return  Ebean.find( SystemUser.class ).where().like( "userName", userName).findUnique();
    }

    public static SystemUser findUserById( String id){
        return  Ebean.find( SystemUser.class).where().like( "id", id).findUnique();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isPerson(){
        return this.getOrganization() == null && this.getPerson() != null;
    }

    public boolean isOrganization(){
        return this.getPerson() == null && this.getOrganization() != null;
    }

    private SystemAccount createSystemAccount( String email, String password ){
        SystemAccount systemAccount = new SystemAccount( this, email, password );
        systemAccount.save();
        return systemAccount;
    }

    public static List<Photo> getProfilePhotos( String ownerId ){
        Album profileAlbum = Album.findAlbumByOwner( ownerId );
       // System.out.println( "Profile album:  --------- " + profileAlbum);
        List<Photo> photos = profileAlbum.getPhotos();
       // System.out.println( "Photos Profile album:  --------- " + photos );
        return photos;
        }



    public String toString(){
        return " SystemUser: " + getId() + " Name: " + getPerson() + " " + getUserName() + " Email: " ;
    }
}
