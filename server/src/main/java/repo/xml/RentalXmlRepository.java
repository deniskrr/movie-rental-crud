package repo.xml;

import domain.Rental;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.UUID;

public class RentalXmlRepository extends XmlRepository<Rental> {
    public RentalXmlRepository(Validator<Rental> validator, String xmlFilePath) {
        super(validator, xmlFilePath);
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Rental createRental(Element rentalNode) {
        Rental rental = new Rental();

        rental.setId(UUID.fromString(getTextFromTagName(rentalNode, "id")));
        rental.setClientID(UUID.fromString(getTextFromTagName(rentalNode, "client_id")));
        rental.setMovieID(UUID.fromString(getTextFromTagName(rentalNode, "movie_id")));
        return rental;
    }

    @Override
    public Optional<Rental> save(Rental entity) throws ValidatorException {
        Optional<Rental> optionalRental = super.save(entity);

        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse("data/rentals.xml");
            Element root = document.getDocumentElement();

            Element rentalElement = document.createElement("rental");
            root.appendChild(rentalElement);

            appendChildWithText(document, rentalElement, "id",
                    String.valueOf(entity.getId()));
            appendChildWithText(document, rentalElement, "client_id",
                    String.valueOf(entity.getClientID()));
            appendChildWithText(document, rentalElement, "movie_id",
                    String.valueOf(entity.getMovieID()));


            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(root),
                    new StreamResult(new FileOutputStream(
                            "./data/rentals.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionalRental;
    }

    public void loadData() throws Exception {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/rentals.xml");
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        int len = nodes.getLength();
        for (int i = 0; i < len; i++) {
            Node rentalNode = nodes.item(i);
            if (rentalNode instanceof Element) {
                Rental rental = createRental((Element) rentalNode);
                super.save(rental);

            }
        }
    }
}
