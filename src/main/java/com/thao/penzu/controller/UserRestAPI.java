package com.thao.penzu.controller;

import com.thao.penzu.message.request.SearchUserByName;
import com.thao.penzu.model.Diary;
import com.thao.penzu.model.User;
import com.thao.penzu.service.IDiaryService;
import com.thao.penzu.service.IUserService;
import com.thao.penzu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class UserRestAPI {
    @Autowired
    private IUserService userService;
    @Autowired
    private IDiaryService diaryService;

    @GetMapping("/user")
    public ResponseEntity<?> getListAllUser() {
        List<User> users = (List<User>) userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}/diary")
    public ResponseEntity<?> getAllDiaryByUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByUserId(id);
        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @PostMapping("user/search-by-name")
    public ResponseEntity<?> getListUserByName(@RequestBody SearchUserByName searchForm) {
        if (searchForm.getName() == "" || searchForm.getName() == null) {
            List<User> users = (List<User>) userService.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        List<User> users = (List<User>) userService.findUsersByNameContaining(searchForm.getName());
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
