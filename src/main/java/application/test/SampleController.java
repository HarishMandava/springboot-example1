package application.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/portal")
public class SampleController {

    private final TopicsService topicsService;
    @Value("${localHost:http://localhost:8081/portal/topics}")
    private String localHostUrl;

    public SampleController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("hi") == null){
            session.setAttribute("hi","hi");
        }
        return (String) session.getAttribute("hi");
    }

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public List<Topics> getTopics(){
        return topicsService.getTopics();
    }

    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public void addTopic(@RequestBody Topics topics){
        topicsService.addTopics(topics);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.GET)
    public Topics getTopicsById(@PathVariable String id){
        return topicsService.getTopics(id);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.DELETE)
    public boolean deleteTopics(@PathVariable String id, HttpServletRequest servletRequest){
        HttpSession session = servletRequest.getSession();
        return topicsService.deleteTopic(id);
    }

    @RequestMapping(value = "/topicsDuplicate", method = RequestMethod.GET)
    public List<Topics> getTopicsDuplicate(){
        ResponseEntity<Topics[]> responseEntity =  new RestTemplate().getForEntity(localHostUrl,Topics[].class);
        Topics[] list = responseEntity.getBody();
        assert list != null;
        return Arrays.asList(list);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.PUT)
    public void updateTopics(@RequestBody Topics topics,@PathVariable String id){
        topicsService.updateTopic(id,topics);
    }
}
