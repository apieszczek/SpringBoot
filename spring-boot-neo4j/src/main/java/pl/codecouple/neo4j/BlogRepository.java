package pl.codecouple.neo4j;

import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by CodeCouple.pl
 */
interface BlogRepository extends GraphRepository<Blog> {
    Blog findByName(String name);
}
