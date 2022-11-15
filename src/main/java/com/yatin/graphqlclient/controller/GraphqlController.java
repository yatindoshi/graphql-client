package com.yatin.graphqlclient.controller;

import com.yatin.graphqlclient.domain.GraphqlRequestBody;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;


@RestController
public class GraphqlController {


    @GetMapping(path = "authors")
    public Mono<Object> getAuthors() {
        var webClient = WebClient.builder().build();
        HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(webClient).build();
        GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();
        final String query = """
                    query {
                    recentPosts(count: 10, offset: 0) {
                        id
                        title
                        category
                        author {
                            id
                            name
                            thumbnail
                        }
                    }
                }""";
        graphQLRequestBody.setQuery(query);
        return webClient.post()
                .uri(URI.create("http://localhost:8080/graphql"))
                .bodyValue(graphQLRequestBody)
                .retrieve()
                .bodyToMono(Object.class);

    }

    @GetMapping(path = "posts/{author}")
    public Mono<Object> postsFromAuthor(@PathVariable("author") String author) {
        var webClient = WebClient.builder().build();
        HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(webClient).build();
        GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();
        final String query = """
                query($author: String) {
                    postsFromAuthor(authorId: $author) {
                       id
                       text
                        title
                        category
                        author {
                            id
                            name
                            thumbnail
                        }
                    }
                }""";
        graphQLRequestBody.setQuery(query);
        graphQLRequestBody.setVariables(Map.of("author", author));
        return webClient.post()
                .uri(URI.create("http://localhost:8080/graphql"))
                .bodyValue(graphQLRequestBody)
                .retrieve()
                .bodyToMono(Object.class);
    }
}
