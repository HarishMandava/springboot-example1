package application.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


@Service
public class TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;

    public List<Topics> getTopics() {
        List<Topics> list = new ArrayList<>();
        topicsRepository.findAll().forEach(list::add);
        return list;
    }

    public void addTopics(Topics topics) {
        topicsRepository.save(topics);
    }

    public Topics getTopics(String id) {
        return topicsRepository.findById(id).get();
    }

    public boolean deleteTopic(String id) {
        topicsRepository.deleteById(id);
        return true;
    }

    public void updateTopic(String id, Topics topics){
        topicsRepository.save(topics);
    }
}
