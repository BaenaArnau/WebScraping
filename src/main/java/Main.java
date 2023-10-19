import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://universe.leagueoflegends.com/es_ES/regions/");

        // Espera implícita para dar tiempo a que la página cargue completamente
        driver.manage().window().maximize(); // Maximiza la ventana (opcional)
        Thread.sleep(5000); // Espera de 5 segundos (puede ser ajustada según necesidades)

        List<WebElement> elementos = driver.findElements(By.className("factionWrapper_9Uuf"));

        ArrayList<String> listaHrefs = new ArrayList<>();

        for (WebElement elemento : elementos) {
            String href = elemento.getAttribute("href");
            listaHrefs.add(href);
        }

        for (String href : listaHrefs) {
            System.out.println("Contenido de href: " + href);
        }

        driver.quit();
    }
}