package application.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/portal")
public class SampleController {

    @Autowired
    private TopicsService topicsService;

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
}
