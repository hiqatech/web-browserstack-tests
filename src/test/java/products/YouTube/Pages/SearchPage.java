package products.YouTube.Pages;

import java.util.HashMap;

public class SearchPage {

    public static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {
        elementLocators.put("search_field", "//*[@id=\"search\"]");
        elementLocators.put("search_submit", "//*[@id=\"search-icon-legacy\"]");
        elementLocators.put("search_result_1st" , "//ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]");
        elementLocators.put("search_result_2nd" , "//ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[2]");
        elementLocators.put("search_result_3rd" , "//ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[3]");
        elementLocators.put("search_result_4nd" , "//ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[4]");
        elementLocators.put("search_result1_image", "//*[@id=\"img\"]");
        elementLocators.put("skip_ad_button" , "//*[contains(text(),'Skip Ad')]");
        elementLocators.put("", "");

    }

    public static String getElementSelector(String element_name)
    {
        setElements();
        String elementLocator = elementLocators.get(element_name);
        return elementLocator;
    }
}
