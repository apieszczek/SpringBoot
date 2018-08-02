package pl.codecouple.tailable.event;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Created by CodeCouple.pl
 */
@RestController
@RequestMapping("/events")
class EventController {

    private final ReactiveEventRepository repository;

    EventController(ReactiveEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> getEvents() {
        return repository.findAllBy()
                .doOnNext(System.out::println);
    }
}
