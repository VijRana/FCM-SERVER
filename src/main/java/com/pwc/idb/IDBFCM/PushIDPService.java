package com.pwc.idb.IDBFCM;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.unbescape.html.HtmlEscape;

@Service
public class PushIDPService {

  private final RestTemplate restTemplate;

  private final FcmClient fcmClient;

  private int id = 0;
  HttpEntity<String> entity;

  public PushIDPService(FcmClient fcmClient) {
    this.restTemplate = new RestTemplate();
    this.fcmClient = fcmClient;
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    this.entity = new HttpEntity<String>("parameters", headers);
  }

  @Scheduled(fixedDelay = 60_000)
  public void sendIDPUpdate() {
    sendPushMessage(HtmlEscape.unescapeHtml("Same IDP Update"));
  }

  void sendPushMessage(String msg) {
    Map<String, String> data = new HashMap<>();
    data.put("id", String.valueOf(++this.id));
    data.put("text", msg);

    // Send a message
    System.out.println("Sending IDP Message...");
    try {
      this.fcmClient.sendFcmMessage(data);
    }
    catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

}
