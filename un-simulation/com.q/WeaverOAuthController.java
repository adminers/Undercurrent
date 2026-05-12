import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class WeaverOAuthController {

    private static final Logger logger = LoggerFactory.getLogger(WeaverOAuthController.class);

    @Autowired
    private UserService userService;  // 你的用户服务，用于本地登录/创建用户

    /**
     * 步骤1：前端点击“泛微登录”按钮，调用此接口获取授权URL并重定向
     * 注意：此方法为 GET，因为需要重定向到泛微页面
     */
    @GetMapping("/weaver/login")
    public void weaverLogin(HttpServletResponse response) throws IOException {
        String authorizeUri = WeaverOAuth2Config.AUTHORIZE_URL
                + "?response_type=code"
                + "&client_id=" + WeaverOAuth2Config.CLIENT_ID
                + "&redirect_uri=" + WeaverOAuth2Config.REDIRECT_URI;
        response.sendRedirect(authorizeUri);
    }

    /**
     * 步骤2：泛微回调地址，携带 code 参数（GET 请求）
     */
    @GetMapping("/weaver/callback")
    public String weaverCallback(@RequestParam("code") String code,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // 调用核心登录逻辑
        return doLogin(code, request, response);
    }

    /**
     * 步骤2（备选）：如果你的前端通过 POST 方式将 code 放在 data 参数中传来
     * POST /fanweiLogin  data={"code":"ST-xxx"}
     */
    @ResponseBody
    @RequestMapping(value = "/fanweiLogin", method = RequestMethod.POST)
    public AjaxJson fanweiLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxJson ajaxJson = new AjaxJson();
        String data = request.getParameter("data");
        if (data == null || data.isEmpty()) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("参数 data 不能为空");
            return ajaxJson;
        }
        JSONObject json = JSONObject.parseObject(data);
        String code = json.getString("code");
        if (code == null || code.isEmpty()) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("code 不能为空");
            return ajaxJson;
        }
        // 执行登录，但注意此处无法重定向，因此直接返回登录成功信息或用户信息
        try {
            // 调用核心方法获取用户ID
            String userId = exchangeCodeForUser(code);
            // 本地登录逻辑（例如生成 token 并存入 session）
            userService.localLogin(userId, request);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("登录成功");
            ajaxJson.setData(userId);
        } catch (Exception e) {
            logger.error("泛微登录失败", e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());
        }
        return ajaxJson;
    }

    /**
     * 核心方法：用 code 换取用户标识 id
     * @return 用户在泛微中的唯一标识（手机号/工号等）
     */
    private String exchangeCodeForUser(String code) throws Exception {
        // 1. 用 code 换取 access_token
        JSONObject tokenParams = new JSONObject();
        tokenParams.put("grant_type", "authorization_code");
        tokenParams.put("client_id", WeaverOAuth2Config.CLIENT_ID);
        tokenParams.put("client_secret", WeaverOAuth2Config.CLIENT_SECRET);
        tokenParams.put("code", code);
        tokenParams.put("redirect_uri", WeaverOAuth2Config.REDIRECT_URI);

        JSONObject tokenResp = HttpUtils.doPostWithParams(WeaverOAuth2Config.ACCESS_TOKEN_URL, tokenParams);
        if (tokenResp.getIntValue("status") != 200 || !"SUCCESS".equals(tokenResp.getString("msg"))) {
            throw new RuntimeException("获取 access_token 失败：" + tokenResp.getString("msg"));
        }
        String accessToken = tokenResp.getString("access_token");

        // 2. 用 access_token 获取用户信息
        JSONObject profileParams = new JSONObject();
        profileParams.put("access_token", accessToken);
        JSONObject profileResp = HttpUtils.doPostWithParams(WeaverOAuth2Config.PROFILE_URL, profileParams);
        if (profileResp.getIntValue("status") != 200 || !"SUCCESS".equals(profileResp.getString("msg"))) {
            throw new RuntimeException("获取用户信息失败：" + profileResp.getString("msg"));
        }
        String userId = profileResp.getString("id");  // 手机号/工号等
        if (userId == null || userId.isEmpty()) {
            throw new RuntimeException("返回的用户 id 为空");
        }
        return userId;
    }

    /**
     * 适用于回调 GET 请求（需要重定向到前端页面）
     */
    private String doLogin(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String userId = exchangeCodeForUser(code);
            // 本地登录：根据 userId 创建或获取本地用户，存入 session
            userService.localLogin(userId, request);
            // 重定向到你的前端主页
            return "redirect:/index.html";
        } catch (Exception e) {
            logger.error("登录失败", e);
            // 可以重定向到错误页面
            return "redirect:/login?error=" + e.getMessage();
        }
    }

    /**
     * 退出登录（清除本地会话并调用泛微注销接口）
     */
    @GetMapping("/weaver/logout")
    public void weaverLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 清除本地 session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // 2. 重定向到泛微退出接口，service 参数需要 URL 编码且域名已加入白名单
        String service = "https://你的域名/logoutCallback";  // 退出完成后你想跳转的地址
        String logoutRedirect = WeaverOAuth2Config.LOGOUT_URL + "?service=" + java.net.URLEncoder.encode(service, "UTF-8");
        response.sendRedirect(logoutRedirect);
    }
}