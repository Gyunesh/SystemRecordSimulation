package nLayeredDemo.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import nLayeredDemo.business.abstracts.UserService;
import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class UserManager implements UserService {

	public static final String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"; // "^[A-Z0-9.%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
	public Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

	private UserDao userDao;

	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean add(User user) {
		if ((checkUser(user) && checkEmail(user.geteMail()) && checkUserRegistered(user.geteMail()))) {
			userDao.add(user);
			return true;
		}
		return false;
	}

	@Override
	public void update(User user) {
		userDao.update(user);

	}

	@Override
	public void delete(User user) {
		userDao.delete(user);

	}

	@Override
	public User getById(int id) {
		return userDao.getById(id);
	}

	@Override
	public List<User> getall() {
		return userDao.getAll();
	}

	@Override
	public User getByEmail(String eMail) {
		return userDao.getByEmail(eMail);
	}

	private boolean checkUser(User user) {
		if (checkFirstName(user.getFirstName()) || checkLastName(user.getLastName())
				|| checkPassword(user.getPassword()) || checkUserRegistered(user.geteMail())) {
			return false;
		}
		return true;
	}

	private boolean checkFirstName(String firstName) {
		if (firstName.length() < 2) {
			System.out.println("User's firstName must have at least 2 characters.");
			return false;
		}
		return true;
	}

	private boolean checkLastName(String lastName) {
		if (lastName.length() < 2) {
			System.out.println("User's lastName must have at least 2 characters.");
			return false;
		}
		return true;
	}

	private boolean checkPassword(String password) {
		if (password.length() < 6) {
			System.out.println("Password must have 6 characters.");
			return false;
		}
		return true;
	}

	private boolean checkEmail(String email) {
		if (pattern.matcher(email).find()) {
			return true;
		}
		System.out.println("Invalid e-mail adress.");
		return false;
	}

	private List<String> getEmails() {
		List<User> users = userDao.getAll();
		List<String> emails = new ArrayList<String>();
		for (User user : users) {
			emails.add(user.geteMail());
		}
		return emails;
	}

	private boolean checkUserRegistered(String mail) {
		List<String> eMails = this.getEmails();
		for (String eMail : eMails) {
			if (eMail.equals(mail)) {
				System.out.println("This email is registered");
				return false;
			}
		}
		return true;
	}

}
