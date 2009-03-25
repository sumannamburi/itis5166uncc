package nbadDB;


public class DefaultHoldings {

    public static void setDefaultHoldings() {

        String userName = "alicejones";
        String stockName = DefaultStocks.ABCG;
        Integer quantity = 1;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "alicejones";
        stockName = DefaultStocks.PMED;
        quantity = 2;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "alicejones";
        stockName = DefaultStocks.COMR;
        quantity = 3;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "alicejones";
        stockName = DefaultStocks.CBUS;
        quantity = 4;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "bobsmith";
        stockName = DefaultStocks.PSOL;
        quantity = 5;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "bobsmith";
        stockName = DefaultStocks.CBUS;
        quantity = 6;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "bobsmith";
        stockName = DefaultStocks.ABCP;
        quantity = 7;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "bobsmith";
        stockName = DefaultStocks.ABCG;
        quantity = 8;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "carlbrown";
        stockName = DefaultStocks.ABCG;
        quantity = 9;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "carlbrown";
        stockName = DefaultStocks.CBUS;
        quantity = 10;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "carlbrown";
        stockName = DefaultStocks.COMR;
        quantity = 11;
        AdminHoldings.addHolding(userName, stockName, quantity);

        userName = "carlbrown";
        stockName = DefaultStocks.PMED;
        quantity = 12;
        AdminHoldings.addHolding(userName, stockName, quantity);
    }

}