package pl.codecouple.neo4j;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by CodeCouple.pl
 */
@NodeEntity
@Data
@NoArgsConstructor
public class Subscriber {

    private Long id;
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Relationship(type = "LIKE")
    public Set<Blog> blogs;

    public void subscribe(Blog blog) {
        if (blogs == null) {
            blogs = new HashSet<>();
        }
        blogs.add(blog);
    }
}
