package openCart.pages;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */


import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;
    private String user_locator = "[data-test=\"username\"]";
    private String pass_locator = "[data-test=\"password\"]";
    private String login_locator = "[data-test=\"login-button\"]";
    private String title = "div.app_logo";

    // Constructor
    public HomePage (Page page){
        this.page = page;
    }

    public String getHomePageTitle(){
        return page.title();
    }

    public String getHomePageURL(){
        return page.url();
    }

    public boolean searchInPage(String tosearch){
        return page.content().contains(tosearch);
    }

    public String login(){
        page.locator(user_locator).fill("standard_user");
        page.locator(pass_locator).fill("secret_sauce");
        page.locator(login_locator).click();
        String actual_tile = page.textContent(title);
        System.out.println("Title is "+actual_tile);
        return actual_tile;
    }
}
