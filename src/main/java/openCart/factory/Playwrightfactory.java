package openCart.factory;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Playwrightfactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

    public static Playwright getPlaywright(){
        return tlPlaywright.get();
    }
    public static Browser getBrowser(){
        return tlBrowser.get();
    }
    public static BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }
    public static Page getPage(){
        return tlPage.get();
    }

    public Page initBrowser(Properties properties){
        //playwright = Playwright.create();
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is "+browserName);

        tlPlaywright.set(Playwright.create());

        switch (properties.getProperty("browser").toLowerCase()){
            case "chromium" :
                System.out.println("Launching chromium!!");
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(tlPlaywright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari" :
                //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(tlPlaywright.get().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox" :
                //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(tlPlaywright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome" :
                System.out.println("Launching chrome");
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                tlBrowser.set(tlPlaywright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            default:
                System.out.println("Browsername not found!!");
                break;
        }
        //browserContext = browser.newContext();
        System.out.println("Browser context is "+getBrowser().newContext());
        tlBrowserContext.set(getBrowser().newContext());
        System.out.println("Browser context is "+getBrowser().newContext());
        //page = browserContext.newPage();
        tlPage.set(getBrowserContext().newPage());

        //page.navigate(properties.getProperty("url"));
        getPage().navigate(prop.getProperty("url").trim());

        System.out.println(System.getProperty("java.class.path"));
        //return page;
        return getPage();
    }
    public Properties initProp() throws IOException {
        FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +"/src/resources/config/config.properties");
        prop = new Properties();
        prop.load(ip);
        return prop;
    }

    public static String takeScreenshot(){
        String path = System.getProperty("user.dir") + "/screenshot" + System.currentTimeMillis() + ".png";
        getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
        return path;
     }
}
