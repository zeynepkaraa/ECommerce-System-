import java.util.ArrayList;

// Name: Zeynep Kara
// Student Id: 501102663

public class Cart {
    
    ArrayList<CartItem> ShoppingCart;
    
    
    public Cart(){
    
        this.ShoppingCart = new ArrayList<CartItem>();

    }

    
    public int getSize(String customerId){

        return ShoppingCart.size();
    }
    
    
}
