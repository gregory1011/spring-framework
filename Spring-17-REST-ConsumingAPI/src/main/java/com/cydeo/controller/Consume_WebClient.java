package com.cydeo.controller;


import com.cydeo.entity.Genre;
import com.cydeo.entity.MovieCinema;
import com.cydeo.repository.GenreRepository;
import com.cydeo.repository.MovieCinemaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class Consume_WebClient {

    // consuming api with WebClient
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8085").build();


    private final MovieCinemaRepository movieCinemaRepo;
    private final GenreRepository genreRepo;


    @GetMapping("/flux-movie-cinemas")
    public Flux<MovieCinema> realAllCinemaFlux(){

        return Flux.fromIterable(movieCinemaRepo.findAll());
    }

    @GetMapping("mono-movie-cinemas/{id}")
    public Mono<MovieCinema> getMovieById(@PathVariable("id") Long id){
        return Mono.just(movieCinemaRepo.findById(id).get());
    }

    @GetMapping("mon-movie-cinemas/{id}")
    public ResponseEntity<MovieCinema> readMovieById(@PathVariable("id") Long id){
        return ResponseEntity.ok(movieCinemaRepo.findById(id).get());
    }

    @PostMapping("/create-genre")
    public Mono<Genre> createGenre(@RequestBody Genre genre){
        Genre created = genreRepo.save(genre);
        return Mono.just(created);
    }

    @DeleteMapping("/delete/genre/{id}")
    public Mono<Void> deleteGenre(@PathVariable("id") Long id){

        genreRepo.deleteById(id);
        return Mono.empty();
    }


    // ___-------_____----____- WebClient _______--------___-----___---____--____-//

    // consuming api with WebClient


    // localhost:8085/flux
    @GetMapping("/flux")
    public Flux<MovieCinema> readWithWebClient(){

        return webClient
                .get()
                // get me this uri: localhost:8085/flux-movie-cinema
                .uri("/flux-movie-cinemas")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(MovieCinema.class);
    }


    @GetMapping("/mono/{id}")
    public Mono<MovieCinema> readMonoWithWebClient(@PathVariable("id") Long idObject){

        return webClient
                .get()
                .uri("/mono-movie-cinema/{id}", idObject)
                .retrieve()
                .bodyToMono(MovieCinema.class);
    }




}
