//package org.example.controller;
//
//import lombok.AllArgsConstructor;
//import org.example.domain.RefreshToken;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api")
//public class RefreshController {
//
//    @GetMapping("/refresh")
//    public ResponseEntity<RefreshToken> refresh(@RequestHeader("Authorization") String authorization) {
//        String accessToken = authorization.substring(7); // Remove "Bearer " from the header value
//        RefreshToken refreshTokenResponse = refreshTokenManager.refresh(accessToken);
//        return ResponseEntity.ok(refreshTokenResponse);
//    }
//
//}
