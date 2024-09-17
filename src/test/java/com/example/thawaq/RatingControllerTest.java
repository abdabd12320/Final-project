package com.example.thawaq;



import com.example.thawaq.Api.ApiResponse;
import com.example.thawaq.Controller.RatingController;
import com.example.thawaq.Model.Expert;
import com.example.thawaq.Model.Rating;
import com.example.thawaq.Model.User;
import com.example.thawaq.Service.RatingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RatingController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class RatingControllerTest {

    @MockBean
    RatingService ratingService;

    @Autowired
    MockMvc mockMvc;

    Rating rating1,rating2;
    User user;
    Expert expert;


    ApiResponse apiResponse;

    List<Rating> ratings;


    @BeforeEach
    void setUp()
    {
        user = new User(null,"Aliq","ASqw123...","STORE","Sami","Al-QQQ","qwwert@gmail.com","0543212345","UK","London",null,null,null);

        expert = new Expert(null,"asa","@dd","@qrty",false,user,null,null);

        apiResponse = new ApiResponse("ApiResponse");

        rating1 = new Rating(null,3,4,2,3,"sssssq","rerew",3,null,expert,null);
        rating2 = new Rating(null,3,4,2,3,"sssssq","rerew",3,null,expert,null);

        ratings = new ArrayList<>();

        ratings.add(rating1);
        ratings.add(rating2);
    }

    @Test
    public void getAllRatings() throws Exception {
        Mockito.when(ratingService.getAllRatings()).thenReturn(ratings);
        mockMvc.perform(get("/api/v1/rating/get-all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("sssssq"));
    }
    @Test
    public void getRatingByLatest() throws Exception {
        Mockito.when(ratingService.getRatingByLatest()).thenReturn(ratings);
        mockMvc.perform(get("/api/v1/rating/get-rating-by-latest"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("sssssq"));
    }
    @Test
    public void getRatingByHigh() throws Exception {
        Mockito.when(ratingService.getRatingByHigh()).thenReturn(ratings);
        mockMvc.perform(get("/api/v1/rating/get-rating-by-high"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("sssssq"));
    }
    @Test
    public void getRatingByLow() throws Exception {
        Mockito.when(ratingService.getRatingByLow()).thenReturn(ratings);
        mockMvc.perform(get("/api/v1/rating/get-rating-by-low"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("sssssq"));
    }
}
