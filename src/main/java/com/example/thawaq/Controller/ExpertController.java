package com.example.thawaq.Controller;

import com.example.thawaq.Api.ApiResponse;
import com.example.thawaq.Model.User;
import com.example.thawaq.Service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expert")
public class ExpertController {
    private final ExpertService expertService;

    @GetMapping("/get-all")
    public ResponseEntity getAllExperts() {
        return ResponseEntity.status(200).body(expertService.getAllExperts());
    }

    @GetMapping("/get-id/{expertId}")
    public ResponseEntity getExpertById(@PathVariable Integer expertId) {
        return ResponseEntity.status(200).body(expertService.getExpertById(expertId));
    }

    @PutMapping("/approve/{requestId}")
    public ResponseEntity approve (@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        expertService.approveRequest(user.getId(), requestId);
        return ResponseEntity.status(200).body(new ApiResponse("Request approved"));
    }

    @PutMapping("/reject/{requestId}")
    public ResponseEntity reject (@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        expertService.rejectRequest(user.getId(), requestId);
        return ResponseEntity.status(200).body(new ApiResponse("Request rejected"));
    }

    @GetMapping("/top-4")
    public ResponseEntity getTop4Experts() {
        return ResponseEntity.status(200).body(expertService.findTop4Experts());
    }

}
