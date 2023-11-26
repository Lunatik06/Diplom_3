package Other;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class DriverRule extends ExternalResource {

    WebDriver driver;

    @Override
    protected void before() throws Throwable {
        if ("yandex".equals(System.getProperty("browser")))
            setUpYandex();
        else {
            setUpChrome();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    public void setUpChrome() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        var service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/main/driver/chromedriver"))
                .build();

        var options = new ChromeOptions()
                .setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

        driver = new ChromeDriver(service, options);
    }

    public void setUpYandex() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        var service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/main/driver/yandexdriver"))
                .build();

        var options = new ChromeOptions()
                .setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        driver = new ChromeDriver(service, options);
    }

    @Override
    protected void after() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
