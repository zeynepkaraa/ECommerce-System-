// Name: Zeynep Kara
// Student Id: 501102663
public class CartItem {
    

    private Product     product;
    private String    productOptions;
    
    public CartItem(Product product, String productOptions ){
    
    this.product = product;
    this.productOptions =productOptions;

    }

    public Product getProduct()
    {
        return product;
    }

    public String getProductOptions(Product product){
       
        return productOptions;
    }

    public void print(){
      
       product.print();
    }
}
