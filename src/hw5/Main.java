package hw5;

import hw5.GoogleAuthManager.GoogleAuthManager;
import hw5.business.concretes.UserManager;
import hw5.core.GoogleAuthManagerAdapter;
import hw5.dataAccess.concretes.InMemoryUserDao;
import hw5.entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManager userManager = new UserManager(new InMemoryUserDao());
		User user = new User(1, "Pınar", "Bedir", "pnr@gmail.com", "123456");
		User user2 = new User(2, "Unzile", "Kocak", "pnr@gmail.com", "456789");


		userManager.register(user);
		userManager.verifyEmail(user.isVerified());
		userManager.login("pnr@gmail.com", "123456");

		System.out.println("--------------------");

		
		userManager.register(user2);
		userManager.verifyEmail(user2.isVerified());
		//userManager.login("unz@gmail.com", "456789");

		

		//login with google 
		GoogleAuthManagerAdapter google = new GoogleAuthManagerAdapter();
		google.login("pnr@gmail.com","123456");
	}
	

}
