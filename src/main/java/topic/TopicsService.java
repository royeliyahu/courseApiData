package topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicsService {

    @Autowired
    private TopicRepository topicRepository;

    private  List<Topic> topics = new ArrayList<>(Arrays.asList(new Topic("spring", "spring framework" , "spring framework description"),
            new Topic("java", "core java" , "core java description"),
            new Topic("spring boot", "spring boot framework" , "spring boot framework description"),
            new Topic()));

    public List<Topic> getAllTopics() {

        //return topics;
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Topic getTopic(String topicId) {
//        return topics.stream().filter(t -> topicId.equals(t.getId())).findFirst().get();
        return topicRepository.findOne(topicId);
    }

    public void addTopic(Topic newTopic) {

//        topics.add(newTopic);
        topicRepository.save(newTopic);
    }

    public void updateTopic(Topic topic, String topicId) {
        topicRepository.save(topic);
    }

    public void removeTopic(Topic topic, String topicId) {

//        topics.removeIf(t -> t.getId().equals(topicId));
        topicRepository.delete(topicId);
    }
}
