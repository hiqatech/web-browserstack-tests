package common.setup;

import static common.setup.AllPages.getGitHubElementSelector;
import static common.setup.AllPages.getYouTubeElementSelector;

public class AllProducts {

    public static String getElementSelector(String elementName)
    {
        if(System.getProperty("product").contains("YouTube"))
            return getYouTubeElementSelector(elementName);
        else if(System.getProperty("product").contains("GitHub"))
            return getGitHubElementSelector(elementName);
        else {System.out.println("Product has not been defined in AllProducts");
            return "NoSuchAProduct";}
    }


}

