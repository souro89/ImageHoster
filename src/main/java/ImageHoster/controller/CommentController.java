package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {


    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "/image/{imageid}/{imagetitle}/comments", method = RequestMethod.POST)
    public String addComments(@PathVariable("imageid") Integer imageId, @PathVariable("imagetitle") String imageTitle, @RequestParam("comment") String comment, HttpSession session, Comment newComment) {
        User user =(User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);
        newComment.setCreatedDate(new Date());
        newComment.setImage(image);
        newComment.setUser(user);
        newComment.setText(comment);
        commentService.uploadComment(newComment);
        return "redirect:/images/"+imageId;
    }
}
