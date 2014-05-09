package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.annotation.EnumMapping;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import play.data.format.Formats;
import play.db.ebean.*;


@Entity
@Table(name = "persons")
public class Person extends Model {

    @Id
    String id = UUID.randomUUID().toString().replaceAll("-","");
    private String firstName;
    private String lastName;
    private String email;
    private String www;
    private String phoneNumber;
    private String cell;
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Sex gender;
    @ManyToMany( cascade = CascadeType.ALL )
    private List< PersonCategory >  categories;
    @ManyToMany( cascade = CascadeType.ALL )
    private List< Organization > organizations;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    private Address billingAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    @UpdatedTimestamp
    private Date updateTime;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<PersonCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<PersonCategory> categories) {
        this.categories = categories;
    }


    public enum Sex{
        Male, Female, Other;
    }

    public Person( String firstName, String lastName, String email){
        setFirstName( firstName );
        setLastName( lastName );
        setEmail( email );
        setCreateTime( new Date() );
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    private static Finder< Long, Person > find = new Finder<Long, Person>( Long.class, Person.class );

    public static List<Person> findPersonByOrganization( String organizationId ){
        List<Person> os = Ebean.find(Person.class).where().eq("organizations.id", organizationId).findList();

        return Ebean.find(Person.class).where().eq("organizations.id", organizationId).findList();
    }

    public static Person findPersonById( String id ){
         return Ebean.find( Person.class ).where().like( "id" , id).findUnique();
    }

    public static List<Person> findPersonsByFirstName( String firstName ){
         List< Person > persons = Ebean.find( Person.class ).where().ilike("firstName", "%" + firstName + "%").findList();
        return persons;
    }

    public static List<Person> findPersonsByLastName( String lastName ){
         List< Person > persons = Ebean.find( Person.class ).where().ilike("lastName", "%" + lastName + "%").findList();
        return persons;
    }

    public static List<Person> findByName(String name) {
        //  List<SystemUser1> artistas = Ebean.find(SystemUser1.class).where().ilike("lastName", "%" + name + "%").findList();
        List<Person> persons = Ebean.find(Person.class).where(Expr.or( Expr.ilike("firstName", "%" + name + "%"), Expr.ilike("lastName", "%" + name + "%"))).findList();
        return persons;
    }

    public static Person findPersonByEmail( String email  ){
        return  Ebean.find( Person.class ).where().like("email", email).findUnique();

    }

    public static Finder<Long, Person > getFind() {
        return find;
    }

    public static void setFind(Finder<Long, Person > find) {
        Person.find = find;
    }


    public String getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddressId() {
        return address;
    }

    public void setAddressId(Address address) {
        this.address = address;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString(){

        return " Person: " + getId() + " Name: " + getLastName() + " " + getFirstName() + " Email: " + getEmail();

    }



}
