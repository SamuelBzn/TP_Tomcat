package tp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service("usersService")
public class UsersService {
	protected ArrayList<User> users;
	
	public UsersService() {
		this.users = new ArrayList<User>();
		
		this.users.add(new User(1, "Mouloud Dupont", 50.85));
		this.users.add(new User(2, "Mahmoud Duquesnes", 300.45));
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public User findUser(int id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		
		return null;
	}
}
