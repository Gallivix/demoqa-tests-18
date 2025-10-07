import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {

    @BeforeAll
    static void setup() {
        // Автоматически скачивает ChromeDriver, совместимый с установленным Chrome
        WebDriverManager.chromedriver().clearDriverCache().setup(); // clearCache — на всякий случай

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 60_000;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");

        // 🔽 ОПЦИОНАЛЬНО: если вы используете СВОЙ Chrome (portable), раскомментируйте и укажите путь:
        // options.setBinary("C:/path/to/your/chrome.exe");

        Configuration.browserCapabilities = options;
    }

    @Test
    void fillFromTest() {
        // Убраны пробелы в URL!
        open("https://demoqa.com/text-box");

        $("#userName").setValue("Artem Izbyshev");
        $("#userEmail").setValue("alex@egorov.com");
        $("#currentAddress").setValue("Some address 1");
        $("#permanentAddress").setValue("Address 2");
        $("#submit").click();

        $("#output").shouldHave(
                text("Artem Izbyshev"),
                text("alex@egorov.com"),
                text("Some address 1"),
                text("Address 2")
        );
    }
}