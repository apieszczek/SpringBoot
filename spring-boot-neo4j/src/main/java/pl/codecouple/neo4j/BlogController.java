package pl.codecouple.neo4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by CodeCouple.pl
 */
@RestController
class BlogController {

    private final BlogRepository blogRepository;
    private final SubscriberRepository subscriberRepository;

    public BlogController(BlogRepository blogRepository, SubscriberRepository subscriberRepository) {
        this.blogRepository = blogRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @DeleteMapping("/")
    void deleteAll() {
        blogRepository.deleteAll();
        subscriberRepository.deleteAll();
    }

    @PostMapping("/blogs")
    void addBlog() {
        Blog blog = new Blog("CodeCouple");
        blogRepository.save(blog);
    }

    @PostMapping("/subscribers")
    void addSubcribers() {
        Subscriber aga = new Subscriber("Aga");
        Subscriber krzys = new Subscriber("Krzys");
        List<Subscriber> subscribers = Arrays.asList(aga, krzys);
        Blog blog = blogRepository.findByName("CodeCouple");
        aga.subscribe(blog);
        krzys.subscribe(blog);
        subscriberRepository.save(subscribers);
    }

    @GetMapping("/subscribers")
    Subscriber getSubscriber() {
        return subscriberRepository.findByName("Aga");
    }
}
