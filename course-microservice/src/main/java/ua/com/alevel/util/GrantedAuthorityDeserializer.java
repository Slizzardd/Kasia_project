package ua.com.alevel.util;

import com.google.gson.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.lang.reflect.Type;

public class GrantedAuthorityDeserializer implements JsonDeserializer<GrantedAuthority> {
    @Override
    public GrantedAuthority deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String authority = jsonObject.get("authority").getAsString();
        return new SimpleGrantedAuthority(authority);
    }
}