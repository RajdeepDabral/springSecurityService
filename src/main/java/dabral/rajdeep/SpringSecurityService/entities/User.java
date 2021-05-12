package dabral.rajdeep.SpringSecurityService.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String profileImage;
    private Boolean isDeleted;
    private Boolean isActive;
    private String token;
    private Date tokenGenerateTime;


    private List<Address> addressList;

    private List<Role> roleList;

    public User(String username, String password, Boolean isActive, Boolean isDeleted, List<Role> roleList) {
        this.email = username;
        this.password = password;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.roleList = roleList;
    }


    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", isDeleted=" + isDeleted +
                ", isActive=" + isActive +
                ", token='" + token + '\'' +
                ", tokenGenerateTime=" + tokenGenerateTime +
                ", addressList=" + addressList +
                ", roleList=" + roleList +
                '}';
    }
}
