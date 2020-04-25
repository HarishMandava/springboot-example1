package application.test;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicsService {

    private List<Topics> topics = new ArrayList<>(Arrays.asList(
            new Topics("1", "java", "java tutorial"),
            new Topics("2", "python", "python basics"),
            new Topics("3", "javaScript", "javascript")
    ));

    public List<Topics> getTopics() {
        return topics;
    }

    public void addTopics(Topics topics) {
        this.topics.add(topics);
    }

    public Topics getTopics(String id) {
        return this.topics.stream().filter(topics -> topics.getId().equals(id)).findFirst().get();
    }

    public boolean deleteTopic(String id) {
        this.topics =  this.topics.stream().filter(topics -> !topics.getId().equals(id)).collect(Collectors.toList());
        return true;
    }
}
