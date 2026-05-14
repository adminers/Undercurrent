import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.HashMap;

@Service
public class WeaverSsoService {

    // 基础配置（建议放在配置文件中）
    private final String E10_HOST = "https://weapp.mulinquan.cn"; // 换成你实际的E10地址
    private final String CLIENT_ID = "你的client_id";
    private final String CLIENT_SECRET = "你的client_secret";
    private final String REDIRECT_URI = "http://小数嘀嗒域名/sso/callback";

    private RestTemplate restTemplate = new RestTemplate();

    public String getWeaverUser(String code) throws Exception {
        // --- 第一步：获取 Access Token ---
        String tokenUrl = E10_HOST + "/papi/sso/oauth2.0/accessToken" +
                "?grant_type=authorization_code" +
                "&client_id=" + CLIENT_ID +
                "&client_secret=" + CLIENT_SECRET +
                "&code=" + code +
                "&redirect_uri=" + REDIRECT_URI;

        // E10 文档要求 POST 请求，参数可以挂在 URL 上或放在 Body
        Map<String, Object> tokenResponse = restTemplate.postForObject(tokenUrl, null, Map.class);

        if (tokenResponse != null && "200".equals(String.valueOf(tokenResponse.get("status")))) {
            String accessToken = (String) tokenResponse.get("access_token");

            // --- 第二步：获取用户信息 (Profile) ---
            String profileUrl = E10_HOST + "/papi/sso/oauth2.0/profile?access_token=" + accessToken;
            Map<String, Object> userResponse = restTemplate.postForObject(profileUrl, null, Map.class);

            if (userResponse != null && "0".equals(String.valueOf(userResponse.get("code")))) {
                // 返回 E10 后台配置的映射字段值（如工号或手机号）
                return String.valueOf(userResponse.get("id"));
            }
        }
        
        throw new RuntimeException("SSO 认证链路异常: " + tokenResponse);
    }
}