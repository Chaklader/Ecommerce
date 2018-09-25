package Ecommerce.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Chaklader on Sep, 2017
 */
@Entity
public class Role {

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setRoleId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}

