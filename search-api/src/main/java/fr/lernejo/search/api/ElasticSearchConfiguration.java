package fr.lernejo.search.api;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

//source : https://www.youtube.com/watch?v=GW7N4LH0e44
@Configuration
public class ElasticSearchConfiguration {
    @Bean
    public RestHighLevelClient client (
        @Value("${elasticsearch.user:elastic}") String user,
        @Value("${elasticsearch.password:admin}") String password,
        @Value("${elasticsearch.host:localhost}") String address,
        @Value("${elasticsearch.port:9200}") int port){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
            AuthScope.ANY,
            new UsernamePasswordCredentials(user, password));
        RestClientBuilder builder = RestClient.builder(new HttpHost(address, port))
            .setHttpClientConfigCallback(
                b -> b.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);
    }
}
