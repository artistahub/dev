package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.annotation.EnumMapping;
import play.db.ebean.*;


@Entity
@Table(name = "artistas")
public class SystemUser extends Model {

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
    private ProfileImage activeProfileImage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private Address location;
    private Address BillingAddress;
    private Address MailingAddress;
    @Enumerated(value=EnumType.STRING)
    private UserType userType;
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }


    @EnumMapping(nameValuePairs="ARTIST = artist, PERFORMANCE = performance, AGENCY = agency, THEATER = theater, FESTIVAL = festival")
    public enum UserType {
         ARTIST, PERFORMANCE, AGENCY, THEATER, FESTIVAL;

    }

    public SystemUser(String fName, String lName, String email, String pass){
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.password = pass;
        this.setDateCreated( new Date() );
    }

    public ProfileImage getActiveProfileImage() {
        return activeProfileImage;
    }

    public void setActiveProfileImage(ProfileImage activeProfileImage) {
        this.activeProfileImage = activeProfileImage;
    }

    private static Finder<Long, SystemUser> find = new Finder<Long, SystemUser>(Long.class, SystemUser.class);

    public static List<SystemUser> getArtistas() {
        List<SystemUser> artistas = Ebean.find(SystemUser.class).findList();
        System.out.print(">>>>>>> " + artistas);
        return artistas;
    }

    public static void deleteArtista(Long id) {
        SystemUser.getFind().ref(id).delete();
    }

    public static SystemUser findUserById( String id){
        return  Ebean.find( SystemUser.class).where().like( "id", id).findUnique();
    }

    public static List<SystemUser> findByName(String name) {
      //  List<SystemUser> artistas = Ebean.find(SystemUser.class).where().ilike("lastName", "%" + name + "%").findList();
        List<SystemUser> artistas = Ebean.find(SystemUser.class).where(Expr.or( Expr.ilike("firstName", "%" + name + "%"), Expr.ilike("lastName", "%" + name + "%"))).findList();
        return artistas;
    }

    public static SystemUser findUserByEmailAndPass( String email, String pass ){
        return  Ebean.find( SystemUser.class ).where().like( "email", email).eq("password", pass).findUnique();

    }

    public static SystemUser findUerByUserName ( String userName){
        return  Ebean.find( SystemUser.class ).where().like( "userName", userName).findUnique();
    }

    public static void createArtista() {
        SystemUser u = new SystemUser("hassan", "Rais", "email@email.com","1234");
        //System.out.println("\n UserId: **** " + Ebean.nextId(SystemUser.class) + "\n");
       // u.setLastName("Rais");
      //  u.setFirstName("Hassan");
        u.setAge(33);
        System.out.print(" new user Id: " + Ebean.nextId(SystemUser.class));
        u.setMailingAddress(Address.createAddress());
        u.setBillingAddress(Address.createAddress());
        u.save();
    }

    public static Finder<Long, SystemUser> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, SystemUser> find) {
        SystemUser.find = find;
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


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String toString(){

        return " SystemUser: " + getId() + " Name: " + getLastName() + " " + getFirstName() + " Email: " + getEmail();

    }


}