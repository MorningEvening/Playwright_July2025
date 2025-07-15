package testcases;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import com.microsoft.playwright.Page;
import openCart.factory.Playwrightfactory;
import openCart.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    Playwrightfactory pf;
    Page page;
    HomePage homePage;
    Properties prop;

    @BeforeTest
    public void setup() throws IOException {

        pf = new Playwrightfactory();
        prop = pf.initProp();
        page = pf.initBrowser(prop);
        homePage = new HomePage(page);
        homePage.login();
    }

    @AfterTest
    public void teardown(){
        page.context().browser().close();
    }
}
