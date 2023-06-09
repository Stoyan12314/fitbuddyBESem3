package org.example.buisness.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.example.buisness.exceptions.CloudinaryException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryManagerImpl {
    private Cloudinary cloudinary;

    public CloudinaryManagerImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                return (String) uploadResult.get("url");
            } catch (Exception e) {
                throw new CloudinaryException();
            }
        }
        return "empty";
    }
}
