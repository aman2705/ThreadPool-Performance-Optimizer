package com.demo.asyncmicroservice.controller;

import com.demo.asyncmicroservice.domain.Customer;
import com.demo.asyncmicroservice.domain.FileData;
import com.demo.asyncmicroservice.service.CustomerService;
import com.demo.asyncmicroservice.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/blocking")
public class BlockingController {

    private final CustomerService customerService;
    private final FileService fileService;

    @GetMapping("/customers/{name}")
    public List<Customer> getCustomerByName(@PathVariable String name) {
        log.info("Getting customer by name {} ", name);
        List<Customer> customerList = customerService.getCustomerByName(name);
        log.info("Received {} customers by name {}", customerList.size(), name);
        return customerList;
    }

    @PostMapping("/customers/save")
    public Customer addCustomer(@RequestBody Customer customer) {
        log.info("Adding user {} to the Database", customer.getName());
        return customerService.addCustomer(customer);
    }

    @GetMapping("/fileread")
    public String readFile() {
        log.info("reading file request");
        return fileService.readFile();
    }

    @PostMapping("/filewrite")
    public boolean writeFile(@RequestBody FileData fileData) {
        log.info("Write data {} to File", fileData);
        return fileService.writeFile(fileData);
    }

   /* public CompletableFuture<String> readFile() {
        log.info("reading file request");
        return asyncService.readFile();

    }*/

   /* @PostMapping("/filewrite")
    public CompletableFuture<Boolean> writeFile(@RequestBody com.levi.microservicedemo.domain.FileData fileData) {
        log.info("Write data {} to File", fileData);
        return asyncService.writeFile(fileData);
    }*/

}
