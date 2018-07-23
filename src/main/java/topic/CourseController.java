package topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/topics/{id}/courses")
    public List<Course> getAllCourse(@PathVariable String id){
        return courseService.getAllCourses(id);
    }

    @RequestMapping("/topics/{topicId}/courses/{courseId}")
    public Course getCourse(@PathVariable String id){
        return courseService.getCourse(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
    public void addCourse(@RequestBody Course course, @RequestBody String topicId){
        course.setTopic(new Topic(topicId, "", ""));
        courseService.addCourse(course);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{courseId}")
    public void updateCourse(@RequestBody Course course, @PathVariable String topicId,
                             @PathVariable String courseId){
        course.setTopic(new Topic(topicId, "", ""));
        courseService.updateCourse(course);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{courseId}")
    public void removeCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
    }


    @RequestMapping(value="/statistics", method= RequestMethod.POST)
    public String userStatisticsScreen(@ModelAttribute Topic user, Model model) {

        if(! users.contains(user)){
            return "noSuchUser";
        }

        user = users.get(users.indexOf(user));

        List<User> userList = new ArrayList<>();
        userList.add(user);

        User u = new User();
        u.setUserName("bro");
        u.setFirstHashtag("ff");
        u.setSecondHashtag("22");
        u.setThirdHashtag("cc");
        userList.add(u);

        model.addAttribute("user", userList);

//        model.addAttribute("user", user);
        model.addAttribute("message", "my data");

        List<MyTweet> tweetList = user.getLast10Twits().stream().collect(Collectors.toList());
        model.addAttribute("last10Twits", tweetList);

        List<TweetCount> tweetCountList =  user.getHashTagTweetCount().entrySet().stream().map(
                data -> new TweetCount(data.getValue(), data.getKey())).collect(Collectors.toList());
        model.addAttribute("hashTagTweetCount", tweetCountList);

        String info = String.format("User display: userName = %s, firsHashtag = %s, secondHashtag = %s, thirdHashtag = %s",
                user.getUserName(), user.getFirstHashtag(), user.getSecondHashtag(), user.getThirdHashtag());
        log.info(info);


        return "statisticsUser";
    }
}
