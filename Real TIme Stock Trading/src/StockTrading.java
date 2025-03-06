public class StockTrading {

    //Making threads and calling functions to replicate stock market scenario
    public static void main(String[] args) {
        System.out.println("Starting the Stock Trading Engine...");
        StockOrderBooking stockOrderBooking = new StockOrderBooking();

        //Creating multiple threads and matcher Thread
        Thread stockMarketThread1 = new Thread(() -> stockMarket(stockOrderBooking));
        Thread stockMarketThread2 = new Thread(() -> stockMarket(stockOrderBooking));
        Thread stockMarketThread3 = new Thread(() -> stockMarket(stockOrderBooking));
        Thread matchOrderThread = new Thread(stockOrderBooking::matchOrder);

        stockMarketThread1.start();
        stockMarketThread2.start();
        matchOrderThread.start();
        stockMarketThread3.start();

        try{
            stockMarketThread1.join();
            stockMarketThread3.join();
            stockMarketThread2.join();
            matchOrderThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    //Function to add stock orders
    public static void stockMarket(StockOrderBooking stockOrderBooking) {
        String[] stockTickers = {"V", "T", "C", "F", "GS", "AAPL", "MSFT", "SPX"};

        for (int i = 0; i < 25; i++) {
            String stockOrderType = (i % 2 == 0) ? "BUY" : "SELL";
            String stockTickerSymbol = stockTickers[i % stockTickers.length];
            int stockQuantity = (i % 100) + 1;
            int stockPrice = (i % 300) + 100;

            stockOrderBooking.addOrder(stockOrderType, stockTickerSymbol, stockQuantity, stockPrice);
        }
    }
}