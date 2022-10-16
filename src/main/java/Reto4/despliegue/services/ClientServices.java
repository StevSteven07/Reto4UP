package Reto4.despliegue.services;


import Reto4.despliegue.entitys.Client;
import Reto4.despliegue.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServices {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public Client save (Client client){
        if(validarCampos(client)) {
            if (client.getIdClient() == null) {
                return clientRepository.save(client);
            } else {
                Optional<Client> e = clientRepository.getClient(client.getIdClient());
                if (e.isPresent()) {
                    return client;
                } else {
                    return clientRepository.save(client);
                }
            }
        } return client;
    }

    public Client update(Client client){
        if(validarCampos(client)) {
            if (client.getIdClient() != null) {
                Optional<Client> c = clientRepository.getClient(client.getIdClient());
                if (c.isPresent()) {
                    if (client.getName() != null) {
                        c.get().setName(client.getName());
                    }
                    if (client.getAge() != null) {
                        c.get().setAge(client.getAge());
                    }
                    if (client.getEmail() != null) {
                        c.get().setEmail(client.getEmail());
                    }
                    if (client.getPassword() != null) {
                        c.get().setPassword(client.getPassword());
                    }
                    clientRepository.save(c.get());
                    return c.get();
                } else {
                    return client;
                }
            } else {
                return client;
            }
        } return client;
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Client> client = clientRepository.getClient(id);
        if (client.isPresent()){
            clientRepository.delete(client.get());
            flag=true;
        }
        return flag;
    }

    public boolean validarCampos (Client client){
        return (client.getEmail().length()<=45 && client.getPassword().length()<=45 &&
                client.getAge()<=250 && (client.getAge()>=1 && client.getAge()<=150));
    }
}
