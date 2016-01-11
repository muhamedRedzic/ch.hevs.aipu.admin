package ch.hevs.aipu.admin.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by pok on 11.01.2016.
 */
@ManagedBean(name = "UserBean")
@SessionScoped
public class UserBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private String username;
    private String pwd;

    private String message;

    private String refUsername;
    private String refPwd;

    private boolean logged = false;

    public UserBean(){
        this.refUsername = "admin";
        this.refPwd = "123";
    }

    public boolean isLogged() {
        return logged;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean login()
    {
        if(this.refUsername.equals(this.username)){

            if(this.refPwd.equals(this.pwd))
            {
                message = "";
                logged = true;
                return true;
            }
            else {
                pwd = "";
                message = "Wrong password !";
            }
        }else {
            username = "";
            message = "Wrong username !";
        }
        return false;
    }

    public boolean logout(){
        this.logged = false;
        return true;
    }
}
