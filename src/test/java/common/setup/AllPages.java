package common.setup;

public class AllPages {

    public static String getYouTubeElementSelector(String elementName)
    {
        if(System.getProperty("activePage").equalsIgnoreCase("Home"))
            return products.YouTube.Pages.HomePage.getElementSelector(elementName);
        else if(System.getProperty("activePage").equalsIgnoreCase("Search"))
            return products.YouTube.Pages.SearchPage.getElementSelector(elementName);
        else {System.out.println( System.getProperty("product") + " " + System.getProperty("activePage") +
                                 " Page has not been defined in the AllPages");
            return "NoSuchAnElement";}
    }

    public static String getGitHubElementSelector(String elementName)
    {
        if(System.getProperty("activePage").equalsIgnoreCase("Home"))
            return products.GitHub.Pages.HomePage.getElementSelector(elementName);
        else if(System.getProperty("activePage").equalsIgnoreCase("MyProfile"))
            return products.GitHub.Pages.MyProfilePage.getElementSelector(elementName);
        else {System.out.println( System.getProperty("product") + " " + System.getProperty("activePage") +
                " Page has not been defined in the AllPages");
            return "NoSuchAnElement";}
    }


}
