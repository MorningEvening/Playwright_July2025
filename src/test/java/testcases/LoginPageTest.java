package testcases;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest{

    @Test (priority = 1)
    public void loginPageNavigationTest(){
        loginPage = homePage.navigateTologinPage();
        String login_title = loginPage.getLoginPageTitle();
        Assert.assertEquals("Products",login_title);
    }

    @Test(priority = 2)
    public void verifyNumofProducts(){
        String total_fromWebsite = loginPage.getTotalItems();
        String config_total = prop.getProperty("total_items");
        Assert.assertEquals(total_fromWebsite,config_total);
    }
}
