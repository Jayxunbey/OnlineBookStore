package uz.pdp.online.control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.online.dao.UserDao;
import uz.pdp.online.dao.impls.RatingDaoImpls;
import uz.pdp.online.entity.Rating;
import uz.pdp.online.entity.User;
import uz.pdp.online.reqobj.ReqRatingObjBody;

@Controller
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingDaoImpls ratingDao;
    private final UserDao userDao;

    @PostMapping
    private String rating(@ModelAttribute ReqRatingObjBody ratingObjBody){

        System.out.println("ratingObjBody = " + ratingObjBody);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedName = authentication.getName();

        User user = userDao.getByUserName(loggedName);

        if (ratingDao.hasRatingForBook(user, ratingObjBody.getBookId())) {
            ratingDao.update(Rating
                    .builder()
                    .rate(ratingObjBody.getStar())
                    .bookId(ratingObjBody.getBookId())
                    .userId(user.getId().intValue())
                    .build());
        }
        else {

            ratingDao.save(Rating
                    .builder()
                    .rate(ratingObjBody.getStar())
                    .bookId(ratingObjBody.getBookId())
                    .userId(user.getId().intValue())
                    .build());
        }

        return "redirect:/books/download/book?id="+ratingObjBody.getBookId();
    }





}
