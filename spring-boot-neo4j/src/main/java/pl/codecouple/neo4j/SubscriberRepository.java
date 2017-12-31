package pl.codecouple.neo4j;

import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by CodeCouple.pl
 */
interface SubscriberRepository extends GraphRepository<Subscriber> {
    Subscriber findByName(String name);
}
