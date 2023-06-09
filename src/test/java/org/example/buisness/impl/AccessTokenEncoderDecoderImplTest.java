package org.example.buisness.impl;

import org.example.buisness.exceptions.InvalidAccessTokenException;
import org.example.domain.AccessToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class AccessTokenEncoderDecoderImplTest {

    @Test
    @DisplayName("Should encode AccessToken correctly")
    void encodeTest() {
        String secretKey = "E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5";
        AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl(secretKey);

        AccessToken accessToken = new AccessToken();
        accessToken.setSubject("subject");
        accessToken.setRoles(Arrays.asList("role1", "role2"));
        accessToken.setUserId(1L);

        String encoded = accessTokenEncoderDecoder.encode(accessToken);

        assertNotNull(encoded);
        assertFalse(encoded.isEmpty());
    }

    @Test
    @DisplayName("Should decode AccessToken correctly")
    void decodeTest() {
        String secretKey = "E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5";
        AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl(secretKey);

        AccessToken accessToken = new AccessToken();
        accessToken.setSubject("subject");
        accessToken.setRoles(Arrays.asList("role1", "role2"));
        accessToken.setUserId(1L);

        String encoded = accessTokenEncoderDecoder.encode(accessToken);
        AccessToken decoded = accessTokenEncoderDecoder.decode(encoded);

        assertEquals(accessToken.getSubject(), decoded.getSubject());
        assertEquals(accessToken.getUserId(), decoded.getUserId());
        assertEquals(accessToken.getRoles().size(), decoded.getRoles().size());
    }

    @Test
    @DisplayName("Should throw InvalidAccessTokenException when token is invalid")
    void decodeInvalidTokenTest() {
        String secretKey = "E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5";
        AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl(secretKey);

        String invalidToken = "invalid.token";

        assertThrows(InvalidAccessTokenException.class, () -> accessTokenEncoderDecoder.decode(invalidToken));
    }



}