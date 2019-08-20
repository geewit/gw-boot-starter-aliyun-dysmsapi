package io.geewit.boot.aliyun.dysms.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import io.geewit.boot.aliyun.AliyunProperties;
import io.geewit.boot.aliyun.dysms.AliyunDySmsProperties;
import io.geewit.utils.uuid.UUIDUtils;
import io.geewit.web.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.Map;

@Configuration
public class SmsSender {
    private final static Logger logger = LoggerFactory.getLogger(SmsSender.class);

    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
//    private static final String domain = "dysmsapi.aliyuncs.com";

    private final AliyunProperties properties;

    private final AliyunDySmsProperties aliyunDySmsProperties;

    public SmsSender(AliyunProperties properties, AliyunDySmsProperties aliyunDySmsProperties) {
        this.properties = properties;
        this.aliyunDySmsProperties = aliyunDySmsProperties;
    }

    @SuppressWarnings({"unused"})
    public SendSmsResponse sendSms(String templateCode, Map<String, String> params, List<String> receivers) throws ClientException {
        String regionId = aliyunDySmsProperties.getRegionId();
        logger.debug("regionId : " + regionId);

        String endpoint = aliyunDySmsProperties.getEndpoint();
        logger.debug("endpoint : " + endpoint);
        //hint 此处可能会抛出异常，注意catch
        IClientProfile profile = DefaultProfile.getProfile(regionId, properties.getKey(), properties.getSecret());
        DefaultProfile.addEndpoint(regionId, product, endpoint);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        String phoneNumbers = StringUtils.join(receivers.toArray(), ",");
        logger.debug("phoneNumbers : " + phoneNumbers);
        request.setPhoneNumbers(phoneNumbers);




        //必填:短信签名-可在短信控制台中找到
        String sign = aliyunDySmsProperties.getSign();
        logger.debug("sign : " + sign);
        request.setSignName(sign);

        //必填:短信模板-可在短信控制台中找到
        logger.debug("templateCode : " + templateCode);
        request.setTemplateCode(templateCode);

        //可选:模板中的变量替换JSON串,如模板内容为 json
        String templateParam = JsonUtils.toJson(params);
        logger.debug("templateParam : " + templateParam);
        request.setTemplateParam(templateParam);

        String outId = UUIDUtils.randomUUID();
        logger.debug("outId : " + outId);
        request.setOutId(outId);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        logger.info("sendSmsResponse : " + JsonUtils.toJson(sendSmsResponse));

        return sendSmsResponse;
    }


//    public static void main(String[] args) {
//        String regionId = "cn-shanghai";
//        String endpoint = "cn-shanghai";
//        String sign = "上海激智信息技术有限公司";
//        String tempalteCode = "SMS_63410150";
//        IClientProfile profile = DefaultProfile.getProfile(regionId, "rviOaWhKvz80REXD", "u0yr8DUenFgKz9eNG94ZljMQMC7MLP");
//        try {
//            DefaultProfile.addEndpoint(endpoint, regionId, "Dysmsapi", "dysmsapi.aliyuncs.com");
//
//            IAcsClient acsClient = new DefaultAcsClient(profile);
//            ImmutableMap.Builder<String, String> paramsBuilder = ImmutableMap.builder();
//            paramsBuilder.put("customer", "test");
//            paramsBuilder.put("code", String.valueOf(RandomUtils.nextInt(100000, 999999)));
//            paramsBuilder.put("product", "保存委托卖单");
//            Map<String, String> params = paramsBuilder.build();
//            SendSmsRequest request = new SendSmsRequest();
//            request.setPhoneNumbers("13917848143");
//
//            //必填:短信签名-可在短信控制台中找到
//            request.setSignName(sign);
//
//            //必填:短信模板-可在短信控制台中找到
//            request.setTemplateCode(tempalteCode);
//
//            //可选:模板中的变量替换JSON串,如模板内容为 json
//            request.setTemplateParam(JsonUtils.toJson(params));
//
//            request.setOutId(UUID.randomUUID().toString());
//
//            //hint 此处可能会抛出异常，注意catch
//            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//            System.out.println("response: " + JsonUtils.toJson(sendSmsResponse));
//        } catch (ClientException e) {
//            System.out.println("ErrCode: " + e.getErrCode());
//            e.printStackTrace();
//        }
//    }
}
