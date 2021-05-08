package hw5.business.concretes;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import hw5.business.abstracts.UserService;
import hw5.dataAccess.abstracts.UserDao;
import hw5.entities.concretes.User;

public class UserManager implements UserService{

	private UserDao userDao;

	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void login(String email, String password) {
		if(emailPasswordCheck(email, password)) {
			System.out.println("login is successful");
		}
		else {
			System.out.println("Login is failed");
		}
	}

	@Override
	public void register(User user) {
		if (checkEmailExist(user.getEmail()) && checkEmailFormat(user.getEmail()) && nameCheck(user.getFirstName()) 
				&& controlPassword(user.getPassword())) {
			userDao.add(user);
			System.out.println("Register is succesful");
			sendEmail(user);
		}
	}

	@Override
	public void sendEmail(User user) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("press 1 for verify : ");
		int verificationcode = scanner.nextInt();
		
		if(verificationcode == 1) {
			user.setVerified(true);
		}else {
			user.setVerified(false);
		}

	}

	@Override
	public void verifyEmail(boolean verificationCode) {
		if (verificationCode) {
			System.out.println("your account is verified");
		} else {
			System.out.println("verify is failed");
		}
	}

	// BusinessCodes
	private boolean controlPassword(String password) {
		if (password.length() < 6) {
			System.out.println("Your password must be at least 6 characters");
			return false;
		} else {
			return true;
		}
	}

	private boolean checkEmailFormat(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			return true;
		} else {
			System.out.println("email is invalid");
			return false;
		}
	}

	private boolean checkEmailExist(String email) {
		
			if (userDao.getByEmail(email) != null) {
				System.out.println("Receive new available email");
				return false;
			}
		return true;
	}

	private boolean nameCheck(String firstName) {
		if (firstName.length() < 2) {
			System.out.println("Your name must be at least 2 characters");
			return false;
		} else {
			return true;
		}
	}
	
	private boolean emailPasswordCheck(String email,String password) {
		for(int i = 0; i< userDao.getAll().size(); i++) {
			if(userDao.getAll().get(i).getEmail() == email && userDao.getAll().get(i).getPassword() == password) {
				if(userDao.getAll().get(i).isVerified())
				{
					return true;
				}
			}
				else {
					System.out.println("Wrong email/password");
					return false;
				}
		}
	//	System.out.println("You have not entered a valid username or password");
		return false;
	}
	// End of the BusinessCodes

}
