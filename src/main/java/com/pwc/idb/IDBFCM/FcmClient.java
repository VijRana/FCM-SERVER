package com.pwc.idb.IDBFCM;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.collection.LLRBNode.Color;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidConfig.Priority;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FcmClient {
	private static final String PRIVATE_KEY_FLE = "classpath:idp-push-firebase-adminsdk-lpez3-904767f2f6.json";

	public FcmClient(ResourceLoader resourceLoader) {
		System.out.println("Using resource loader:" + resourceLoader.getResource(PRIVATE_KEY_FLE));
		try (InputStream serviceAccount = resourceLoader.getResource(PRIVATE_KEY_FLE).getInputStream()) {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void sendFcmMessage(Map<String, String> data) throws InterruptedException, ExecutionException {

		AndroidConfig androidConfig = AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis())
				.setCollapseKey("idp").setPriority(Priority.HIGH)
				.setNotification(AndroidNotification.builder().setTag("idp").build()).build();

		ApnsConfig apnsConfig = ApnsConfig.builder().setAps(Aps.builder().setCategory("idp").setThreadId("idp").build())
				.build();

		Message message = Message.builder().putAllData(data).setTopic("idp").setApnsConfig(apnsConfig)
				.setAndroidConfig(androidConfig)
				.setNotification(new Notification("PWC_IDP", "A new IDP project Notification")).build();

		String response = FirebaseMessaging.getInstance().sendAsync(message).get();
		System.out.println("Sent message: " + response);
	}

	public void sendPersonalMessage(String clientToken, Map<String, String> data)
			throws InterruptedException, ExecutionException {
		System.out.println(clientToken);
		AndroidConfig androidConfig = AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis())
				.setCollapseKey("personal").setPriority(Priority.HIGH).setNotification(AndroidNotification.builder()
						.setSound("default").setColor(("#ff0000")).setTag("personal").build())
				.build();

		ApnsConfig apnsConfig = ApnsConfig.builder()
				.setAps(Aps.builder().setCategory("personal").setThreadId("personal").build()).build();

		Message message = Message.builder().putAllData(data).setToken(clientToken).setApnsConfig(apnsConfig)
				.setAndroidConfig(androidConfig)
				.setNotification(new Notification("New Task", "Request for task approval")).build();

		String response = FirebaseMessaging.getInstance().sendAsync(message).get();
		System.out.println("Sent message to Respective PMO: " + response);
	}

}
