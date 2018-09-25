package Ecommerce.repository;

import Ecommerce.entities.Role;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByname(String name);
}
