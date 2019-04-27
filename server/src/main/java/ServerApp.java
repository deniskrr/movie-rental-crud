import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApp {
    public static void main(String[] args) {
        System.setProperty("USERNAMESQL", "postgres");
        System.setProperty("PASSWORDSQL", "2233");

        System.out.println("Server started.");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("config");

    }
}
