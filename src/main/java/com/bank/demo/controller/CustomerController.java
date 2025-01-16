package com.bank.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bank.demo.service.CustomerService;
import com.bank.demo.response.CustomerResponse;

@RestController
public class CustomerController {


        @Autowired
        private CustomerService customerService;
       
        @GetMapping("/customer")
        @ResponseBody
        public CustomerResponse getCustomerByDobAndSsn(@RequestParam("dob") String dob, @RequestParam("ssn") String ssn) {
            return customerService.findCustomerByDobAndSsn(dob, ssn);
        }
    }
    
