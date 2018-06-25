package com.tai.git.controllers;

import com.tai.git.dtos.CodeRequest;
import com.tai.git.dtos.LibraryDTO;
import com.tai.git.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String GITHUB_AUTH_URL = "https://github.com/login/oauth/access_token";

    @PostMapping("/auth")
    public HttpEntity<List<LibraryDTO>> authorize(@RequestBody CodeRequest codeRequest){
        System.out.println(codeRequest);

        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("client_id", codeRequest.getClient_id());
        map.add("client_secret", codeRequest.getClient_secret());
        map.add("client_code", codeRequest.getCode());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( GITHUB_AUTH_URL, request , String.class );
        System.out.println(response);

        return null;
    }

}
