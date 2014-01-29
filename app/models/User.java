package models;

import java.rmi.server.ExportException;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.annotation.EnumMapping;
import play.db.ebean.*;


@Entity
@Table(name = "artistas")
public class User extends Model {

    @Id
    //private Long id;
    private String id = UUID.randomUUID().toString().replaceAll("-","");
    private String firstName;
    private String lastName;
    private String userName;
    private int age;
    private String password;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private ProfileImage activeProfileImage = new ProfileImage("/images/clown.jpg", "");
   // @OneToMany(cascade = CascadeType.ALL)
   // private List<ProfileImage> profileImages;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private Address location;
    private Address BillingAddress;
    private Address MailingAddress;
    private String test;

    public User( String fName, String lName, String email, String pass){
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.password = pass;
        this.setDateCreated( new Date() );
    }

 //   public List<ProfileImage> getProfileImages( ) {

   //     return profileImages;
   // }

  //  public void setProfileImages(List<ProfileImage> profileImages) {
   //     this.profileImages = profileImages;
   // }

    public ProfileImage getActiveProfileImage() {
        return activeProfileImage;
    }

    public void setActiveProfileImage(ProfileImage activeProfileImage) {
        this.activeProfileImage = activeProfileImage;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @EnumMapping(nameValuePairs="artista=a, performance=p, agency= ag")
    public enum ActorType {
        artista, performance, agency
    }




    @Enumerated(value=EnumType.ORDINAL)
    ActorType actorType = ActorType.artista;

    private static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    public static List<User> getArtistas() {
        List<User> artistas = Ebean.find(User.class).findList();
        System.out.print(">>>>>>> " + artistas);
        return artistas;
    }

    public static void deleteArtista(Long id) {
        User.getFind().ref(id).delete();
    }

    public static User findUserById( String id){
        return  Ebean.find( User.class).where().like( "id", id).findUnique();
    }

    public static List<User> findByName(String name) {
      //  List<User> artistas = Ebean.find(User.class).where().ilike("lastName", "%" + name + "%").findList();
        List<User> artistas = Ebean.find(User.class).where(Expr.or( Expr.ilike("firstName", "%" + name + "%"), Expr.ilike("lastName", "%" + name + "%"))).findList();
        return artistas;
    }

    public static User findUserByEmailAndPass( String email, String pass ){
        return  Ebean.find( User.class ).where().like( "email", email).eq("password", pass).findUnique();

    }

    public static User findUerByUserName ( String userName){
        return  Ebean.find( User.class ).where().like( "userName", userName).findUnique();
    }

    public static void createArtista() {
        User u = new User("hassan", "Rais", "email@email.com","1234");
        //System.out.println("\n UserId: **** " + Ebean.nextId(User.class) + "\n");
       // u.setLastName("Rais");
      //  u.setFirstName("Hassan");
        u.setAge(33);
        System.out.print(" new user Id: " + Ebean.nextId(User.class));
        u.setMailingAddress(Address.createAddress());
        u.setBillingAddress(Address.createAddress());
        u.save();
    }

    public static Finder<Long, User> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, User> find) {
        User.find = find;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @OneToOne(cascade = CascadeType.ALL)
    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }
    @OneToOne(cascade = CascadeType.ALL)
    public Address getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        BillingAddress = billingAddress;
    }
    @OneToOne(cascade = CascadeType.ALL)
    public Address getMailingAddress() {
        return MailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        MailingAddress = mailingAddress;
    }

    public String toString(){

        return " User: " + getId() + " Name: " + getLastName() + " " + getFirstName() + " Email: " + getEmail();

    }

}