@Service
public class UserService {
    /**
     * 本地登录逻辑：根据泛微返回的 userId（手机号/工号）查找或创建用户
     */
    public void localLogin(String weaverUserId, HttpServletRequest request) {
        // 示例：查询数据库
        User user = userRepository.findByWeaverId(weaverUserId);
        if (user == null) {
            // 自动创建用户（可根据业务决定是否需要额外信息）
            user = new User();
            user.setWeaverId(weaverUserId);
            user.setUsername(weaverUserId);
            userRepository.save(user);
        }
        // 存入 session 或生成 JWT
        request.getSession().setAttribute("currentUser", user);
    }
}