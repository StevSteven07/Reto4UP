package Reto4.despliegue.services;


import Reto4.despliegue.entitys.Message;
import Reto4.despliegue.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServices {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }

    public Message save (Message message){
        if(validarCampos(message)) {
            if (message.getIdMessage() == null) {
                return messageRepository.save(message);
            } else {
                Optional<Message> m = messageRepository.getMessage(message.getIdMessage());
                if (m.isPresent()) {
                    return message;
                } else {
                    return messageRepository.save(message);
                }
            }
        } return message;
    }

    public Message update(Message message){
        if(validarCampos(message)) {
            if (message.getIdMessage() != null) {
                Optional<Message> m = messageRepository.getMessage(message.getIdMessage());
                if (m.isPresent()) {
                    if (message.getMessageText() != null) {
                        m.get().setMessageText(message.getMessageText());
                    }
                    messageRepository.save(m.get());
                    return m.get();
                } else {
                    return message;
                }
            } else {
                return message;
            }
        } return message;
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Message> message = messageRepository.getMessage(id);
        if (message.isPresent()){
            messageRepository.delete(message.get());
            flag=true;
        }
        return flag;
    }

    public boolean validarCampos (Message message){
        return (message.getMessageText().length()<=250);
    }
}
