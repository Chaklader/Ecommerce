package Ecommerce.repository;

import Ecommerce.entities.UserPayment;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {

}