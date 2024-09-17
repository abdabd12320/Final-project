package com.example.thawaq.Controller;

import com.example.thawaq.Api.ApiResponse;
import com.example.thawaq.DTO.ClientDTO;
import com.example.thawaq.DTO.ExpertDTO;
import com.example.thawaq.DTO.StoreAdminDTO;
import com.example.thawaq.Model.User;
import com.example.thawaq.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping("/get-id/{userId}")
    public ResponseEntity getUserById(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(userService.getUserById(userId));
    }

    @PostMapping("/register-client")
    public ResponseEntity registerClient(@Valid @RequestBody ClientDTO clientDTO) {
        userService.clientRegister(clientDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Client registered successfully"));
    }

    @PostMapping("/register-expert")
    public ResponseEntity registerExpert(@Valid @RequestBody ExpertDTO expertDTO) {
        userService.expertRegister(expertDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Expert registered successfully"));
    }

    @PostMapping("/register-store")
    public ResponseEntity registerAdmin(@Valid @RequestBody StoreAdminDTO storeAdminDTO) {
        userService.storeAdminRegister(storeAdminDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Store Admin registered successfully"));
    }

    @PutMapping("/update-client")
    public ResponseEntity updateClient(@AuthenticationPrincipal User user, @Valid @RequestBody ClientDTO clientDTO) {
        userService.clientUpdate(user,clientDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Client updated successfully"));
    }

    @PutMapping("/update-expert")
    public ResponseEntity updateExpert(@AuthenticationPrincipal User user,@Valid @RequestBody ExpertDTO expertDTO) {
        userService.expertUpdate(user,expertDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Expert updated successfully"));
    }

    @PutMapping("/update-store")
    public ResponseEntity updateAdmin(@AuthenticationPrincipal User user,@Valid @RequestBody StoreAdminDTO storeAdminDTO) {
        userService.storeAdminUpdate(user,storeAdminDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Store Admin updated successfully"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity delete(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }

    ///v2   v3
    @PutMapping("/block-expert/{expertId}")
    public ResponseEntity BlockExpert(@PathVariable Integer  expertId ,@AuthenticationPrincipal User user){
        userService.BlockExpert(expertId,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Expert blocked successfully"));
    }
    ///v2   v3
    @PutMapping("/unblock-expert/{expertId}")
    public ResponseEntity UnblockExpert(@PathVariable Integer expertId,@AuthenticationPrincipal User user){
        userService.UnblockExpert(expertId,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Expert unblocked successfully"));
    }
    ///v2
    @PutMapping("/block-Store-admin/{storeAdminId}/{userId}")
    public ResponseEntity BlockStoreAdmin(@PathVariable Integer storeAdminId,@PathVariable Integer userId){
        userService.BlockStoreAdmin(storeAdminId,userId);
        return ResponseEntity.status(200).body(new ApiResponse("StoreAdmin blocked successfully"));
    }
    ///v2
    @PutMapping("/unblock-Store-admin/{storeAdminId}/{userId}")
    public ResponseEntity UnblockStoreAdmin(@PathVariable Integer storeAdminId,@PathVariable Integer userId){
        userService.UnBlockStoreAdmin(storeAdminId,userId);
        return ResponseEntity.status(200).body(new ApiResponse("Expert unblocked successfully"));
    }

    @PutMapping("/block-store/{storeId}")
    public ResponseEntity blockStore(@PathVariable Integer storeId){
        userService.blockStore(storeId);
        return ResponseEntity.status(200).body(new ApiResponse("Store blocked successfully"));
    }

    @PutMapping("/unblock-store/{storeId}")
    public ResponseEntity unblockStore(@PathVariable Integer storeId){
        userService.unBlockStore(storeId);
        return ResponseEntity.status(200).body(new ApiResponse("Store unblocked successfully"));
    }

    //Jana
    @PutMapping("/activate-storeAdmin/{storeAdminId}")
    public ResponseEntity activateStoreAdmin(@AuthenticationPrincipal User user,@PathVariable Integer storeAdminId) {
        userService.activateStoreAdmin(user.getId(),storeAdminId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully Activate Store Admin"));
    }

    //Jana
    @PutMapping("/deactivate-storeAdmin/{storeAdminId}")
    public ResponseEntity deactivateStoreAdmin(@AuthenticationPrincipal User user,@PathVariable Integer storeAdminId) {
        userService.DeactivateStoreAdmin(user.getId(),storeAdminId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully Activate Store Admin"));
    }

}
