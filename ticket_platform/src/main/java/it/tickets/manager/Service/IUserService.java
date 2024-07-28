package it.tickets.manager.Service;

import it.tickets.manager.Model.UserModel;

import java.util.List;

public interface IUserService {
    UserModel findTicketOfUser(Integer id);

    UserModel findUser(Integer id);
    List<UserModel> showUser();

}
