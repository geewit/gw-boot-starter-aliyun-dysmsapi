package io.geewit.boot.aliyun.dysms;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * aliyun dysms properties
 *
 * @author geewit
 */
@ConfigurationProperties(prefix = "aliyun.dysms")
public class AliyunDySmsProperties {
    /**
     * regionId
     */
    private String regionId;
    /**
     * endpoint
     */
    private String endpoint;

    private String sign;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
