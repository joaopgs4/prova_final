package zambom.pf.prova_final.feedback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<Feedback> listarFeedbacks() {
        return feedbackService.listFeedback();
    }

    @GetMapping("/{id}")
    public Feedback findFeedback(@PathVariable String id) {
        return feedbackService.getFeedback(id);
    }

    @PostMapping
    public Feedback saveFeedback(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @DeleteMapping("/{id}")
    public void apagaFeedback(@PathVariable String id) {
        feedbackService.deleteFeedback(id);
    }
    
}
