import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;

public class Main {
    private static final Logger logger = LogManager.getLogger("mylogger");
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(
                        "config"
                );

        logger.info("Program started");
        Console console = context.getBean(Console.class);
        console.run();


    }
}
