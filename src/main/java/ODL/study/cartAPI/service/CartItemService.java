package ODL.study.cartAPI.service;

import org.springframework.stereotype.Service;

import ODL.study.cartAPI.dto.CartItemDTO;
import ODL.study.cartAPI.entity.Cart;
import ODL.study.cartAPI.entity.CartItem;
import ODL.study.cartAPI.repository.CartItemRepository;
import ODL.study.cartAPI.service.mapper.CartItemDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemService extends CrudImpl<CartItem, CartItemRepository, CartItemDTO, CartItemDTOMapper> {

    CartItemRepository repository;

    public void deleteByIdAndCart(final long id, final Cart cart) {
        repository.deleteByIdAndCart(id, cart);
    }

}
