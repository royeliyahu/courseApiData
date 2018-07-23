package topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

//    private  List<Course> courses = new ArrayList<>(Arrays.asList(new Course("spring", "spring framework" , "spring framework description"),
//                new Course("java", "core java" , "core java description"),
//                new Course("spring boot", "spring boot framework" , "spring boot framework description"),
//                new Course()));

    public List<Course> getAllCourses(String topicId) {

        //return courses;
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId).forEach(courses::add);
        return courses;
    }

    public Course getCourse(String courseId) {
//        return courses.stream().filter(t -> topicId.equals(t.getId())).findFirst().get();
        return courseRepository.findOne(courseId);
    }

    public void addCourse(Course newCourse) {

//        courses.add(newCourse);
        courseRepository.save(newCourse);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    public void removeCourse(String courseId) {

//        courses.removeIf(t -> t.getId().equals(topicId));
        courseRepository.delete(courseId);
    }
}
