package ua.com.alevel.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.alevel.web.dto.requests.JwtUser;

import java.io.IOException;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private static final String URL_FOR_LOAD_USER = "http://localhost:8082/api/v1/inner/getAuthorization";

    @Override
    public UserDetails loadUserByUsername(String authToken) throws UsernameNotFoundException {

        JwtUser jwtUser = getJwtUserByAuthToken(authToken);
        if (!jwtUser.isEnabled()) {
            throw new DisabledException("User account is disabled");
        }
        return jwtUser;
    }

    private static JwtUser getJwtUserByAuthToken(String authToken) {
        try {
            String response = sendHttpGetRequest(authToken);
            System.out.println("response = " + response);

            JwtUser jwtUser = JwtUser.fromJson(response);
            System.out.println("jwtUser = " + jwtUser);
            return jwtUser;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String sendHttpGetRequest(String authorizationHeaderValue) throws IOException {
        System.out.println("authorizationHeaderValue = " + authorizationHeaderValue);
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URL_FOR_LOAD_USER);
        httpGet.setHeader("Authorization", authorizationHeaderValue);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        return EntityUtils.toString(httpResponse.getEntity());
    }
}