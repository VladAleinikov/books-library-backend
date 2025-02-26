package org.example.bookslibrary.controller;

import org.example.bookslibrary.model.User.User;
import org.example.bookslibrary.security.user.AuthUser;
import org.example.bookslibrary.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers(){
        System.out.println("something");
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        Optional<User> user = userService.getUser(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
