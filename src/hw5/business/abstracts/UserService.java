package hw5.business.abstracts;

import java.util.List;

import hw5.entities.concretes.User;

public interface UserService {
	void login(String email, String password);
	void register(User user);
	void sendEmail(User user);
	void verifyEmail(boolean verificationCode);

}
