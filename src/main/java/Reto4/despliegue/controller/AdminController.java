
package Reto4.despliegue.controller;


import Reto4.despliegue.entitys.Admin;
import Reto4.despliegue.services.AdminServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Admin") 
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    @GetMapping("/all")
    public List<Admin> getAll(){
        return adminServices.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Admin> getLibrary(@PathVariable("id") int id) {
        return adminServices.getAdmin(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin save(@RequestBody Admin admin){
        return adminServices.save(admin);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin update(@RequestBody Admin admin){
        return adminServices.update(admin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return adminServices.delete(id);
    }
}