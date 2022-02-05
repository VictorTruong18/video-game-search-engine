package fr.lernejo.search.api;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GameInfoListener {
    private final RestHighLevelClient client;

    public GameInfoListener(RestHighLevelClient restHighLevelClient) {
        this.client = restHighLevelClient;
    }

    @RabbitListener(queues =  AmqpConfiguration.GAME_INFO_QUEUE)
    public void onMessage(String msg, @Header("game_id") String id) throws IOException {
        if (id == null) {
            return;
        }
        IndexRequest indexRequest = new IndexRequest("games");
        indexRequest.id(id);
        indexRequest.source(msg, XContentType.JSON);
        System.out.println("Message received " + msg + "\n");
        this.client.index(indexRequest, RequestOptions.DEFAULT);
    }
}
