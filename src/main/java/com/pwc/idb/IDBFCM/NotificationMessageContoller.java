package com.pwc.idb.IDBFCM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

@RestController
@CrossOrigin
@RequestMapping("/push")
public class NotificationMessageContoller {
	private UserRepository userRepository;
	private FcmClient fcmClient;
	public NotificationMessageContoller(UserRepository userRepo, FcmClient fcmClient) {
		this.userRepository= userRepo;
		this.fcmClient= fcmClient;
	}
   
	@PostMapping("/send")
	public User sendMessageToPMO(@RequestParam String email) {
		System.out.println(email);
		String registeredToken = "";
		User user=null;
		List<User> userList = new ArrayList<>();
		userList= this.userRepository.findAll();
		for(User usser:userList) {	
			if(usser.getEmail().equals(email)) {
				user= usser;
				registeredToken= usser.getToken();
			}
		}
		// send Notification data
		Map<String, String> data = new HashMap<>();
		 data.put("name", "Debo");
		 data.put("link", "idp-ionic.com");
		try {
			this.fcmClient.sendPersonalMessage(registeredToken, data);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		return user;
	}
}
