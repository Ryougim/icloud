package com.jiaml.user.autoconfigure;

import com.jiaml.user.api.feign.UserFeignClient;
import com.jiaml.user.api.feign.UserFeignMarker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(UserFeignMarker.class)
@EnableFeignClients(basePackageClasses = UserFeignMarker.class)
@EnableConfigurationProperties(MsUserConfiguration.class)
@Import({UserFeignClient.UserFallbackFactory.class})
public class MsUserAutoConfiguration {

}




