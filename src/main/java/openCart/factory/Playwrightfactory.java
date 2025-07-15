package openCart.factory;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import com.microsoft.playwright.*;

import java.util.Locale;

public class Playwrightfactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    public Page initBrowser(String browserName){
        System.out.println("Browser name is "+browserName);
        playwright = Playwright.create();

        switch (browserName.toLowerCase()){
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
        System.out.println("browser context launches newpage and its "+page);
        page.navigate("https://www.saucedemo.com/");
        return page;
    }
}
