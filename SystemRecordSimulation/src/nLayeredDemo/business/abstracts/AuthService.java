package nLayeredDemo.business.abstracts;

import nLayeredDemo.entities.concretes.User;

public interface AuthService {
	
	void register(User user);
	void registerWithGoogle(String email,String password);
	void login(String eMail, String password);

}
