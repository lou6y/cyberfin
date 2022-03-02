package tn.esprit.spring.services.interfaces;

import tn.esprit.spring.dao.entities.Role;

public interface RoleService {
	Role findByRole (String role);

}
