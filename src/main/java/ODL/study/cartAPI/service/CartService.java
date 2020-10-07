package ODL.study.cartAPI.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import ODL.study.cartAPI.dto.CartDTO;
import ODL.study.cartAPI.entity.Cart;
import ODL.study.cartAPI.repository.CartRepository;
import ODL.study.cartAPI.service.mapper.CartDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {

    CartRepository cartRepository;

    CartDTOMapper cartDTOMapper;

    public CartDTO create(Cart cart) {
        return cartDTOMapper.toDTO(cartRepository.saveAndFlush(cart));
    }

    public CartDTO read(Long cartID) throws EntityNotFoundException {
        Cart cart = cartRepository.findById(cartID).orElseThrow(() -> new EntityNotFoundException());
        return cartDTOMapper.toDTO(cart);
    }

    public List<CartDTO> getAll() {
        return cartDTOMapper.toDTOs(cartRepository.findAll());
    }

}
