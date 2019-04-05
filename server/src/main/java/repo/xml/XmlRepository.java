package repo.xml;

import domain.BaseEntity;
import domain.Validator.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import repo.InMemoryRepository;

public abstract class XmlRepository<T extends BaseEntity> extends InMemoryRepository<T> {
    protected String xmlFilePath;

    public XmlRepository(Validator<T> validator, String xmlFilePath) {
        super(validator);
        this.xmlFilePath = xmlFilePath;
    }

    static void appendChildWithText(Document document,
                                    Node parent, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    static String getTextFromTagName(Element element, String tagName) {
        NodeList elements = element.getElementsByTagName(tagName);
        Node node = elements.item(0);
        return node.getTextContent();
    }

    public abstract void loadData() throws Exception;
}
