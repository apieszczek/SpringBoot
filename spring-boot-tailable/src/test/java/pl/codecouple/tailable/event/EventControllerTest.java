package pl.codecouple.tailable.event;

import com.mongodb.reactivestreams.client.MongoCollection;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by CodeCouple.pl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

    @Autowired
    private ReactiveEventRepository repository;
    @Autowired
    ReactiveMongoOperations operations;

    @Before
    public void setUp() {

        Mono<MongoCollection<Document>> recreateCollection = operations.collectionExists(Event.class) //
                .flatMap(exists -> exists ? operations.dropCollection(Event.class) : Mono.just(exists)) //
                .then(operations.createCollection(Event.class, CollectionOptions.empty()
                        .size(4096)
                        .capped()));

        StepVerifier.create(recreateCollection).expectNextCount(1).verifyComplete();

        Flux<Event> insertAll = operations.insertAll(Flux.just(new Event(EventType.CREATED, new Date())).collectList());

        StepVerifier.create(insertAll).expectNextCount(1).verifyComplete();
    }

    @Test
    public void shouldReturnEventsFromCollection() {

        Queue<Event> events = new ConcurrentLinkedQueue<>();

        repository.findAllBy()
                .doOnNext(System.out::println)
                .doOnNext(events::add)
                .subscribe();

        StepVerifier.create(repository.save(new Event(EventType.CREATED, new Date())))
                .expectNextCount(1)
                .verifyComplete();

        StepVerifier.create(repository.save(new Event(EventType.UPDATED, new Date())))
                .expectNextCount(1)
                .verifyComplete();

        StepVerifier.create(repository.save(new Event(EventType.DELETED, new Date())))
                .expectNextCount(1)
                .verifyComplete();

        Assertions.assertThat(events).hasSize(4);
    }
}
