package xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class JDOMExample {

  public static void main(String[] args) {
    try {
      // 1. Erstellen einer XML-Datei

      // 1.1. Erstellen des Wurzelelements "books"
      Element booksRootElement = new Element("books");

      // 1.2. Erstellen eines neuen Dokuments mit dem Wurzelelement
      Document document = new Document(booksRootElement);

      // 1.3. Erstellen eines neuen Elements "book" und Hinzufügen von Attributen und
      // Inhalt
      Element bookElement = new Element("book");
      bookElement.setAttribute("id", "1");
      bookElement.addContent(new Element("title").setText("Java für Anfänger"));
      bookElement.addContent(new Element("author").setText("Max Mustermann"));
      bookElement.addContent(new Element("price").setText("19.99"));

      // 1.4. Hinzufügen des "book"-Elements zum Wurzelelement "books"
      booksRootElement.addContent(bookElement);

      // 1.5. Erstellen eines XMLOutputters und Formatierung der XML-Ausgabe
      XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

      // 1.6. Schreiben der XML-Datei mit dem XMLOutputter
      xmlOutputter.output(document, new FileWriter(new File("books.xml")));

      System.out.println("XML-Datei erstellt");

      // 2. Lesen der XML-Datei

      // 2.1. Erstellen eines SAXBuilders, um die XML-Datei zu verarbeiten
      SAXBuilder saxBuilder = new SAXBuilder();

      // 2.2. Verwenden des SAXBuilders, um die XML-Datei in ein JDOM-Dokument zu
      // laden
      Document xmlDoc = saxBuilder.build(new File("books.xml"));

      // 2.3. Zugriff auf das Wurzelelement des JDOM-Dokuments
      Element rootElement = xmlDoc.getRootElement();

      // 2.4. Erhalten einer Liste aller "book"-Elemente unterhalb des Wurzelelements
      List<Element> bookElements = rootElement.getChildren("book");

      // 2.5. Durchlaufen der Liste der "book"-Elemente und Extrahieren der Daten
      for (Element book : bookElements) {
        String id = book.getAttributeValue("id");
        String title = book.getChildText("title");
        String author = book.getChildText("author");
        String price = book.getChildText("price");

        // 2.6. Ausgeben der extrahierten Informationen auf der Konsole
        System.out.println("\nBuch ID: " + id);
        System.out.println("Titel: " + title);
        System.out.println("Autor: " + author);
        System.out.println("Preis: " + price);
      }
    } catch (IOException | JDOMException e) {
      e.printStackTrace();
    }
  }
}
