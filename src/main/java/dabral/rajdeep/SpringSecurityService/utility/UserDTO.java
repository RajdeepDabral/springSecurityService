package dabral.rajdeep.SpringSecurityService.utility;

import dabral.rajdeep.SpringSecurityService.entities.Role;

import java.util.List;

public class UserDTO {
    private String email;
    private String password;
    private Boolean isActive;
    private Boolean isDeleted;
    private List<Role> roleList;

    public UserDTO(String email, String password, Boolean isActive, Boolean isDeleted, List<Role> roleList) {
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.roleList = roleList;
    }

    public UserDTO() {
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
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", roleList=" + roleList +
                '}';
    }
}
