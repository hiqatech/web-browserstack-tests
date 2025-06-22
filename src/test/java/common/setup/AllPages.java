package common.setup;

import products.Banking.Pages.*;

public class AllPages {

    public static String getBankingElementSelector(String elementName)
    {
        if(System.getProperty("activePage").equalsIgnoreCase("Home"))
            return Home.getElementSelector(elementName);
        else if(System.getProperty("activePage").equalsIgnoreCase("Login"))
            return Login.getElementSelector(elementName);
        else if(System.getProperty("activePage").equalsIgnoreCase("UserHome"))
            return UserHome.getElementSelector(elementName);
        else if(System.getProperty("activePage").equalsIgnoreCase("Deposit"))
            return Deposit.getElementSelector(elementName);
        else if(System.getProperty("activePage").equalsIgnoreCase("Transactions"))
            return Transactions.getElementSelector(elementName);
        else {System.out.println( System.getProperty("product") + " " + System.getProperty("activePage") +
                                 " Page has not been defined in the AllPages");
            return "NoSuchAnElement";}
    }

    public static void setAllProductsPageElements(){
        Deposit.setElements();
        Home.setElements();
        Login.setElements();
        Transactions.setElements();
        UserHome.setElements();
    }


}
