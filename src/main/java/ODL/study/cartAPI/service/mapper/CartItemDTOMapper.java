package ODL.study.cartAPI.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ODL.study.cartAPI.dto.CartItemDTO;
import ODL.study.cartAPI.entity.CartItem;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemDTOMapper implements EntityToDTOMapper<CartItemDTO, CartItem, CartItemDTO> {

    ModelMapper mapper = new ModelMapper();

    @Override
    public CartItemDTO toDTO(CartItem entity, Object... args) {
        CartItemDTO dto = mapper.map(entity, CartItemDTO.class);
        dto.setCart_id(entity.getCart().getId());
        return dto;
    }

    @Override
    public CartItem toEntity(CartItemDTO dto, Object... args) {
        return mapper.map(dto, CartItem.class);
    }

}
