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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    Playwrightfactory pf;
    Page page;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected Properties prop;

    @Parameters("browser")
    @BeforeTest
    public void setup(@Optional("chromium") String browserName) throws IOException {
        pf = new Playwrightfactory();
        prop = pf.initProp();
        if(browserName!= null){
            prop.setProperty("browser",browserName);
        }
        page = pf.initBrowser(prop);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void teardown(){
        page.context().browser().close();
    }
}
