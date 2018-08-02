package pl.codecouple.tailable.event;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * Created by CodeCouple.pl
 */
interface ReactiveEventRepository extends ReactiveMongoRepository<Event, String> {

    @Tailable
    Flux<Event> findAllBy();

    @Tailable
    Flux<Event> findAllByEventType(EventType eventType);
}
