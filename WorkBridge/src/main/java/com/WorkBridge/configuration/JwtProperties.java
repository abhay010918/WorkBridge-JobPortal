package com.WorkBridge.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties
@Component
public class JwtProperties {

    @Value("${jwt.algorithm.key}")
    private String secretkey;

    @Value("${jwt.expiry.duration}")
    private Long expirationTime;

    @Value("${jwt.issuer}")
    private String issuer;

}
