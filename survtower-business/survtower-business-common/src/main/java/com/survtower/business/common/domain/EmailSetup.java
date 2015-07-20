package com.survtower.business.common.domain;

import com.survtower.business.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@Table(name = "email_setup")
public class EmailSetup extends BaseEntity {

    @Column(name = "smtp_host")
    private String host;
    @Column(name = "smtp_port")
    private String port;
    @Column(name = "smtp_username")
    private String username;
    @Column(name = "smtp_password")
    private String password;

    @Column(name = "mail_smtp_user")
    private String mailSmtpUser;
    @Column(name = "mail_smtp_from")
    private String mailSmtpFrom;
    @Column(name = "mail_smtp_local_host")
    private String mailSmtpLocalHost;
    @Column(name = "mail_smtp_auth")
    private String mailSmtpAuth;
    @Column(name = "mail_smtp_host")
    private String mailSmtpHost;
    @Column(name = "mail_smtp_start_tls_enable")
    private String mailSmtpStartTlsEnable;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailSmtpUser() {
        return mailSmtpUser;
    }

    public void setMailSmtpUser(String mailSmtpUser) {
        this.mailSmtpUser = mailSmtpUser;
    }

    public String getMailSmtpFrom() {
        return mailSmtpFrom;
    }

    public void setMailSmtpFrom(String mailSmtpFrom) {
        this.mailSmtpFrom = mailSmtpFrom;
    }

    public String getMailSmtpLocalHost() {
        return mailSmtpLocalHost;
    }

    public void setMailSmtpLocalHost(String mailSmtpLocalHost) {
        this.mailSmtpLocalHost = mailSmtpLocalHost;
    }

    public String getMailSmtpAuth() {
        return mailSmtpAuth;
    }

    public void setMailSmtpAuth(String mailSmtpAuth) {
        this.mailSmtpAuth = mailSmtpAuth;
    }

    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    public String getMailSmtpStartTlsEnable() {
        return mailSmtpStartTlsEnable;
    }

    public void setMailSmtpStartTlsEnable(String mailSmtpStartTlsEnable) {
        this.mailSmtpStartTlsEnable = mailSmtpStartTlsEnable;
    }

}
