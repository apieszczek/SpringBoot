package pl.codecouple.neo4j;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by CodeCouple.pl
 */
@NodeEntity
@Data
@NoArgsConstructor
public class Blog {

    @GraphId
    private Long blogId;
    private String name;

    public Blog(String name) {
        this.name = name;
    }
}
