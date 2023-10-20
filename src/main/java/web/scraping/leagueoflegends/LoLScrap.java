package web.scraping.leagueoflegends;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import javax.lang.model.element.Element;
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

    }

    private void RobarDatos() throws InterruptedException {

        for (String href : listaHrefsRegiones){
            String nombre;
            String descripcion = null;
            int historiasRelacionada;
            List<String> nombreCampeones = new ArrayList<>();

            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            FirefoxOptions options = new FirefoxOptions();

            WebDriver driver = new FirefoxDriver(options);
            driver.get(href);
            Thread.sleep(5000);

            WebElement element = driver.findElement(By.className("title_1orQ"));

            nombre = element.getText();

            element = driver.findElement(By.className("description_1-6k"));

            List<WebElement> elementos = element.findElements(By.tagName("p"));

            for (WebElement elemetoss : elementos){
                descripcion = descripcion + elemetoss.getText() + "\n";
            }

            element = driver.findElement(By.className("moreContentBtn_2uNP"));
            element.click();
            elementos = driver.findElements(By.className("copy_xxN7"));

            for (WebElement elemetoss : elementos){
                nombreCampeones.add(elemetoss.findElement(By.tagName("h1")).getText());
            }

            elementos = driver.findElements(By.className("itemWrapper_22mb small_1-2z"));
            historiasRelacionada = elementos.size();

            driver.quit();
        }
    }
}
