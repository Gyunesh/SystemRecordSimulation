package nLayeredDemo.business.concretes;

import nLayeredDemo.business.abstracts.AuthService;
import nLayeredDemo.business.abstracts.UserService;
import nLayeredDemo.core.abstracts.RegisterService;
import nLayeredDemo.core.abstracts.VerificationService;
import nLayeredDemo.entities.concretes.User;

public class AuthManager implements AuthService {

	private UserService userService;
	private RegisterService registerService;
	private VerificationService verificationService;

	public AuthManager(UserService userService, RegisterService registerService) {
		this.userService = userService;
		this.registerService = registerService;
		
	}

	@Override
	public void register(User user) {
		if (this.userService.add(user)) {
			System.out.println(user.getFirstName()+" Register succesful Please verify your e-mail address.");
			this.verificationService.send();

		} else {
			System.out.println("Register failed.");
		}

	}

	@Override
	public void registerWithGoogle(String email, String password) {
		this.registerService.register(email, password);
		System.out.println("You registered with google account");
	}

	@Override
	public void login(String eMail, String password) {
		if (this.userService.getByEmail(eMail) != null) {
			if (this.userService.getByEmail(eMail).geteMail().equals(eMail)
					&& this.userService.getByEmail(eMail).getPassword().equals(password)) {
				System.out.println("Login successfuly");

			} else {
				System.out.println("Your e-mail address or password is incorrect.");
			}
		} else {
			System.out.println("This user is not registered.");
		}

	}

}
