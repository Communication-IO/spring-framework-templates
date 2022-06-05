package com.ahlquist.commio.tablepagination.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ahlquist.commio.tablepagination.data.Call;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    @GetMapping({"/", "/view"})
    public String view() {
        return "index";
    }

    @GetMapping({"/getcall"})
    public ResponseEntity<Call> listAllUsers() {

        // Dummy list of values
        List<String[]> obj = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            String fname = "username-";
            String lname = "lastname-";
            String Id = "Id-";
            String email = "hooverdude@gmail.com-";
            String pin = "pin-";
            String mStringArray[] = {fname + i, lname + i, Id + i, 
                                        email + i, pin + i};
            obj.add(mStringArray);

        }
        Call c = new Call();
        c.setData(obj);
        return new ResponseEntity<>(c, HttpStatus.OK);

    }
} 
