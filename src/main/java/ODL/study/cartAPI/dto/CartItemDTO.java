package ODL.study.cartAPI.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "Product is mandatory")
    String product;

    @NotNull
    @Min(value = 1, message = "Quantity cant'be lower than 1")
    int quantity;

}
