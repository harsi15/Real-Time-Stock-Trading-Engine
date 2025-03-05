public class StockOrderBooking {
    private Order head = null;
    public synchronized void addOrder(char[] stockOrderType, char[] stockTickerSymbol, int stockQuantity, int stockPrice){
        Order stockOrder = new Order(stockOrderType, stockTickerSymbol, stockQuantity, stockPrice);
        stockOrder.next = head;
        head = stockOrder;
    }

    public synchronized void matchOrder(){

    }
}
