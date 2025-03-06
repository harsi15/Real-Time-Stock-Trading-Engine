//Creating a base Structure of our order class
public class Order {

    int stockQuantity;
    int stockPrice;
    String stockTickerSymbol;
    String stockOrderType;
    volatile Order next;

    public Order(String stockOrderType, String stockTickerSymbol, int stockQuantity, int stockPrice){
        this.stockOrderType = stockOrderType;
        this.stockTickerSymbol = stockTickerSymbol;
        this.stockQuantity = stockQuantity;
        this.stockPrice = stockPrice;
        this.next = null;
        System.out.println("New order added: " + stockOrderType + " " + stockQuantity + " shares of " + stockTickerSymbol + " at " + stockPrice);

    }
}
