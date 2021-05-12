package nLayeredDemo;




import nLayeredDemo.business.concretes.AuthManager;
import nLayeredDemo.business.concretes.UserManager;

import nLayeredDemo.core.concretes.GoogleSignUpManagerAdapter;
import nLayeredDemo.dataAccess.concretes.InMemoryUserDao;
import nLayeredDemo.entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		AuthManager authManager = new AuthManager(new UserManager(new InMemoryUserDao()),new GoogleSignUpManagerAdapter());
		UserManager userManager = new UserManager(new InMemoryUserDao());
		GoogleSignUpManagerAdapter googleSignUpManagerAdapter = new GoogleSignUpManagerAdapter();
		
		
		User user = new User(1,"Ali","Veli","aliveli@hotmail.com","123456");
		
		System.out.println(userManager.getByEmail(user.geteMail()));
		
		authManager.register(user);
		authManager.login(user.geteMail(), user.getPassword());
		
		googleSignUpManagerAdapter.register(user.geteMail(), user.getPassword());
		
	}

}