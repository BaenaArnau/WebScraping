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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class LoLScrap {
    private int tiempo = 3000;
    private ArrayList<String> listaHrefsRegiones = new ArrayList<>();
    private ArrayList<String> listaHrefsCampeones = new ArrayList<>();
    private ArrayList<Campeon> campeones = new ArrayList<>();
    private ArrayList<Region> regiones = new ArrayList<>();
    private Region regio = new Region("Runaterra","Esto no es una region como tal ya que es todo el continete donde estan las regiones del juego y el sitio donde Riot pone a los campeones que no tiene una region definida.",0);

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

            System.out.println("Nombre: " + nombre);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Número de historias relacionadas: " + historiasRelacionada);

            Region region = new Region(nombre, descripcion, historiasRelacionada);
            regiones.add(region);
        }
    }
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

            campHref = "https://www.leagueoflegends.com/es-es/champions/" + nombre.toLowerCase() + "/";

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
            Campeon campeon = new Campeon(nombre,apodo,campeonesConRelacion,biografia,aparicionEnCinematicas,numRelatosCortos,rol,raza, region, habilidades,numDeAspectos,dificultad);
            campeones.add(campeon);
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
            if (nombre.equalsIgnoreCase("Sistema de cola de armas")){
                linkVideo = "null";
            }else {
                element = driver.findElement(By.cssSelector("video[data-testid='" + contenidoVideo + "']"));
                element = element.findElement(By.cssSelector("source[type='video/mp4']"));
                linkVideo = element.getAttribute("src");
            }

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
    private void meterCampeonesEnReiones(List<Region> regiones, List<Campeon> campeones){
        for (Campeon campeon : campeones){
            for (int i = 0; i < regiones.size(); i++) {
                if (campeon.getRegion().equalsIgnoreCase(regiones.get(i).getNombre())){
                    regiones.get(i).getCampeones().add(campeon);
                }
            }
        }
    }
    private void crearCSV(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("LoLWebScraping.csv")))){
            for (Region region : regiones){
                writer.write(region.getNombre());
                writer.write(",");
                writer.write(region.getDescripcion());
                writer.write(",");
                for (Campeon campeon : region.getCampeones()){
                    writer.write("(");
                    writer.write(campeon.getNombre());
                    writer.write(",");
                    writer.write(campeon.getApodo());
                    writer.write(",");
                    for (String string : campeon.getCampeonesConRelacion()){
                        writer.write("(");
                        writer.write(string);
                        writer.write("),");
                    }
                    writer.write(campeon.getBiografia());
                    writer.write(",");
                    writer.write(String.valueOf(campeon.isAparicionEnCinematicas()));
                    writer.write(",");
                    writer.write(String.valueOf(campeon.getNumRelatosCortos()));
                    writer.write(",");
                    writer.write(campeon.getRol());
                    writer.write(",");
                    writer.write(campeon.getRaza());
                    writer.write(",");
                    writer.write(campeon.getRegion());
                    writer.write(",");
                    for (Habilidad habilidad : campeon.getHabilidades()){
                        writer.write("(");
                        writer.write(habilidad.getNombre());
                        writer.write(",");
                        writer.write(String.valueOf(habilidad.isPasiva()));
                        writer.write(",");
                        writer.write(String.valueOf(habilidad.getAsignacionDeTelca()));
                        writer.write(",");
                        writer.write(habilidad.getDescripcion());
                        writer.write(",");
                        writer.write(habilidad.getLinkVideo());
                        writer.write("),");
                    }
                    writer.write(String.valueOf(campeon.getNumDeAspectos()));
                    writer.write(",");
                    writer.write(campeon.getDificultad());
                    writer.write("),");
                }
                writer.write(region.getHistoriasRelacionada());
                writer.newLine();
            }
            writer.close();
            System.out.println("Archivo CSV creado correctamente.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
                campeonNode.appendChild(campeonesConRelacionNode);
                for (String string : campeon.getCampeonesConRelacion()){
                    Node campeonConRelacionNode = document.createElement("CampeonConRelacion");
                    campeonConRelacionNode.appendChild(document.createTextNode(string));
                    campeonesConRelacionNode.appendChild(campeonConRelacionNode);
                }

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
                    asignacionDeTelcaHabilidad.appendChild(document.createTextNode(String.valueOf(habilidad.getAsignacionDeTelca())));
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