public class StockOrderBooking {
    private volatile Order firstOrder = null;

    //To compare the stocks we have written a code reuse the code logic
    private boolean compareStockTickers(String stockTicker1, String stockTicker2){
        if(stockTicker1.length() !=stockTicker2.length()){
            return false;
        }
        for(int i = 0; i< stockTicker1.length(); i++){
            if(stockTicker1.charAt(i) != stockTicker2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    private boolean compareStockOrderType(String stockOrderType, String expected){
        return stockOrderType.equals(expected);
    }
    public synchronized void addOrder(String stockOrderType, String stockTickerSymbol, int stockQuantity, int stockPrice){
        Order stockOrder = new Order(stockOrderType, stockTickerSymbol, stockQuantity, stockPrice);
        stockOrder.next = firstOrder;
        firstOrder = stockOrder;
        System.out.println("Adding order: " + stockOrderType + " " + stockQuantity + " shares of " + stockTickerSymbol + " at " + stockPrice);
    }

    public synchronized void matchOrder(){

        //Condition to stop if there are no more orders left to match
        while(firstOrder!=null){
            boolean stockMatched = false;
            Order prev = null;
            Order curr = firstOrder;

            //Need to check if current order is not null and there is next order available
            while(curr!=null && curr.next !=null){
                Order nextOrder = curr.next;

                if(compareStockOrderType(curr.stockOrderType, "BUY") && compareStockOrderType(nextOrder.stockOrderType,"SELL") && compareStockTickers(curr.stockTickerSymbol, nextOrder.stockTickerSymbol) && curr.stockPrice >= nextOrder.stockPrice){
                    int stockQuantityMatched = Math.min(curr.stockQuantity, nextOrder.stockQuantity);
                    curr.stockQuantity = curr.stockQuantity - stockQuantityMatched;
                    nextOrder.stockQuantity = nextOrder.stockQuantity - stockQuantityMatched;

                    if(curr.stockQuantity == 0){
                        if(prev!=null){
                            prev.next = curr.next;
                        }else{
                            firstOrder = curr.next;
                        }
                        curr = nextOrder;
                    }

                    if(nextOrder.stockQuantity == 0){
                        curr.next = nextOrder.next;
                    }
                    stockMatched = true;
                }
                prev = curr;
                curr = curr.next;
            }

            if (!stockMatched){
                System.out.println("No Stock match found! :(");
                break;
            }
        }
        System.out.println("All the stock orders are executed! :)");
    }
}
