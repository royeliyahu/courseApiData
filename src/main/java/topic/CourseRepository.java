package topic;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String>{
    public List<Course> findByName(String courseName);
    public List<Course> findByDescription(String description);
    public List<Course> findByTopicId(String topicId);

}
