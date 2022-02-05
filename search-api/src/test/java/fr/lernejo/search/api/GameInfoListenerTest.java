package fr.lernejo.search.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lernejo.search.api.GameInfoListener;
import fr.lernejo.search.api.Launcher;
import org.springframework.amqp.core.Message;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class GameInfoListenerTest {

    @Test
    void test_listener_sucess(){
        try (AbstractApplicationContext springContext = new AnnotationConfigApplicationContext(Launcher.class)) {
            RabbitTemplate rabbitTemplate = springContext.getBean(RabbitTemplate.class);
            rabbitTemplate.convertAndSend("", "game_info", "{'test' : 'working' }", m -> {
                m.getMessageProperties().getHeaders().put("game_id", "1");
                return m;
            });
        }
    }

    @Test
    void test_listener_missing_id(){
        try (AbstractApplicationContext springContext = new AnnotationConfigApplicationContext(Launcher.class)) {
            RabbitTemplate rabbitTemplate = springContext.getBean(RabbitTemplate.class);
            rabbitTemplate.setRoutingKey("game_info");
            rabbitTemplate.convertAndSend("", "game_info", "{'test' : 'working' }", m -> {
                return m;
            });
        }
    }
}
