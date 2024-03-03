package uz.pdp.online.dao;

import uz.pdp.online.entity.User;

public interface UserDao extends CrudDao<User, Long> {


    User getByUserName(String userName);

}
