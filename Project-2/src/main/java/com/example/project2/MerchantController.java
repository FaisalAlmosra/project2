package com.example.project2;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/mer")
public class MerchantController {
        private ArrayList<Merchant> merchantsList=new ArrayList<>();

        @GetMapping
        public ResponseEntity getmerchant(){
            return ResponseEntity.status(200).body(merchantsList);
        }

        @PostMapping
        public ResponseEntity addmerchant(@RequestBody @Valid Merchant merchant, Errors errors){
            if(errors.hasErrors()){
                String message=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message,400));
            }
            merchantsList.add(merchant);
            return ResponseEntity.status(201).body( new ApiResponse("New merchant added !",201));
        }

        @PutMapping("{index}")
        public ResponseEntity updatemerchant(@RequestBody @Valid Merchant merchant
                ,@PathVariable int index,Errors errors){
            if(errors.hasErrors()){
                String message=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message,400));
            }
            if(index>=merchantsList.size()){
                return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
            }
            merchantsList.set(index,merchant);
            return ResponseEntity.status(201).body( new ApiResponse("merchant updated !",201));
        }

        @DeleteMapping("{index}")
        public ResponseEntity deletemerchant(@PathVariable int index){
            if(index>=merchantsList.size()){
                return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
            }
            merchantsList.remove(index);
            return ResponseEntity.status(200).body(new ApiResponse("merchant deleted !",200));
        }
    }

