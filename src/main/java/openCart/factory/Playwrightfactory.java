package openCart.factory;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class Playwrightfactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;
    public Page initBrowser(Properties properties){
        playwright = Playwright.create();

        switch (properties.getProperty("browser").toLowerCase()){
            case "chromium" :
                System.out.println("Launching chromium!!");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "safari" :
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox" :
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome" :
                System.out.println("Launching chrome");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            default:
                System.out.println("Browsername not found!!");
                break;
        }
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(properties.getProperty("url"));
        return page;
    }
    public Properties initProp() throws IOException {
        FileInputStream ip = new FileInputStream("/Users/pritipradhan/Documents/Practice_Automation_codes/PlayWright_Jul_2025/src/resources/config/config.properties");
        prop = new Properties();
        prop.load(ip);
        return prop;
    }
}
