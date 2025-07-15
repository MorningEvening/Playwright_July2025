package testcases;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import com.microsoft.playwright.Page;
import openCart.factory.Playwrightfactory;
import openCart.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class HomePageTest {

    Playwrightfactory pf; // directly using new here
    Page page;
    HomePage homePage; // directly using homepage

    @BeforeTest
    public void setup(){

        //pf is the object of the class playwrightfactory and using that initbrowser is called which in turn returns page object
        // now to use methods associated with homepage lets assignt the returned paged to homepage
        pf = new Playwrightfactory();
        page = pf.initBrowser("chromium");
        System.out.println("initbrowser runs and page is created " + page);
        homePage = new HomePage(page);
        homePage.login();
    }

   @Test
    public void homePageTitle(){
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle,"Swag Labs");
    }
    @Test
    public void homePageURL(){
        String url_login = homePage.getHomePageURL();
        Assert.assertEquals(url_login,"https://www.saucedemo.com/inventory.html");
    }

    @DataProvider
    public Object[][] getProductdata(){
        return new Object[][]{
                {"Fleece"},
                {"Backpack"},
                {"Not available product"}
        };
    }

    @Test(dataProvider = "getProductdata")
    public void searchItem(String prodName){
        Boolean search = homePage.searchInPage(prodName);
        System.out.println("Searching for product "+prodName);
        System.out.println("search boolean is "+search);
        Assert.assertTrue(search);
    }

   @AfterTest
    public void teardown(){
        page.context().browser().close();
    }
}
