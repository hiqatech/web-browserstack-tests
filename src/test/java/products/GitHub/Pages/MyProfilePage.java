package products.GitHub.Pages;

import java.util.HashMap;

public class MyProfilePage {

    public static HashMap<String, String> elementLocators = new HashMap<String, String>();

    public static void setElements()
    {
        elementLocators.put("my_profile_menu", "//summary[@aria-label=\"View profile and more\"]");
        elementLocators.put("my_database_repository", "//a[@href=\"/hiqatech/my-data-base\"]");
        elementLocators.put("code_menu", "//span[@data-content=\"Code\"]");
        elementLocators.put("my_cars_file", "//a[@title=\"MyCars.xlsx\"]");
        elementLocators.put("download_button", "//div[@class=\"BtnGroup\"]/a");
        elementLocators.put("my_repository_name", "//*[@class=\"mr-2 flex-self-stretch\"]");
        elementLocators.put("add_file_dropdown", "//*[@class=\"details-overlay details-reset position-relative d-block\"]/summary");
        elementLocators.put("upload_file_option", "//*[@class=\"details-overlay details-reset position-relative d-block\"]/div/ul/li[4]");
        elementLocators.put("choose_your_files_field", "//input[@type=\"file\"]");
        elementLocators.put("commit_changes_button", "//button[@data-edit-text=\"Commit changes\"]");
        elementLocators.put("", "");

    }

    public static String getElementSelector(String element_name)
    {
        setElements();
        String elementLocator = elementLocators.get(element_name);
        return elementLocator;
    }

}
