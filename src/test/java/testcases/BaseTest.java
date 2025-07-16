package testcases;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import com.microsoft.playwright.Page;
import openCart.factory.Playwrightfactory;
import openCart.pages.HomePage;
import openCart.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    Playwrightfactory pf;
    Page page;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected Properties prop;

    @BeforeTest
    public void setup() throws IOException {
        pf = new Playwrightfactory();
        prop = pf.initProp();
        page = pf.initBrowser(prop);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void teardown(){
        page.context().browser().close();
    }
}
