package com.example.project2;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


    @RestController
    @RequestMapping("/api/v1/cat")
    public class CategoryController {
        private ArrayList<Category> categorysList=new ArrayList<>();

        @GetMapping
        public ResponseEntity getcategory(){
            return ResponseEntity.status(200).body(categorysList);
        }

        @PostMapping
        public ResponseEntity addcategory(@RequestBody @Valid Category category, Errors errors){
            if(errors.hasErrors()){
                String message=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message,400));
            }
            categorysList.add(category);
            return ResponseEntity.status(201).body( new ApiResponse("New category added !",201));
        }

        @PutMapping("{index}")
        public ResponseEntity updatecategory(@RequestBody @Valid Category category
                ,@PathVariable int index,Errors errors){
            if(errors.hasErrors()){
                String message=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message,400));
            }
            if(index>=categorysList.size()){
                return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
            }
            categorysList.set(index,category);
            return ResponseEntity.status(201).body( new ApiResponse("category updated !",201));
        }

        @DeleteMapping("{index}")
        public ResponseEntity deletecategory(@PathVariable int index){
            if(index>=categorysList.size()){
                return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
            }
            categorysList.remove(index);
            return ResponseEntity.status(200).body(new ApiResponse("category deleted !",200));
        }
}
