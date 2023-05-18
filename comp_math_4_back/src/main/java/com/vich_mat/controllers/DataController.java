package com.vich_mat.controllers;

import com.vich_mat.payload.ErrorResponse;
import com.vich_mat.payload.DotsRequest;
import com.vich_mat.services.DotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/vichka")
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class DataController {

    @Autowired
    DotsService dotsService;

    @PostMapping(value = "/calculate")
    public ResponseEntity<Object> getData(@RequestBody DotsRequest dotsRequest){
        try{
            return ResponseEntity.ok(dotsService.calculate(dotsRequest));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse("unknown exception in controller"));
        }

    }


}
