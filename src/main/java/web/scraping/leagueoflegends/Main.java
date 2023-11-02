package web.scraping.leagueoflegends;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ParserConfigurationException, TransformerException {
        LoLScrap loLScrap = new LoLScrap();

        loLScrap.comezarElRobo();
    }
}
