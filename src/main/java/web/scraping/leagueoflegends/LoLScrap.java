package web.scraping.leagueoflegends;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LoLScrap {
    private ArrayList<String> listaHrefsRegiones = new ArrayList<>();
    private ArrayList<String> listaHrefsCampeones = new ArrayList<>();
    private ArrayList<Campeon> campeones = new ArrayList<>();
    private ArrayList<Region> regiones = new ArrayList<>();
    private ArrayList<Habilidad> habilidades = new ArrayList<>();

    public void comezarElRobo() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);
        //guardarHrefRegiones(driver);
        //robarRegion(driver);
        guardarHrefCampeones(driver);
        robarCampeones(driver);

        driver.close();
    }

    public void guardarHrefRegiones (WebDriver driver) throws InterruptedException {
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

    public void guardarHrefCampeones (WebDriver driver) throws InterruptedException {
        driver.get("https://universe.leagueoflegends.com/es_ES/champions/");
        Thread.sleep(5000);

        List<WebElement> elementos = driver.findElements(By.className("item_30l8"));


        for (WebElement elemento : elementos) {
            String href = elemento.findElement(By.tagName("a")).getAttribute("href");
            listaHrefsCampeones.add(href);
        }

        for (String href : listaHrefsCampeones) {
            System.out.println("Contenido de href: " + href);
        }
    }

    private void robarRegion(WebDriver driver) throws InterruptedException {

        for (String href : listaHrefsRegiones) {
            String nombre;
            String descripcion = "";
            int historiasRelacionada;
            List<WebElement> elements;

            driver.get(href);
            Thread.sleep(5000);

            WebElement element = driver.findElement(By.className("title_1orQ"));
            nombre = element.getText();

            element = driver.findElement(By.className("description_1-6k"));
            if (nombre.equalsIgnoreCase("zaun") | nombre.equalsIgnoreCase("piltover") | nombre.equalsIgnoreCase("TARGON")){
                descripcion = element.getText();

            }else {
                elements = element.findElements(By.tagName("p"));
                for (WebElement elemetoss : elements) {

                    descripcion += elemetoss.getText() + "\n";
                }
            }

            elements = driver.findElements(By.className("item_3MaG"));
            historiasRelacionada = elements.size();

            System.out.println("Nombre: " + nombre);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Número de historias relacionadas: " + historiasRelacionada);

            Region region = new Region(nombre, descripcion, historiasRelacionada);
            regiones.add(region);
        }
    }
    public void robarCampeones(WebDriver driver) throws InterruptedException {

        for (String href : listaHrefsCampeones){
            String nombre;
            String apodo;
            List<String> campeonesConRelacion = new ArrayList<>();
            String biografia;
            boolean aparicionEnCinematicas;
            int numRelatosCortos;
            String rol;
            String raza;
            String region;
            List<Habilidad> habilidades;
            List<String> nombreDeAspectos;

            driver.get(href);
            Thread.sleep(5000);

            WebElement element = driver.findElement(By.className("title_1orQ"));
            nombre = element.getText();

            element = driver.findElement(By.className("subheadline_rlsJ"));
            apodo = element.getText();

            List<WebElement> elements = driver.findElements(By.className("champion_1xlO"));
            for (WebElement elemento : elements){
                elemento = elemento.findElement(By.tagName("div"));
                elemento = elemento.findElement(By.tagName("h5"));
                campeonesConRelacion.add(elemento.getText());
            }

            try {
                element = driver.findElement(By.className("race_3k58"));
                raza = element.findElement(By.tagName("h6")).getText();
            }catch (NoSuchElementException e){
                raza = "Desconocida";
            }

            /*element = driver.findElement(By.className("race_3k58"));
            if (element != null){
                raza = element.findElement(By.tagName("h6")).getText();
            }else {
                raza = "Desconocida";
            }*/

            element = driver.findElement(By.className("typeDescription_ixWu"));
            element = element.findElement(By.tagName("div"));
            rol = element.findElement(By.tagName("h6")).getText();

            elements = driver.findElements(By.className("additionalContent_25fY"));
            numRelatosCortos = elements.size();

            element = driver.findElement(By.className("factionText_EnRL"));
            element = element.findElement(By.tagName("h6"));
            region = element.findElement(By.tagName("span")).getText();

            element = driver.findElement(By.className("top__0Tf"));
            if (element != null){
                aparicionEnCinematicas = true;
            }else {
                aparicionEnCinematicas = false;
            }

            element = driver.findElement(By.className("biography_3YIe"));
            element = element.findElement(By.tagName("a"));
            biografia = element.getAttribute("href");

            driver.get(biografia);
            Thread.sleep(5000);

            biografia = null;
            elements = driver.findElements(By.className("p_1_sJ"));
            for (WebElement elemento : elements){
                biografia += elemento.getText();
            }

            System.out.println("Nombre: " + nombre);
            System.out.println("Apodo: " + apodo);
            System.out.println("Campeones Relacionados: " + campeonesConRelacion.size());
            System.out.println("Biografía: " + biografia);
            System.out.println("Aparición en Cinemáticas: " + aparicionEnCinematicas);
            System.out.println("Número de Relatos Cortos: " + numRelatosCortos);
            System.out.println("Rol: " + rol);
            System.out.println("Raza: " + raza);
            System.out.println("Región: " + region);
        }
    }
}
