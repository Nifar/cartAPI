package ODL.study.cartAPI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ODL.study.cartAPI.entity.Cart;
import ODL.study.cartAPI.entity.CartItem;
import ODL.study.cartAPI.service.CartItemService;
import ODL.study.cartAPI.service.CartService;
import ODL.study.cartAPI.service.mapper.CartDTOMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    CartDTOMapper cartDTOMapper;

    //@formatter:off
    @Test
    public void cartCreateTest() throws Exception {
            mockMvc.perform(post("/carts"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.items").isArray())
            .andExpect(jsonPath("$.items").isEmpty());
    }

    @Test
    public void cartItemWithoutBodyCreateTest() throws Exception {
        Long cartId = cartService.create(new Cart()).getId();
        mockMvc.perform(post(String.format("/carts/%d/items", cartId)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    public void cartItemWithWrongQuantityCreateTest() throws Exception {
        Long cartId = cartService.create(new Cart()).getId();
        mockMvc.perform(post(String.format("/carts/%d/items", cartId)).param("product", "TestProduct")
                .param("quantity", "-1"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").isNotEmpty());
    }
 
    @Test
    public void cartItemWithBlankProductCreateTest() throws Exception {
        Long cartId = cartService.create(new Cart()).getId();
        mockMvc.perform(
                post(String.format("/carts/%d/items", cartId)).param("product", "").param("quantity", "3"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    public void cartItemCreateTest() throws Exception {
        Long cartId = cartService.create(new Cart()).getId();
        String productName = "Test product", quantity = "3";
        mockMvc.perform(
                post(String.format("/carts/%d/items", cartId)).param("product", productName).param("quantity", quantity))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.product").value(productName))
                .andExpect(jsonPath("$.quantity").value(quantity))
                .andExpect(jsonPath("$.cart_id").value(cartId));
    }

    @Test
    public void cartItemWithNotExistCartDeleteTest() throws Exception {
        Long cartItemId = cartItemService
                .create(new CartItem(cartDTOMapper.toEntity(cartService.create(new Cart())), "product", 1)).getId();
        mockMvc.perform(delete(String.format("/carts/999999/items/%d", cartItemId)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    public void cartItemWithNotExistCartItemDeleteTest() throws Exception {
        Long cartId = cartService.create(new Cart()).getId();
        mockMvc.perform(delete(String.format("/carts/%d/items/999999", cartId)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").isNotEmpty());
    }    

    @Test
    public void cartItemDeleteTest() throws Exception {
        Cart cart = cartDTOMapper.toEntity(cartService.create(new Cart()));
        Long cartItemId = cartItemService.create(new CartItem(cart, "product", 1)).getId();
        mockMvc.perform(delete(String.format("/carts/%d/items/%d", cart.getId(), cartItemId)))
        .andExpect(status().isOk());
    }

    @Test
    public void getCartTest() throws Exception {
        Long cartId = cartService.create(new Cart()).getId();
        mockMvc.perform(get(String.format("/carts/%d", cartId)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.items").isArray());
    }

    @Test
    public void getNotExistCartTest() throws Exception {
        mockMvc.perform(get("/carts/999999"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").isNotEmpty());
    }
    //@formatter:on
}
