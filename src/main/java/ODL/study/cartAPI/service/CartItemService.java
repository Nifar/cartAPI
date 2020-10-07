package ODL.study.cartAPI.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import ODL.study.cartAPI.dto.CartItemDTO;
import ODL.study.cartAPI.entity.CartItem;
import ODL.study.cartAPI.repository.CartItemRepository;
import ODL.study.cartAPI.service.mapper.CartItemDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemService {

    CartItemRepository cartItemRepository;

    CartItemDTOMapper cartItemDTOMapper;

    public CartItemDTO read(Long cartID) throws EntityNotFoundException {
        CartItem cartItem = cartItemRepository.findById(cartID).orElseThrow(() -> new EntityNotFoundException());
        return cartItemDTOMapper.toDTO(cartItem);
    }

    public CartItemDTO create(final CartItem cartItem) {
        return cartItemDTOMapper.toDTO(cartItemRepository.saveAndFlush(cartItem));
    }

}
