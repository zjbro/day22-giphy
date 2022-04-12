package vttp2022.day22.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.day22.service.GiphyService;

@Controller
@RequestMapping("/")
public class GiphyController {

    @Autowired
    private GiphyService gService;

    @GetMapping("/search")
    public String giphySearch(
        @RequestParam String q,
        @RequestParam String rating,
        @RequestParam Integer limit,
        Model model
    ){
        List<String> imageUrls = gService.getGiphs(q, rating, limit);
        model.addAttribute("imageUrls", imageUrls);
        return "showResults";


    }
    
}
