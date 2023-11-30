package web.scraping.leagueoflegends;

import com.opencsv.CSVWriter;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de control para efectuar el WebScraping de la pagina del LoL
 */
public class LoLScrap {

    /**
     * El tiempo que tarda en seguir el codigo cada vez que cambia de pagina
     */
    public int tiempo = 3000;

    /**
     * Esta lista nos permitira guardar los links de las regiones
     */
    private ArrayList<String> listaHrefsRegiones = new ArrayList<>();

    /**
     * Esta lista nos permitira guardar los links de los campeones
     */
    private ArrayList<String> listaHrefsCampeones = new ArrayList<>();

    /**
     * Esta lista nos permitira guardar los campeones que scrapeamos
     */
    private ArrayList<Campeon> campeones = new ArrayList<>();

    /**
     * Esta lista nos permitira guardar las regiones que scrapeamos
     */
    private ArrayList<Region> regiones = new ArrayList<>();

    /**
     * Esta lista nos permitira guardar las habilidades que scrapeamos
     */
    List<Habilidad> habilidades = new ArrayList<>();

    /**
     * Esta region es la region por defecto del LoL que en verdad es todo el mundo del lol pero no sale omo tal en la pagina
     */
    private Region regio = new Region("Runaterra","Esto no es una region como tal ya que es todo el continete donde estan las regiones del juego y el sitio donde Riot pone a los campeones que no tiene una region definida.",0);

    /**
     * Inicia el proceso de web scraping de datos de League of Legends.
     *
     * @throws InterruptedException          si el hilo se interrumpe durante la espera.
     * @throws ParserConfigurationException si no se puede crear un DocumentBuilder.
     * @throws TransformerException         si ocurre un error durante la transformación XML.
     */
    public void comezarElRobo() throws InterruptedException, ParserConfigurationException, TransformerException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);

        guardarHrefRegiones(driver);
        robarRegion(driver);
        guardarHrefCampeones(driver);
        robarCampeones(driver);
        meterCampeonesEnReiones(regiones,campeones);
        crearCSV();
        crearXML();

        driver.quit();
    }

    /**
     * Guarda los hrefs de las regiones desde el sitio web del universo de League of Legends.
     *
     * @param driver el WebDriver para la automatización del navegador.
     * @throws InterruptedException si el hilo se interrumpe durante la espera.
     */
    private void guardarHrefRegiones (WebDriver driver) throws InterruptedException {
        driver.get("https://universe.leagueoflegends.com/es_ES/regions/");
        Thread.sleep(tiempo);

        List<WebElement> elementos = driver.findElements(By.className("factionWrapper_9Uuf"));


        for (WebElement elemento : elementos) {
            String href = elemento.getAttribute("href");
            listaHrefsRegiones.add(href);
        }
    }

    /**
     * Guarda los hrefs de los campeones desde el sitio web del universo de League of Legends.
     *
     * @param driver el WebDriver para la automatización del navegador.
     * @throws InterruptedException si el hilo se interrumpe durante la espera.
     */
    private void guardarHrefCampeones (WebDriver driver) throws InterruptedException {
        driver.get("https://universe.leagueoflegends.com/es_ES/champions/");
        Thread.sleep(tiempo);

        List<WebElement> elementos = driver.findElements(By.className("item_30l8"));


        for (WebElement elemento : elementos) {
            String href = elemento.findElement(By.tagName("a")).getAttribute("href");
            listaHrefsCampeones.add(href);
        }
    }
    /**
     * Obtiene información sobre las regiones desde el sitio web del universo de League of Legends.
     *
     * @param driver el WebDriver para la automatización del navegador.
     * @throws InterruptedException si el hilo se interrumpe durante la espera.
     */
    private void robarRegion(WebDriver driver) throws InterruptedException {
        regiones.add(regio);

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

                    descripcion += elemetoss.getText() + " ";
                }
            }

            elements = driver.findElements(By.className("item_3MaG"));
            historiasRelacionada = elements.size();

            Region region = new Region(nombre, descripcion, historiasRelacionada);
            regiones.add(region);
        }
    }

    /**
     * Obtiene información sobre los campeones desde el sitio web del universo de League of Legends.
     *
     * @param driver el WebDriver para la automatización del navegador.
     * @throws InterruptedException si el hilo se interrumpe durante la espera.
     */
    private void robarCampeones(WebDriver driver) throws InterruptedException {

        for (String href : listaHrefsCampeones) {
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
            String nombreToHref;

            driver.get(href);
            Thread.sleep(tiempo);

            WebElement element = driver.findElement(By.className("title_1orQ"));
            nombre = element.getText();

            element = driver.findElement(By.className("subheadline_rlsJ"));
            apodo = element.getText();

            List<WebElement> elements = driver.findElements(By.className("champion_1xlO"));
            for (WebElement elemento : elements) {
                elemento = elemento.findElement(By.tagName("div"));
                elemento = elemento.findElement(By.tagName("h5"));
                campeonesConRelacion.add(elemento.getText());
            }

            elements = driver.findElements(By.className("race_3k58"));
            if (!elements.isEmpty()) {
                raza = elements.get(0).findElement(By.tagName("h6")).getText();
            } else {
                raza = "Desconocida";
            }

            element = driver.findElement(By.className("typeDescription_ixWu"));
            element = element.findElement(By.tagName("div"));
            rol = element.findElement(By.tagName("h6")).getText();

            elements = driver.findElements(By.className("additionalContent_25fY"));
            numRelatosCortos = elements.size();


            if (nombre.equalsIgnoreCase("Malphite") | nombre.equalsIgnoreCase("Qiyana") | nombre.equalsIgnoreCase("Milio") | nombre.equalsIgnoreCase("Neeko") | nombre.equalsIgnoreCase("Nidalee") | nombre.equalsIgnoreCase("Rengar") | nombre.equalsIgnoreCase("Zyra")) {
                region = "Ixtal";
            } else {
                element = driver.findElement(By.className("factionText_EnRL"));
                element = element.findElement(By.tagName("h6"));
                region = element.findElement(By.tagName("span")).getText();
            }


            elements = driver.findElements(By.className("top__0Tf"));
            if (!elements.isEmpty()) {
                aparicionEnCinematicas = true;
            } else {
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
            for (WebElement elemento : elements) {
                biografia += elemento.getText() + " ";
            }

            driver.get(href);
            Thread.sleep(tiempo);


            if (nombre.contains("'")){
                nombreToHref = nombre.replace("'","-");
            } else if (nombre.equalsIgnoreCase("Bardo")) {
                nombreToHref = "Bard";
            } else if (nombre.equalsIgnoreCase("Dr. Mundo")) {
                nombreToHref = "Dr-mundo";
            } else if (nombre.equalsIgnoreCase("Maestro Yi")) {
                nombreToHref = "Master-yi";
            } else if (nombre.equalsIgnoreCase("Nunu y Willump")) {
                nombreToHref = "Nunu";
            } else if (nombre.equalsIgnoreCase("Renata Glasc")) {
                nombreToHref = "Renata";
            } else if (nombre.contains(" ")) {
                nombreToHref = nombre.replace(" ","-");
            }else {
                nombreToHref = nombre;
            }
            if (!nombre.equalsIgnoreCase("Hwei")){
                campHref = "https://www.leagueoflegends.com/es-es/champions/" + nombreToHref.toLowerCase() + "/";

                driver.get(campHref);
                Thread.sleep(tiempo);

                elements = driver.findElements(By.className("style__CarouselItemText-sc-gky2mu-16"));
                numDeAspectos = elements.size();

                element = driver.findElement(By.cssSelector("div[data-testid='overview:difficulty']"));
                dificultad = element.getText();

                habilidades = robarHabilidad(driver, campHref, nombre);
                Campeon campeon = new Campeon(nombre,apodo,campeonesConRelacion.size(),biografia,aparicionEnCinematicas,numRelatosCortos,rol,raza, region, habilidades,numDeAspectos,dificultad);
                campeones.add(campeon);
            }
        }
    }

    /**
     * Obtiene información sobre las habilidades de un campeón desde el sitio web del universo de League of Legends.
     *
     * @param driver el WebDriver para la automatización del navegador.
     * @param href   la URL de la página del campeón.
     * @return una lista de habilidades para el campeón.
     * @throws InterruptedException si el hilo se interrumpe durante la espera.
     */
    private List<Habilidad> robarHabilidad(WebDriver driver, String href, String campeon) throws InterruptedException {
        List<Habilidad> habilidadesDeCampeon = new ArrayList<>();
        driver.get(href);
        Thread.sleep(tiempo);
        for (int i = 0; i < 5; i++) {
            String nombre;
            boolean pasiva;
            char asignacionDeTelca;
            String descripcion;
            String linkVideo = "";
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

            try {
                element = driver.findElement(By.cssSelector("video[data-testid='" + contenidoVideo + "']"));
                try {
                    element = element.findElement(By.cssSelector("source[type='video/mp4']"));
                    linkVideo = element.getAttribute("src");
                }catch (org.openqa.selenium.NoSuchElementException e) {
                    element = element.findElement(By.cssSelector("source[type='video/webm']"));
                    linkVideo = element.getAttribute("src");
                }
            }catch (org.openqa.selenium.NoSuchElementException e){
                linkVideo = null;
            }

            Habilidad habilidad = new Habilidad(campeon,nombre,pasiva,asignacionDeTelca,descripcion,linkVideo);
            habilidadesDeCampeon.add(habilidad);
            habilidades.add(habilidad);
        }
        return habilidadesDeCampeon;
    }

    /**
     * Asocia los campeones con sus respectivas regiones.
     *
     * @param regiones   una lista de regiones.
     * @param campeones  una lista de campeones.
     */
    private void meterCampeonesEnReiones(List<Region> regiones, List<Campeon> campeones){
        for (Campeon campeon : campeones){
            for (int i = 0; i < regiones.size(); i++) {
                if (campeon.getRegion().equalsIgnoreCase(regiones.get(i).getNombre())){
                    regiones.get(i).getCampeones().add(campeon);
                }
            }
        }
    }

    /**
     * Crea archivos CSV con los datos obtenidos de cada clase.
     */
    private void crearCSV() {
        try (CSVWriter writerRegion = new CSVWriter(new FileWriter("Regiones.csv"))) {
            writerRegion.writeNext(new String[]{"nombre,descripcion,historiasRelacionada"});
            for (Region region : regiones) {
                writerRegion.writeNext(new String[]{region.toString()});
            }
            System.out.println("Archivo CSV de regiones se ha creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (CSVWriter writerCampeones = new CSVWriter(new FileWriter("Campeons.csv"))) {
            writerCampeones.writeNext(new String[]{"region,nombre,apodo,campeonesConRelacion,biografia,aparicionEnCinematicas,numRelatosCortos,rol,raza,numDeAspectos,dificultad"});
            for (Campeon campeon : campeones) {
                writerCampeones.writeNext(new String[]{campeon.toString()});
            }
            System.out.println("Archivo CSV de campeones se ha creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (CSVWriter writerHabilidad = new CSVWriter(new FileWriter("Habilidades.csv"))) {
            writerHabilidad.writeNext(new String[]{"campeon,nombre,pasiva,asignacionDeTecla,descripcion,linkVideo"});
            for (Habilidad habilidad : habilidades) {
                writerHabilidad.writeNext(new String[]{habilidad.toString()});
            }
            System.out.println("Archivo CSV de habilidades se ha creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea un archivo XML con los datos obtenidos.
     *
     * @throws ParserConfigurationException si no se puede crear un DocumentBuilder.
     * @throws TransformerException         si ocurre un error durante la transformación XML.
     */
    private void crearXML() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.newDocument();

        Node rootNode = document.createElement("regiones");
        document.appendChild(rootNode);

        for (Region region : regiones){
            Node regionNode = document.createElement("region");
            rootNode.appendChild(regionNode);

            Node nombreRegion = document.createElement("Nombre");
            nombreRegion.appendChild(document.createTextNode(region.getNombre()));
            regionNode.appendChild(nombreRegion);

            Node descripcionRegion = document.createElement("Descripcion");
            descripcionRegion.appendChild(document.createTextNode(region.getDescripcion()));
            regionNode.appendChild(descripcionRegion);

            Node campeonesNode = document.createElement("campeones");
            regionNode.appendChild(campeonesNode);
            for (Campeon campeon : region.getCampeones()){
                Node campeonNode = document.createElement("campeon");
                campeonesNode.appendChild(campeonNode);

                Node nombreCampeon = document.createElement("Nombre");
                nombreCampeon.appendChild(document.createTextNode(campeon.getNombre()));
                campeonNode.appendChild(nombreCampeon);

                Node apodoCampeon = document.createElement("Apodo");
                apodoCampeon.appendChild(document.createTextNode(campeon.getApodo()));
                campeonNode.appendChild(apodoCampeon);

                Node campeonesConRelacionNode = document.createElement("CampeonesConRelacion");
                campeonesConRelacionNode.appendChild(document.createTextNode(String.valueOf(campeon.getCampeonesConRelacion())));
                campeonNode.appendChild(campeonesConRelacionNode);

                Node biografiaCampeon = document.createElement("Biografia");
                biografiaCampeon.appendChild(document.createTextNode(campeon.getBiografia()));
                campeonNode.appendChild(biografiaCampeon);

                Node aparicionEnCinematicasCampeon = document.createElement("AparicionEnCinematicas");
                aparicionEnCinematicasCampeon.appendChild(document.createTextNode(String.valueOf(campeon.isAparicionEnCinematicas())));
                campeonNode.appendChild(aparicionEnCinematicasCampeon);

                Node numRelatosCortosCampeon = document.createElement("NumRelatosCortos");
                numRelatosCortosCampeon.appendChild(document.createTextNode(String.valueOf(campeon.getNumRelatosCortos())));
                campeonNode.appendChild(numRelatosCortosCampeon);

                Node rolCampeon = document.createElement("Rol");
                rolCampeon.appendChild(document.createTextNode(campeon.getRol()));
                campeonNode.appendChild(rolCampeon);

                Node razaCampeon = document.createElement("Raza");
                razaCampeon.appendChild(document.createTextNode(campeon.getRaza()));
                campeonNode.appendChild(razaCampeon);

                Node regionCampeon = document.createElement("Region");
                regionCampeon.appendChild(document.createTextNode(campeon.getRegion()));
                campeonNode.appendChild(regionCampeon);

                Node habilidadesCampeon = document.createElement("Habilidades");
                campeonNode.appendChild(habilidadesCampeon);
                for (Habilidad habilidad : campeon.getHabilidades()){
                    Node habilidadCampeon = document.createElement("Habilidad");
                    habilidadesCampeon.appendChild(habilidadCampeon);

                    Node nombreHabilidad = document.createElement("Nombre");
                    nombreHabilidad.appendChild(document.createTextNode(habilidad.getNombre()));
                    habilidadCampeon.appendChild(nombreHabilidad);

                    Node pasivaHabilidad = document.createElement("Pasiva");
                    pasivaHabilidad.appendChild(document.createTextNode(String.valueOf(habilidad.isPasiva())));
                    habilidadCampeon.appendChild(pasivaHabilidad);

                    Node asignacionDeTelcaHabilidad = document.createElement("AsignacionDeTelca");
                    asignacionDeTelcaHabilidad.appendChild(document.createTextNode(String.valueOf(habilidad.getAsignacionDeTecla())));
                    habilidadCampeon.appendChild(asignacionDeTelcaHabilidad);

                    Node descripcionHabilidad = document.createElement("Descripcion");
                    descripcionHabilidad.appendChild(document.createTextNode(habilidad.getDescripcion()));
                    habilidadCampeon.appendChild(descripcionHabilidad);

                    Node linkVideoHabilidad = document.createElement("LinkVideo");
                    linkVideoHabilidad.appendChild(document.createTextNode(habilidad.getLinkVideo()));
                    habilidadCampeon.appendChild(linkVideoHabilidad);
                }

                Node numDeAspectosCampeon = document.createElement("NumDeAspectos");
                numDeAspectosCampeon.appendChild(document.createTextNode(String.valueOf(campeon.getNumDeAspectos())));
                campeonNode.appendChild(numDeAspectosCampeon);

                Node dificultadCampeon = document.createElement("Dificultad");
                dificultadCampeon.appendChild(document.createTextNode(campeon.getDificultad()));
                campeonNode.appendChild(dificultadCampeon);
            }

            Node historiasRelacionadaRegion = document.createElement("HistoriasRelacionada");
            historiasRelacionadaRegion.appendChild(document.createTextNode(String.valueOf(region.getHistoriasRelacionada())));
            regionNode.appendChild(historiasRelacionadaRegion);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("LoLWebScraping.xml"));
        transformer.transform(source, result);

        System.out.println("Archivo XML creado correctamente.");
    }
}