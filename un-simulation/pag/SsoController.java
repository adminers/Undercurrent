import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private WeaverSsoService ssoService;

    /**
     * 接收 E10 回调的地址
     * 对应 E10 后台配置的 redirect_uri: http://小数嘀嗒域名/sso/callback
     */
    @RequestMapping("/callback")
    public ModelAndView callback(@RequestParam(value = "code", required = false) String code, 
                                 HttpSession session) {
        
        // 1. 校验 code 是否存在
        if (code == null || code.isEmpty()) {
            return new ModelAndView("error").addObject("msg", "未获取到授权码code");
        }

        try {
            // 2. 调用 Service 向 E10 换取 accessToken 并获取用户信息
            String weaverUserId = ssoService.getWeaverUser(code);

            if (weaverUserId != null) {
                // 3. 【业务逻辑】在小数嘀嗒数据库中查找此用户并完成本地登录
                // User user = userService.findByWeaverId(weaverUserId);
                // session.setAttribute("currentUser", user);
                
                System.out.println("SSO 登录成功，用户标识为: " + weaverUserId);
                return new ModelAndView("redirect:/index.jsp"); // 登录成功跳转首页
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("error").addObject("msg", "SSO 认证失败");
    }
}