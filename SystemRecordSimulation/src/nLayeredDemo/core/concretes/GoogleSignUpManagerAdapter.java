package nLayeredDemo.core.concretes;

import nLayeredDemo.core.abstracts.RegisterService;

import nLayeredDemo.googleSignUp.GoogleSignUpManager;


public class GoogleSignUpManagerAdapter implements RegisterService {

	

	@Override
	public void register(String email, String password) {
		GoogleSignUpManager googleSignUpManager = new GoogleSignUpManager();
		
		googleSignUpManager.googleRegister(email, password);
		

	}

}
