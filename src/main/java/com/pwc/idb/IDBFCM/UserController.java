package com.pwc.idb.IDBFCM;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.Message;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	private UserRepository userRepository;

	private FcmClient fcmClient;

	public UserController(UserRepository userRepo, FcmClient fcm) {
		this.userRepository = userRepo;
		this.fcmClient = fcm;
	}

	@GetMapping("/all")
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<>();
		userList = this.userRepository.findAll();
		return userList;
	}

	@PutMapping("/add")
	public boolean insert(@RequestBody User user) {
		NotificationMessage messages = new NotificationMessage("IDP Welcome Message",
				"Welcome" + user.getName() + "to IDP");
		if (user == null) {
			return false;
		} else {
			this.userRepository.save(user);
//			User data = this.userRepository.findUserByEmailId(user.getEmail());
//			System.out.println("get user details"+ data.getName());
			return true;
		}

	}

}
