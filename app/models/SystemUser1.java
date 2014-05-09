package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import play.db.ebean.*;


@Entity
@Table(name = "artistas")
public class SystemUser1 extends Model {

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
    private String test;
    @OneToOne(cascade = CascadeType.ALL)
    private UserType userType;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }


    public SystemUser1(String fName, String lName, String email, String pass, UserType userType){
        setFirstName( fName );
        setLastName( lName );
        setEmail( email );
        setPassword( pass );
        setUserType( userType);
        this.setDateCreated( new Date() );
    }

    public ProfileImage getActiveProfileImage() {
        return activeProfileImage;
    }

    public void setActiveProfileImage(ProfileImage activeProfileImage) {
        this.activeProfileImage = activeProfileImage;
    }

    private static Finder<Long, SystemUser1> find = new Finder<Long, SystemUser1>(Long.class, SystemUser1.class);

    public static List<SystemUser1> getArtistas() {
        List<SystemUser1> artistas = Ebean.find(SystemUser1.class).findList();
        System.out.print(">>>>>>> " + artistas);
        return artistas;
    }

    public static void deleteArtista(Long id) {
        SystemUser1.getFind().ref(id).delete();
    }

    public static SystemUser1 findUserById( String id){
        return  Ebean.find( SystemUser1.class).where().like( "id", id).findUnique();
    }

    public static List<SystemUser1> findByName(String name) {
      //  List<SystemUser1> artistas = Ebean.find(SystemUser1.class).where().ilike("lastName", "%" + name + "%").findList();
        List<SystemUser1> artistas = Ebean.find(SystemUser1.class).where(Expr.or( Expr.ilike("firstName", "%" + name + "%"), Expr.ilike("lastName", "%" + name + "%"))).findList();
        return artistas;
    }

    public static SystemUser1 findUserByEmailAndPass( String email, String pass ){
        return  Ebean.find( SystemUser1.class ).where().like( "email", email).eq("password", pass).findUnique();

    }

    public static SystemUser1 findUerByUserName ( String userName){
        return  Ebean.find( SystemUser1.class ).where().like( "userName", userName).findUnique();
    }

   // public static void createArtista() {
        //SystemUser1 u = new SystemUser1("hassan", "Rais", "email@email.com","1234", "artist");
        //System.out.println("\n UserId: **** " + Ebean.nextId(SystemUser1.class) + "\n");
       // u.setLastName("Rais");
      //  u.setFirstName("Hassan");
      //  u.setAge(33);
      //  System.out.print(" new user Id: " + Ebean.nextId(SystemUser1.class));
      //  u.setMailingAddress(Address.createAddress());
       // u.setBillingAddress(Address.createAddress());
       // u.save();
    //}

    public static Finder<Long, SystemUser1> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, SystemUser1> find) {
        SystemUser1.find = find;
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


   /* public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }*/

    public String toString(){

        return " SystemUser1: " + getId() + " Name: " + getLastName() + " " + getFirstName() + " Email: " + getEmail();

    }


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}