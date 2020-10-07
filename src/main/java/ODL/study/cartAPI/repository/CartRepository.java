package ODL.study.cartAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ODL.study.cartAPI.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
