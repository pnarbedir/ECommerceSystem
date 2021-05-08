package hw5.dataAccess.abstracts;

import java.util.List;

import hw5.entities.concretes.User;

public interface UserDao {
	
    void add(User user);
    void remove(User user);
    User get(int id);
    User getByEmail(String email);
    User getByEmailAndPassword(String email,String password);
    List<User> getAll();

}
