package org.example.bookslibrary.modules.User.controller;

import org.example.bookslibrary.modules.User.dto.user.UserResponse;
import org.example.bookslibrary.modules.User.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponse> getUsers(@PageableDefault Pageable pageable, @RequestParam(defaultValue = "") String searchQuery){
        return userService.getUsers(pageable, searchQuery);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/count")
    public long countUsers(){
        return userService.countUsers();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String  userId){
        userService.deleteUser(userId);
    }
}
