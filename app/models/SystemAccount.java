package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "systemaccount")
public class SystemAccount extends Model {
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-", "");
    @OneToOne(cascade = CascadeType.ALL)
    private SystemUser1 systemUser1;
    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.free;
    public enum AccountType {
        free, gold, platinum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getId() {
        return id;
    }

    public SystemUser1 getSystemUser1() {
        return systemUser1;
    }

    public void setSystemUser1(SystemUser1 systemUser1) {
        this.systemUser1 = systemUser1;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
