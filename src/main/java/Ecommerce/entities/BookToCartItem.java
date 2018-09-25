package Ecommerce.entities;

import javax.persistence.*;

@Entity
public class BookToCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookToCartItemId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    public Long getBookToCartItemId() {
        return bookToCartItemId;
    }

    public void setBookToCartItemId(Long bookToCartItemId) {
        this.bookToCartItemId = bookToCartItemId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}
