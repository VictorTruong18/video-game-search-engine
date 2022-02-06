package fr.lernejo.search.api;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class ElasticSearchController {
    private final RestHighLevelClient cli;

    ElasticSearchController(RestHighLevelClient cli) {
        this.cli = cli;
    }

    @GetMapping("/api/games")
    ArrayList<Object> getGames(@RequestParam(name = "query") String query) throws IOException {
        ArrayList gamesList = new ArrayList();
        SearchRequest searchRequest = new SearchRequest().source(
            SearchSourceBuilder.searchSource().query(new QueryStringQueryBuilder(query)));
        SearchResponse searchResponse = this.cli.search(searchRequest, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(hit -> gamesList.add(hit.getSourceAsMap()));
        return gamesList;
    }
}
