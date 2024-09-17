package com.example.thawaq.Config;

import com.example.thawaq.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(getDaoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("api/v1/user/register-client").permitAll()
                .requestMatchers("api/v1/user/register-expert").permitAll()
                .requestMatchers("api/v1/user/register-store").permitAll()
                .requestMatchers("api/v1/user/update-client").hasAuthority("CLIENT")
                .requestMatchers("api/v1/user/update-expert").hasAuthority("EXPERT")
                .requestMatchers("api/v1/user/update-store").hasAuthority("STORE")
                .requestMatchers("api/v1/user/delete/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/user/get-all").hasAuthority("ADMIN")
                .requestMatchers("api/v1/user/get-id/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/user/block-store/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/user/unblock-store/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/client/get-all").hasAuthority("ADMIN")
                .requestMatchers("api/v1/client/get-id/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/store-admin/get-all").hasAuthority("ADMIN")
                .requestMatchers("api/v1/store-admin/get-id/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/expert/get-all").hasAuthority("ADMIN")
                .requestMatchers("api/v1/expert/get-id/**").hasAuthority("ADMIN")
                .requestMatchers("api/v1/expert/approve/**").hasAuthority("EXPERT")
                .requestMatchers("api/v1/expert/reject/**").hasAuthority("EXPERT")
                .requestMatchers("api/v1/expert/top-4").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-cafes-name/**").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-restaurant-name/**").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-both-name/**").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-cafes-category/**").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-restaurant-category/**").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-both-category/**").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-cafes-city/**").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-restaurant-city/**").permitAll()
                .requestMatchers("api/v1/store/get-best-quality-both-city/**").permitAll()
                .requestMatchers("api/v1/store/get-cafes-you-like").hasAuthority("CLIENT")
                .requestMatchers("api/v1/store/get-restaurants-you-like").hasAuthority("CLIENT")
                .requestMatchers("api/v1/store/get-cafes-you-may-like").hasAuthority("CLIENT")
                .requestMatchers("api/v1/store/get-cafes-you-may-like").hasAuthority("CLIENT")
                .requestMatchers("api/v1/branch/open/**").hasAuthority("STORE")
                .requestMatchers("api/v1/branch/close/**").hasAuthority("STORE")
                .requestMatchers("api/v1/rating/get-all").permitAll()
                .requestMatchers("api/v1/rating/get-my-client").hasAuthority("CLIENT")
                .requestMatchers("api/v1/rating/get-my-expert").hasAuthority("EXPERT")
                .requestMatchers("api/v1/rating/expert/add-rating/{storeId}").hasAuthority("EXPERT")
                .requestMatchers("api/v1/rating/client/add-rating/{storeId}").hasAuthority("CLIENT")
                .requestMatchers("api/v1/rating/update-rating/{ratingId}").hasAuthority("ADMIN")
                .requestMatchers("api/v1/rating/delete-rating/{ratingId}").hasAuthority("ADMIN")

                .requestMatchers("api/v1/rating/average-rating-store-service/{storeId}").permitAll()
                .requestMatchers("api/v1/rating//top-4-cafes").permitAll()
                .requestMatchers("api/v1/rating/top-4-restaurant").permitAll()


                //Category
                .requestMatchers("api/v1/category/apply-discount/{categoryName}/{branchId}/{discountPercentage}").hasAuthority("STORE")

                //Menu
                .requestMatchers("api/v1/menu/get-By-price-range/{priceMin}/{priceMax}").permitAll()

                //Request
                .requestMatchers("api/v1/request/get-all").hasAuthority("ADMIN")
                .requestMatchers("api/v1/request/get-my-request").hasAuthority("EXPERT")
                .requestMatchers("api/v1/request/add-request/{expertId}").hasAuthority("STORE")
                .requestMatchers("api/v1/request/update-request/{requestId}").hasAuthority("ADMIN")
                .requestMatchers("api/v1/request/delete-request/{requestId}").hasAuthority("ADMIN")

                //Store
                .requestMatchers("api/v1/store/get-typeOfActivity/{typeOfActivity}").permitAll()

                .requestMatchers("api/v1/store/get-best-service-cafes-name/{name}").permitAll()
                .requestMatchers("api//v1/store/get-best-service-restaurant-name/{name}").permitAll()
                .requestMatchers("api/v1/store/get-best-service-both-name/{name}").permitAll()

                .requestMatchers("api/v1/store/get-best-service-cafes-categoryName/{categoryName}").permitAll()
                .requestMatchers("api/v1/store/get-best-service-restaurant-categoryName/{categoryName}").permitAll()
                .requestMatchers("api/v1/store/get-best-service-both-categoryName/{categoryName}").permitAll()


                .requestMatchers("api/v1/store/get-best-service-cafe-cityName/{cityName}").permitAll()
                .requestMatchers("api/v1/store/get-best-service-restaurant-cityName/{cityName}").permitAll()
                .requestMatchers("api/v1/store/get-best-service-both-cityName/{cityName}").permitAll()

                //USER
                .requestMatchers("api/v1/user/activate-storeAdmin/{storeAdminId}").hasAuthority("ADMIN")
                .requestMatchers("api/v1/user/deactivate-storeAdmin/{storeAdminId}").hasAuthority("ADMIN")

                /* v5 */.requestMatchers("api/v1/rating/get-both-by-cleaning-of-rating","api/v1/rating/get-restaurant-by-cleaning-of-rating","api/v1/rating/get-cafe-by-cleaning-of-rating").permitAll()
                .requestMatchers("api/v1/rating/get-branch-both-by-cleaning-of-rating","api/v1/rating/get-branch-restaurant-by-cleaning-of-rating","api/v1/rating/get-branch-cafe-by-cleaning-of-rating").permitAll()
                .requestMatchers("api/v1/rating/get-menu-both-by-cleaning-of-rating","api/v1/rating/get-menu-restaurant-by-cleaning-of-rating","api/v1/rating/get-menu-cafe-by-cleaning-of-rating").permitAll()
                .requestMatchers("/api/v1/rating/get-rating-by-latest","/api/v1/rating/get-rating-by-high","/api/v1/rating/get-rating-by-low","/api/v1/rating/get-rating-by-comment/{comment}").permitAll()
                .requestMatchers("/api/v1/address/get","/api/v1/branch/get","/api/v1/store/get").permitAll()
                .requestMatchers("api/v1/address/add","api/v1/address/update","api/v1/address/delete/{id}","/api/v1/branch/get-my-branches","/api/v1/branch/add/{storeID}","/api/v1/branch/update/{branchID}","/api/v1/branch/delete/{branchID}","/api/v1/store/get-my-store","/api/v1/store/add/**","/api/v1/store/update/{id}","/api/v1/store/delete/{id}").hasAuthority("STORE")

                .requestMatchers("/api/v1/category/get", "/api/v1/category/add", "/api/v1/category/update", "/api/v1/category/delete").permitAll()
                .requestMatchers("/api/v1/menu/get", "/api/v1/menu/add", "/api/v1/menu/update", "/api/v1/menu/delete").permitAll()

                .requestMatchers("/api/v1/favorite/add", "/api/v1/favorite/delete").permitAll()
                .requestMatchers("/api/v1/rating/average-rating-store","/api/v1/rating/average-rating-expert").permitAll()

                .requestMatchers("api/v1/user/block-expert","api/v1/user/unblock-expert").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/store/get-lowes-cost-cafes-name"
                        ,"/api/v1/store/get-lowes-cost-restaurant-name","/api/v1/store/get-lowes-cost-both-name",
                        "/api/v1/store/get-lowes-cost-cafes-category","/api/v1/store/get-lowes-restaurant-cafes-category",
                        "/api/v1/store/get-lowes-restaurant-both-category","/api/v1/store/get-lowes-cost-cafes-city",
                        "/api/v1/store/get-lowes-cost-restaurant-city", "/api/v1/store/get-lowes-cost-both-city").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
