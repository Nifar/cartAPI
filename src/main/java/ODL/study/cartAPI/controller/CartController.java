package ODL.study.cartAPI.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ODL.study.cartAPI.dto.CartDTO;
import ODL.study.cartAPI.dto.CartItemDTO;
import ODL.study.cartAPI.entity.Cart;
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

    @GetMapping("/create")
    public CartDTO createCart() {
        return cartService.create(new Cart());
    }

    @GetMapping("{cartID}/items")
    public CartItemDTO createCartItem(@PathVariable final Long cartID) throws EntityNotFoundException {
        return null;
    }

    @GetMapping
    public List<CartDTO> getCarts() {
        return cartService.getAll();
    }

}
