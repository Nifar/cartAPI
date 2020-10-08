package ODL.study.cartAPI.dto;

import java.util.List;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDTO {

    @NotNull
    Long id;

    List<CartItemDTO> items;

}
