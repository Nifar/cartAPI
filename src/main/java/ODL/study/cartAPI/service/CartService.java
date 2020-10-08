package ODL.study.cartAPI.service;

import org.springframework.stereotype.Service;

import ODL.study.cartAPI.dto.CartDTO;
import ODL.study.cartAPI.entity.Cart;
import ODL.study.cartAPI.repository.CartRepository;
import ODL.study.cartAPI.service.mapper.CartDTOMapper;

@Service
public class CartService extends CrudImpl<Cart, CartRepository, CartDTO, CartDTOMapper> {

}
