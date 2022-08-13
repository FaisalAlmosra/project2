package com.example.project2;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/pro")
public class ProductController {
    private ArrayList<Product> productsList=new ArrayList<>();

    @GetMapping
    public ResponseEntity getproduct(){
        return ResponseEntity.status(200).body(productsList);
    }

    @PostMapping
    public ResponseEntity addproduct(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        productsList.add(product);
        return ResponseEntity.status(201).body( new ApiResponse("New product added !",201));
    }

    @PutMapping("{index}")
    public ResponseEntity updateproduct(@RequestBody @Valid Product Product
            ,@PathVariable int index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(index>=productsList.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        productsList.set(index,Product);
        return ResponseEntity.status(201).body( new ApiResponse("product updated !",201));
    }

    @DeleteMapping("{index}")
    public ResponseEntity deleteproduct(@PathVariable int index){
        if(index>=productsList.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        productsList.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("product deleted !",200));
    }




    }


