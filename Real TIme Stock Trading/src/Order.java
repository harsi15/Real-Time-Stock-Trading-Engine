//Creating a base Structure of our order class
public class Order {

    int stockQuantity;
    int stockPrice;
    char[] stockTickerSymbol;
    char[] stockOrderType;

    public Order(char[] stockOrderType, char[] stockTickerSymbol, int stockQuantity, int stockPrice){
        this.stockOrderType = stockOrderType;
        this.stockTickerSymbol = stockTickerSymbol;
        this.stockQuantity = stockQuantity;
        this.stockPrice = stockPrice;
    }
}
