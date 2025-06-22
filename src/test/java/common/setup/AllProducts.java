package common.setup;

import static common.setup.AllPages.getBankingElementSelector;

public class AllProducts {

    public static String getElementSelector(String elementName)
    {
        if(System.getProperty("product").contains("Banking"))
            return getBankingElementSelector(elementName);
        else {System.out.println("Product has not been defined in AllProducts");
            return "NoSuchAProduct";}
    }


}

