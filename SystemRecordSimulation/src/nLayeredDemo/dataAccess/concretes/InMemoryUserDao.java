package nLayeredDemo.dataAccess.concretes;

import java.util.ArrayList;

import java.util.List;

import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class InMemoryUserDao implements UserDao {

	List<User> users = new ArrayList<User>();

	@Override
	public void add(User user) {
		users.add(user);
		System.out.println(user.getFirstName() + " added.");

	}

	@Override
	public void update(User user) {
		User newInfo = users.stream().filter(u -> u.getId() == user.getId()).findFirst().orElse(null);
		newInfo.setFirstName(user.getFirstName());
		newInfo.setLastName(user.getLastName());
		newInfo.seteMail(user.geteMail());
		newInfo.setPassword(user.getPassword());

	}

	@Override
	public void delete(User user) {
		users.remove(user);

	}

	@Override
	public User getById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public User getByEmail(String email) {
		for (User user : users) {
			if (user.geteMail() == email)
				return user;
		}
		return null;
	}

}
