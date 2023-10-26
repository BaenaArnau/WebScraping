package web.scraping.leagueoflegends;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LoLScrap {
    private int tiempo = 3000;
    private ArrayList<String> listaHrefsRegiones = new ArrayList<>();
    private ArrayList<String> listaHrefsCampeones = new ArrayList<>();
    private ArrayList<Campeon> campeones = new ArrayList<>();
    private ArrayList<Region> regiones = new ArrayList<>();
    private ArrayList<Habilidad> habilidades = new ArrayList<>();

    public void comezarElRobo() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);

        //listaHrefsCampeones.add("https://universe.leagueoflegends.com/es_ES/champion/ahri/");
        //guardarHrefRegiones(driver);
        //robarRegion(driver);
        //guardarHrefCampeones(driver);
        //robarCampeones(driver);
        robarHabilidad(driver, "https://www.leagueoflegends.com/es-es/champions/ahri/");

        driver.quit();
    }

    private void guardarHrefRegiones (WebDriver driver) throws InterruptedException {
        driver.get("https://universe.leagueoflegends.com/es_ES/regions/");
        Thread.sleep(tiempo);

        List<WebElement> elementos = driver.findElements(By.className("factionWrapper_9Uuf"));


        for (WebElement elemento : elementos) {
            String href = elemento.getAttribute("href");
            listaHrefsRegiones.add(href);
        }

        for (String href : listaHrefsRegiones) {
            System.out.println("Contenido de href: " + href);
        }
    }

    private void guardarHrefCampeones (WebDriver driver) throws InterruptedException {
        driver.get("https://universe.leagueoflegends.com/es_ES/champions/");
        Thread.sleep(tiempo);

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
            Thread.sleep(tiempo);

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
    private void robarCampeones(WebDriver driver) throws InterruptedException {

        for (String href : listaHrefsCampeones){
            String nombre;
            String apodo;
            List<String> campeonesConRelacion = new ArrayList<>();
            String biografia = "";
            boolean aparicionEnCinematicas;
            int numRelatosCortos;
            String rol;
            String raza;
            String region;
            List<Habilidad> habilidades;
            int numDeAspectos;
            String dificultad;
            String campHref;

            driver.get(href);
            Thread.sleep(tiempo);

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

            elements = driver.findElements(By.className("race_3k58"));
            if (!elements.isEmpty()){
                raza = elements.get(0).findElement(By.tagName("h6")).getText();
            }else {
                raza = "Desconocida";
            }

            element = driver.findElement(By.className("typeDescription_ixWu"));
            element = element.findElement(By.tagName("div"));
            rol = element.findElement(By.tagName("h6")).getText();

            elements = driver.findElements(By.className("additionalContent_25fY"));
            numRelatosCortos = elements.size();


            if (nombre.equalsIgnoreCase("Malphite") | nombre.equalsIgnoreCase("Qiyana") | nombre.equalsIgnoreCase("Milio") | nombre.equalsIgnoreCase("Neeko") | nombre.equalsIgnoreCase("Nidalee") | nombre.equalsIgnoreCase("Rengar") | nombre.equalsIgnoreCase("Zyra")){
                region = "Ixtal";
            }else {
                element = driver.findElement(By.className("factionText_EnRL"));
                element = element.findElement(By.tagName("h6"));
                region = element.findElement(By.tagName("span")).getText();
            }


            elements = driver.findElements(By.className("top__0Tf"));
            if (!elements.isEmpty()){
                aparicionEnCinematicas = true;
            }else {
                aparicionEnCinematicas = false;
            }

            element = driver.findElement(By.className("biography_3YIe"));
            element = element.findElement(By.tagName("a"));
            campHref = element.getAttribute("href");

            driver.get(campHref);
            Thread.sleep(tiempo);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            element = driver.findElement(By.id("CatchElement"));
            elements = element.findElements(By.tagName("p"));
            for (WebElement elemento : elements){
                biografia += elemento.getText() + "\n";
            }

            driver.get(href);
            Thread.sleep(tiempo);

            element = driver.findElement(By.className("gameInfo_1HtZ"));
            element = element.findElement(By.tagName("a"));
            campHref = element.getAttribute("href");

            driver.get(campHref);
            Thread.sleep(tiempo);

            elements = driver.findElements(By.className("style__CarouselItemText-sc-gky2mu-16"));
            numDeAspectos = elements.size();

            element = driver.findElement(By.cssSelector("div[data-testid='overview:difficulty']"));
            dificultad = element.getText();

            System.out.println("Nombre: " + nombre);
            System.out.println("Apodo: " + apodo);
            System.out.println("Campeones Relacionados: " + campeonesConRelacion.size());
            System.out.println("Biografía: " + biografia);
            System.out.println("Aparición en Cinemáticas: " + aparicionEnCinematicas);
            System.out.println("Número de Relatos Cortos: " + numRelatosCortos);
            System.out.println("Rol: " + rol);
            System.out.println("Raza: " + raza);
            System.out.println("Región: " + region);
            System.out.println("Numero de aspectos: " + numDeAspectos);
            System.out.println("Dificultad del campeon: " + dificultad);

            habilidades = robarHabilidad(driver, campHref);
        }
    }

    private List<Habilidad> robarHabilidad(WebDriver driver, String href) throws InterruptedException {
        List<Habilidad> habilidades = new ArrayList<>();
        driver.get(href);
        Thread.sleep(tiempo);
        for (int i = 0; i < 5; i++) {
            String nombre;
            boolean pasiva;
            char asignacionDeTelca;
            String descripcion;
            String linkVideo;
            String contenido = "abilities:selector-" + i;
            String contenidoHabilidad = "abilities:ability-" + i;
            String contenidoVideo = "abilities-" + i + ":video";

            Actions acciones = new Actions(driver);
            WebElement boton = driver.findElement(By.cssSelector("button[data-testid='" + contenido + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", boton);
            acciones.moveToElement(boton).click().perform();
            Thread.sleep(tiempo);

            WebElement element = driver.findElement(By.cssSelector("li.style__AbilityInfoItem-sc-1bu2ash-8.lGkNU.is-active[data-testid='" + contenidoHabilidad + "']"));


            if (i == 0){
                pasiva = true;
                asignacionDeTelca = 'p';
            }else {
                pasiva = false;
                asignacionDeTelca = element.findElement(By.tagName("h6")).getText().charAt(0);
            }
            nombre = element.findElement(By.tagName("h5")).getText();
            descripcion = element.findElement(By.tagName("p")).getText();
            element = driver.findElement(By.cssSelector("video[data-testid='" + contenidoVideo + "']"));
            element = element.findElement(By.cssSelector("source[type='video/mp4']"));
            linkVideo = element.getAttribute("src");

            System.out.println("Nombre: " + nombre);
            System.out.println("Pasiva: " + pasiva);
            System.out.println("Asignación de Tecla: " + asignacionDeTelca);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Link de Video: " + linkVideo);

            JavascriptExecutor js = (JavascriptExecutor) driver;

            Habilidad habilidad = new Habilidad(nombre,pasiva,asignacionDeTelca,descripcion,linkVideo);
            habilidades.add(habilidad);
        }
        return habilidades;
    }
}
