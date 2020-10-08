package ODL.study.cartAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("cart_id")
    Long cartId;

    @NotNull
    String product;

    @NotNull
    int quantity;

}
