package tn.esprit.spring.dao.entities;
import org.springframework.security.core.GrantedAuthority;
public enum RoleName implements GrantedAuthority {
ADMIN, SUPERADMIN, INVESTOR, CLIENT;
@Override
public String getAuthority() {
return "ROLE_" + name();
}}
