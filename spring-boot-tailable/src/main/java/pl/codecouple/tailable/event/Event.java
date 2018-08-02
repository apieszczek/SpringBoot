package pl.codecouple.tailable.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by CodeCouple.pl
 */
@Data
@RequiredArgsConstructor
@Document
class Event {

    @Id
    private String id;
    private final EventType eventType;
    private final Date date;
}
