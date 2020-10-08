package ODL.study.cartAPI.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ODL.study.cartAPI.dto.CartDTO;
import ODL.study.cartAPI.dto.CartItemDTO;
import ODL.study.cartAPI.entity.Cart;
import ODL.study.cartAPI.entity.CartItem;
import ODL.study.cartAPI.service.CartItemService;
import ODL.study.cartAPI.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {

    CartItemService cartItemSrvice;

    CartService cartService;

    @PostMapping
    public CartDTO createCart() {
        return cartService.create(new Cart());
    }

    @PostMapping("{cart}/items")
    public CartItemDTO createCartItem(@PathVariable final Cart cart, @RequestParam final String product,
            @RequestParam final int quantity) throws EntityNotFoundException {
        return cartItemSrvice.create(new CartItem(cart, product, quantity));
    }

    @DeleteMapping("{cart}/items/{cartItemId}")
    public void deleteCartItem(@PathVariable final Cart cart, @PathVariable final Long cartItemId) {
        cartItemSrvice.deleteByIdAndCart(cartItemId, cart);
    }

    @GetMapping("{cartId}")
    public CartDTO getCart(@PathVariable final long cartId) {
        return cartService.read(cartId);
    }

}
