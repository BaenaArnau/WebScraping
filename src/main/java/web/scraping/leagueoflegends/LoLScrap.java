package web.scraping.leagueoflegends;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;

public class LoLScrap {
    ArrayList<String> listaHrefsRegiones = new ArrayList<>();
    ArrayList<Campeon> campeones = new ArrayList<>();
    ArrayList<Region> regiones = new ArrayList<>();
    ArrayList<Habilidad> habilidades = new ArrayList<>();
    private void GuardarHrefRegiones () throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://universe.leagueoflegends.com/es_ES/regions/");

        Thread.sleep(5000);

        List<WebElement> elementos = driver.findElements(By.className("factionWrapper_9Uuf"));


        for (WebElement elemento : elementos) {
            String href = elemento.getAttribute("href");
            listaHrefsRegiones.add(href);
        }

        for (String href : listaHrefsRegiones) {
            System.out.println("Contenido de href: " + href);
        }

        driver.quit();
    }

    private void RobarDatosRegion(){
        for (String href : listaHrefsRegiones){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            FirefoxOptions options = new FirefoxOptions();

            WebDriver driver = new FirefoxDriver(options);
            driver.get(href);

        }
    }
}
