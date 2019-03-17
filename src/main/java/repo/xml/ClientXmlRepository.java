package repo.xml;

import domain.Client;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import repo.InMemoryRepository;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.UUID;


public class ClientXmlRepository extends XmlRepository<Client> {
    public ClientXmlRepository(Validator<Client> validator, String xmlFilePath) {
        super(validator, xmlFilePath);
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional<Client> optionalClient = super.save(entity);

        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse("data/clients.xml");

            Element root = document.getDocumentElement();
            Element clientElement = document.createElement("client");
            root.appendChild(clientElement);
            appendChildWithText(document, clientElement, "id",
                    entity.getId().toString());
            appendChildWithText(document, clientElement, "first_name", entity.getFirstName());
            appendChildWithText(document, clientElement, "last_name", entity.getLastName());
            appendChildWithText(document, clientElement, "year",
                    String.valueOf(entity.getYearOfBirth()));

            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(root),
                    new StreamResult(new FileOutputStream(
                            "./data/clients.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionalClient;
    }

    public void loadData() throws Exception {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/clients.xml");
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        int len = nodes.getLength();
        for (int i = 0; i < len; i++) {
            Node clientNode = nodes.item(i);
            if (clientNode instanceof Element) {
                Client client = createClient((Element) clientNode);
                super.save(client);

            }
        }
    }

    private static Client createClient(Element clientNode) {
        Client client = new Client();

        client.setId(UUID.fromString(getTextFromTagName(clientNode, "id")));
        client.setFirstName(getTextFromTagName(clientNode, "first_name"));
        client.setLastName(getTextFromTagName(clientNode, "last_name"));
        client.setYearOfBirth(Integer.valueOf(getTextFromTagName(clientNode, "year")));
        return client;
    }
}
