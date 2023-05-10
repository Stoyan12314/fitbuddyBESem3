package org.example.Api;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dfrtd4fke",
                "api_key", "573444829667343",
                "api_secret", "oZv_nHcSDjHoRvRK6A7X64HKrws"));
        return cloudinary;
    }
}