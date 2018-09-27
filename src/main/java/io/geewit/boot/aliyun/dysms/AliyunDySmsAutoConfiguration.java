package io.geewit.boot.aliyun.dysms;

import com.aliyuncs.IAcsClient;
import io.geewit.boot.aliyun.AliyunProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun com.aliyun.mns auto configuration
 *
 * @author geewit
 */
@Configuration
@ConditionalOnClass(IAcsClient.class)
@EnableConfigurationProperties({ AliyunProperties.class, AliyunDySmsProperties.class })
public class AliyunDySmsAutoConfiguration {
}
