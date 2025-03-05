public class StockOrderBooking {
    private Order head = null;

    //To compare the stocks we have written a code reuse the code logic
    private boolean compareStockTickers(char[] stockTicker1, char[] stockTicker2){
        if(stockTicker1.length !=stockTicker2.length){
            return false;
        }
        for(int i = 0; i< stockTicker1.length; i++){
            if(stockTicker1[i] != stockTicker2[i]){
                return false;
            }
        }
        return true;
    }
    public synchronized void addOrder(char[] stockOrderType, char[] stockTickerSymbol, int stockQuantity, int stockPrice){
        Order stockOrder = new Order(stockOrderType, stockTickerSymbol, stockQuantity, stockPrice);
        stockOrder.next = head;
        head = stockOrder;
    }

    public synchronized void matchOrder(){

    }
}
