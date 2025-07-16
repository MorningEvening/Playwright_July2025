package openCart.pages;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import com.microsoft.playwright.Page;

public class LoginPage {

    Page page;
    private String loginTitle_locator = "//div[@class='header_secondary_container']/span";
    private String listLocator="//div[@class='inventory_list']/div";
    public LoginPage(Page page){
        this.page = page;
    }

    public String getLoginPageTitle(){
        return page.locator(loginTitle_locator).innerText();
    }

    public String getTotalItems(){
        String total = String.valueOf(page.locator(listLocator).count());
        System.out.println("Total items counted from xpath "+total);
        return total;
    }

}
