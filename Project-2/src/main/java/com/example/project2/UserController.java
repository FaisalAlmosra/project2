package com.example.project2;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/use")
public class UserController {
    private ArrayList<User> usersList=new ArrayList<>();

    @GetMapping
    public ResponseEntity getuser(){
        return ResponseEntity.status(200).body(usersList);
    }

    @PostMapping
    public ResponseEntity adduser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        usersList.add(user);
        return ResponseEntity.status(201).body( new ApiResponse("New user added !",201));
    }

    @PutMapping("{index}")
    public ResponseEntity updateuser(@RequestBody @Valid User user
            , @PathVariable int index, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(index>=usersList.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        usersList.set(index,user);
        return ResponseEntity.status(201).body( new ApiResponse("user updated !",201));
    }

    @DeleteMapping("{index}")
    public ResponseEntity deleteuser(@PathVariable int index){
        if(index>=usersList.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        usersList.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted !",200));
    }
}


