package fr.lernejo.fileinjector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) throws IOException {
        try (AbstractApplicationContext springContext = new AnnotationConfigApplicationContext(Launcher.class)) {
            if (args.length == 1) {
                File file = Paths.get(args[0]).toFile();
                Game[] games = new ObjectMapper().readValue(file, Game[].class);
                RabbitTemplate template = springContext.getBean(RabbitTemplate.class);
                for (Game game : games) {
                    template.setMessageConverter(new Jackson2JsonMessageConverter());
                    template.convertAndSend("", "game_info", game, m -> {
                        m.getMessageProperties().setHeader("game_id", game.id);
                        return m; });
                }
            }
        }
    }
}
