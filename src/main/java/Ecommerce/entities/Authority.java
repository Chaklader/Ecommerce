package Ecommerce.entities;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Chaklader on Sep, 2017
 */
public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
