package it.tickets.manager.Service;

import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements  IUserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> showUser(){
        List<UserModel> users = userRepository.findAll();
        return users;
    }


    @Override
    public UserModel findTicketOfUser(Integer id) {
        UserModel UserEnable = userRepository.findById(id).get();
        return  UserEnable;
    }

    public UserModel findUser(Integer id){
        try {
            UserModel user = userRepository.getReferenceById(id);
            return user;
        }catch (Exception e){
             throw new IllegalAccessError("User not found");
        }

    }
}
