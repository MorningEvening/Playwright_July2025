package testcases;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class HomePageTest extends BaseTest{

   @Test
    public void homePageTitle(){
       // the homepage can be accessed here because both Basetest and HomePageTest are in same package
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle,"Swag Labs");
    }
    @Test
    public void homePageURL(){
        String url_login = homePage.getHomePageURL();
        Assert.assertEquals(url_login,"https://www.saucedemo.com/");
    }

//    @DataProvider
    public Object[][] getProductdata(){
        return new Object[][]{
                {"Fleece"},
                {"Backpack"},
                {"Not available product"}
        };
    }

  //  @Test(dataProvider = "getProductdata")
    public void searchItem(String prodName){
        Boolean search = homePage.searchInPage(prodName);
        System.out.println("Searching for product "+prodName);
        System.out.println("search boolean is "+search);
        Assert.assertTrue(search);
    }

}
