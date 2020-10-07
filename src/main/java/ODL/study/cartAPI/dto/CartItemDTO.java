package ODL.study.cartAPI.dto;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDTO {

    @NotNull
    Long id;

    @NotNull
    Long cart_id;

    @NotNull
    String product;

    @NotNull
    int quantity;

}
