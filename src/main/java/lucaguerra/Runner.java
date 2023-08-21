package lucaguerra;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import lucaguerra.entities.User;
import lucaguerra.payload.NewUserPayload;
import lucaguerra.repositories.UserRepository;
import lucaguerra.security.AuthController;
import lucaguerra.service.UsersService;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	UsersService userService;

	@Autowired
	UserRepository utenteRepo;

	@Autowired
	AuthController authController;

	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker(new Locale("it"));

		List<User> utentiDb = utenteRepo.findAll();
		if (utentiDb.isEmpty()) {

			for (int i = 0; i < 10; i++) {
				String username = faker.funnyName().name();
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String email = faker.internet().emailAddress();
				String password = "1234";
				NewUserPayload user = new NewUserPayload(username, name, surname, email, password);
				authController.saveUser(user);

			}
		}

	}

}