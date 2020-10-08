package ODL.study.cartAPI.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ODL.study.cartAPI.entity.Cart;
import ODL.study.cartAPI.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Transactional
    void deleteByIdAndCart(long id, Cart cart);

}
