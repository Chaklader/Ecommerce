package Ecommerce.service.impl;


import Ecommerce.entities.*;
import Ecommerce.repository.BookToCartItemRepository;
import Ecommerce.repository.CartItemRepository;
import Ecommerce.service.def.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookToCartItemRepository bookToCartItemRepository;


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }


    @Transactional(rollbackFor = Exception.class)
    public CartItem updateCartItem(CartItem cartItem) {

        BigDecimal bigDecimal = new BigDecimal(cartItem.getBook()
                .getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));

        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        cartItem.setSubtotal(bigDecimal);

        cartItemRepository.save(cartItem);

        return cartItem;
    }


    /*
    * add the book to the cart item for the particular user
    * */
    @Transactional(rollbackFor = Exception.class)
    public CartItem addBookToCartItem(Book book, User user, int qty) {

        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

        for (CartItem cartItem : cartItemList) {
            if (book.getBookId() == cartItem.getBook().getBookId()) {
                cartItem.setQty(cartItem.getQty() + qty);
                cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
                cartItemRepository.save(cartItem);
                return cartItem;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setBook(book);

        cartItem.setQty(qty);
        cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
        cartItem = cartItemRepository.save(cartItem);

        BookToCartItem bookToCartItem = new BookToCartItem();
        bookToCartItem.setBook(book);
        bookToCartItem.setCartItem(cartItem);
        bookToCartItemRepository.save(bookToCartItem);

        return cartItem;
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public CartItem findById(Long id) {
        return cartItemRepository.findOne(id);
    }


    @Transactional(rollbackFor = Exception.class)
    public void removeCartItem(CartItem cartItem) {

        bookToCartItemRepository.deleteByCartItem(cartItem);
        cartItemRepository.delete(cartItem);
    }


    @Transactional(rollbackFor = Exception.class)
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<CartItem> findByOrder(Order order) {
        return cartItemRepository.findByOrder(order);
    }
}
