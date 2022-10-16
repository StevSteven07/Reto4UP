
package Reto4.despliegue.services;

import Reto4.despliegue.entitys.Admin;
import Reto4.despliegue.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();
    }

    public Optional<Admin> getAdmin(int id){
        return adminRepository.getAdmin(id);
    }

    public Admin save (Admin admin){
        if(validarCampos(admin)) {
            if (admin.getId() == null) {
                return adminRepository.save(admin);
            } else {
                Optional<Admin> e = adminRepository.getAdmin(admin.getId());
                if (e.isPresent()) {
                    return admin;
                } else {
                    return adminRepository.save(admin);
                }
            }
        } return admin;
    }
    public Admin update(Admin admin){
        if(validarCampos(admin)) {
            if (admin.getId() != null) {
                Optional<Admin> a = adminRepository.getAdmin(admin.getId());
                if (a.isPresent()) {
                    if (admin.getName() != null) {
                        a.get().setName(admin.getName());
                    }
                    if (admin.getEmail() != null) {
                        a.get().setEmail(admin.getEmail());
                    }
                    if (admin.getPassword() != null) {
                        a.get().setPassword(admin.getPassword());
                    }
                    adminRepository.save(a.get());
                    return a.get();
                } else {
                    return admin;
                }
            } else {
                return admin;
            }
        } return admin;
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Admin> admin = adminRepository.getAdmin(id);
        if (admin.isPresent()){
            adminRepository.delete(admin.get());
            flag=true;
        }
        return flag;
    }

    public boolean validarCampos (Admin admin){
        return (admin.getEmail().length()<=45 && admin.getPassword().length()<=45 &&
                admin.getName().length()<=250);
    }
}
