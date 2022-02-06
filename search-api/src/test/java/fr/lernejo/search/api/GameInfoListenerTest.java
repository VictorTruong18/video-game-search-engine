package fr.lernejo.search.api;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

class GameInfoListenerTest {

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
}
