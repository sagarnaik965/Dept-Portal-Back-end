package in.cdac.dbswitch;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String tenant="test";

    public CustomAuthenticationToken(Object principal, Object credentials, String tenant) {
        super(principal, credentials);
        this.tenant = "test";
        super.setAuthenticated(false);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, String tenant,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.tenant = "test";
        super.setAuthenticated(true); 
    }

    public String getTenant() {
        return this.tenant;
    }
}