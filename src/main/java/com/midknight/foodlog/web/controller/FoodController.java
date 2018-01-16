package com.midknight.foodlog.web.controller;

import com.midknight.foodlog.model.User;
import com.midknight.foodlog.service.FoodService;
import com.midknight.foodlog.model.Food;
import com.midknight.foodlog.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Onlyme on 9/26/2017.
 */

@Controller
//@ComponentScan("com.midknight.foodlog.service")
public class FoodController {
    @Autowired
    private FoodService foodService;

//    @RequestMapping(value = "/")
//    public String listFood(Model model) {
//        List<Food> foods =foodService.findAll();
//        model.addAttribute("foods",foods);
//        return "index";
//    }

    @RequestMapping(value = "/")
    public String listFood(Model model, Principal principal) {
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        List<Food> foods =foodService.findByUserId(user.getId());
        model.addAttribute("foods",foods);
        return "index";
    }

    @RequestMapping(value = "/food")
    public String foodDetails(ModelMap modelMap){

        return "food-details";
    }

    //Food image data
    @RequestMapping("/foods/{foodId}.jpg")
    @ResponseBody
    public byte[] foodImage(@PathVariable Long foodId){
        return foodService.findById(foodId).getBytes();
    }

    // Upload a new food
    @RequestMapping(value = "/foods", method = RequestMethod.POST)
    public String addFood(Food food, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes, Principal principal) {

        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        food.setUserID(user.getId());
        food.setUsername(user.getUsername());
        //Upload new Food if data is valid
        foodService.save(food,file);

        //Add flash msg for success
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Food successfully uploaded!", FlashMessage.Status.SUCCESS));

        //Redirect browser to new food's detail view
        return String.format("redirect:/foods/%s",food.getId());
    }

    // Form for uploading a new food
    @RequestMapping("/upload")
    public String formNewfood(Model model) {
        //Add model attributes needed for new Food upload form
        model.addAttribute("food",new Food());
        model.addAttribute("categories",foodService.findAll());
        model.addAttribute("action","/foods");
        model.addAttribute("heading","Upload");
        model.addAttribute("submit","Upload");
        return "form";
    }

    // Single Food page
    @RequestMapping("/foods/{foodId}")
    public String foodDetails(@PathVariable Long foodId, Model model) {
        //Get food whose id is foodId
        Food food = foodService.findById(foodId);

        model.addAttribute("food", food);
        return "details";
    }

    // Delete an existing Food
    @RequestMapping(value = "/foods/{foodId}/delete", method = RequestMethod.POST)
    public String deletefood(@PathVariable Long foodId, RedirectAttributes redirectAttributes) {
        //Delete the food whose id is foodId
        Food food = foodService.findById(foodId);
        foodService.delete(food);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("deleted", FlashMessage.Status.SUCCESS));
        //Redirect to app root
        return "redirect:/home";
    }

    // Form for editing an existing food
    @RequestMapping(value = "/foods/{foodId}/edit")
    public String formEditFood(@PathVariable Long foodId, Model model) {
        //Add model attributes needed for edit form
        if(!model.containsAttribute("food")) {
            model.addAttribute("food", foodService.findById(foodId));
        }
        model.addAttribute("categories",foodService.findAll());
        model.addAttribute("action",String.format("/foods/%s",foodId));
        model.addAttribute("heading","Update");
        model.addAttribute("submit","Update");
        return "form";
    }

    // Update an existing food
    @RequestMapping(value = "/foods/{foodId}", method = RequestMethod.POST)
    public String updatefood(Food food, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        //Update food if data is valid
        foodService.save(food,file);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("food successfully uploaded!", FlashMessage.Status.SUCCESS));
        //Redirect browser to updated food's detail view
        return String.format("redirect:/foods/%s", food.getId());
    }

    // Like/unlike an existing food
    @RequestMapping(value="foods/{foodId}/like")
    public String toggleLike(@PathVariable Long foodId, HttpServletRequest request){
        Food food = foodService.findById(foodId);
        foodService.toggleLike(food);
        //HttpServletRequest allows redirecting back to where the request was sent from
        return String.format("redirect:%s",request.getHeader("referer"));
    }
}
