package models;

import com.avaje.ebean.annotation.EnumMapping;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "systemaccount")
public class SystemAccount {
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Id
    private String id = UUID.randomUUID().toString().replaceAll("-", "");
    @OneToOne(cascade = CascadeType.ALL)
    private SystemUser systemUser;
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

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
