package nbadDB;


public class DefaultStocks {

    /**
     * ABC Gas (ABCG)
     */
    public static String ABCG="ABC Gas (ABCG)";
    /**
     * Premium Solar (PSOL)
     */
    public static String PSOL="Premium Solar (PSOL)";
    /**
     * ABC Pharma (ABCP)
     */
    public static String ABCP ="ABC Pharma (ABCP)";
    /**
     * Premium MedTools (PMED)
     */
    public static String PMED="Premium MedTools (PMED)";
    /**
     * America Rail INC(COMR)
     */
    public static String COMR="America Rail INC(COMR)";
    /**
     * City Bus Express INC(CBUS)
     */
    public static String CBUS="City Bus Express INC(CBUS)";


    public static void setDefaultStocks() {

        String name = ABCG;
        Double price = 10.01;
        String category = "Energy";
        AdminStocks.addStock(name, price, category);

        name = PSOL;
        price = 9.09;
        category = "Energy";
        AdminStocks.addStock(name, price, category);


        name = ABCP;
        price = 8.08;
        category = "Healthcare";
        AdminStocks.addStock(name, price, category);

        name = PMED;
        price = 7.07;
        category = "Healthcare";
        AdminStocks.addStock(name, price, category);


        name = COMR;
        price = 8.08;
        category = "Transportation";
        AdminStocks.addStock(name, price, category);

        name = CBUS;
        price = 7.07;
        category = "Transportation";
        AdminStocks.addStock(name, price, category);
    }

}