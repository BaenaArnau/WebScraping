import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> listaHrefsRegiones = new ArrayList<>();

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
            String nombre;
            String descripcion = "";
            int historiasRelacionada;
            List<String> nombreCampeones = new ArrayList<>();

            driver.get(href);
            Thread.sleep(5000);

            WebElement element = driver.findElement(By.className("title_1orQ"));
            nombre = element.getText();

            element = driver.findElement(By.className("description_1-6k"));
            List<WebElement> elements = element.findElements(By.tagName("p"));

            for (WebElement elemetoss : elements) {
                descripcion += elemetoss.getText() + "\n";
            }

            elements = driver.findElements(By.className(".QCard_hextech_root_a9df.QCard_hextech_clickable_275e.item_3MaG"));
            historiasRelacionada = elements.size();

            System.out.println("Nombre: " + nombre);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Número de historias relacionadas: " + historiasRelacionada);
            System.out.println("Nombres de campeones: " + Arrays.toString(nombreCampeones.toArray()));
        }

        driver.quit(); // Mueve esta línea fuera del bucle for
    }
}
