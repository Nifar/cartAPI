package ODL.study.cartAPI.service;

import org.springframework.stereotype.Service;

import ODL.study.cartAPI.dto.CartItemDTO;
import ODL.study.cartAPI.entity.CartItem;
import ODL.study.cartAPI.repository.CartItemRepository;
import ODL.study.cartAPI.service.mapper.CartItemDTOMapper;

@Service
public class CartItemService extends CrudImpl<CartItem, CartItemRepository, CartItemDTO, CartItemDTOMapper> {

}
