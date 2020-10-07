package ODL.study.cartAPI.service.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ODL.study.cartAPI.dto.CartDTO;
import ODL.study.cartAPI.entity.Cart;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartDTOMapper implements EntityToDTOMapper<CartDTO, Cart, CartDTO> {

    ModelMapper mapper = new ModelMapper();
    CartItemDTOMapper cartItemDTOMapper;

    @Override
    public CartDTO toDTO(Cart entity, Object... args) {
        CartDTO dto = mapper.map(entity, CartDTO.class);
        dto.setCarts(entity.getCarts().stream().map(cartItemDTOMapper::toDTO).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public Cart toEntity(CartDTO dto, Object... args) {
        return mapper.map(dto, Cart.class);
    }

}
