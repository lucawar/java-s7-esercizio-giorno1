package lucaguerra.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lucaguerra.entities.User;
import lucaguerra.payload.NewUserPayload;
import lucaguerra.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UsersService userService;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody NewUserPayload body) {
		User createdUser = userService.save(body);
		return createdUser;
	}

	@GetMapping("")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return userService.find(page, size, sortBy);
	}

	@GetMapping("/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User findById(@PathVariable UUID userId) {
		return userService.findById(userId);
	}

	@PutMapping("/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User updateUser(@PathVariable UUID userId, @RequestBody NewUserPayload body) {
		return userService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) {
		userService.findByIdAndDelete(userId);
	}
}
