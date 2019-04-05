package repo.xml;

import domain.Movie;
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

public class MovieXmlRepository extends XmlRepository<Movie> {
    public MovieXmlRepository(Validator<Movie> validator, String xmlFilePath) {
        super(validator, xmlFilePath);
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> optionalMovie = super.save(entity);

        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse("data/movies.xml");
            Element root = document.getDocumentElement();

            Element movieElement = document.createElement("movie");
            root.appendChild(movieElement);

            appendChildWithText(document, movieElement, "id",
                    String.valueOf(entity.getId()));
            appendChildWithText(document, movieElement, "title", entity.getTitle());
            appendChildWithText(document, movieElement, "genre", entity.getGenre());
            appendChildWithText(document, movieElement, "year",
                    String.valueOf(entity.getYear()));
            appendChildWithText(document, movieElement, "rating",
                    String.valueOf(entity.getRating()));

            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(root),
                    new StreamResult(new FileOutputStream(
                            "./data/movies.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionalMovie;
    }

    public void loadData() throws Exception{
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/movies.xml");
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        int len = nodes.getLength();
        for (int i = 0; i < len; i++) {
            Node movieNode = nodes.item(i);
            if (movieNode instanceof Element) {
                Movie movie = createMovie((Element) movieNode);
                super.save(movie);

            }
        }
    }

    private static Movie createMovie(Element movieNode) {
        Movie movie = new Movie();

        movie.setId(UUID.fromString(getTextFromTagName(movieNode, "id")));
        movie.setTitle(getTextFromTagName(movieNode, "title"));
        movie.setGenre(getTextFromTagName(movieNode, "genre"));
        movie.setRating(Double.valueOf(getTextFromTagName(movieNode, "rating")));
        movie.setYear(Integer.valueOf(getTextFromTagName(movieNode, "year")));
        return movie;
    }
}
