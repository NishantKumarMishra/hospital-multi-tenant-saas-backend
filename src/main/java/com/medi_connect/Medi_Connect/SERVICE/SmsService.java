package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.REPOSITORIES.SmsInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService implements SmsInterface {

    @Value("${sms.fast2sms.api-key}")
    private String apiKey;

    @Value("${sms.enabled}")
    private boolean smsEnabled;

    private static final String url = "https://www.fast2sms.com/dev/bulkV2";



    @Override
    public void sendSms(String phone, String message) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.set("authorization",apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String,Object> body = new HashMap<>();

        body.put("route","q");
        body.put("numbers",phone);
        body.put("message",message);

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body,headers);

        restTemplate.postForObject(url,request,String.class);


    }



}
