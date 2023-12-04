package games.highping.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.server.config.security.JwtTokenUtil;
import games.highping.server.mapper.AdminMapper;
import games.highping.server.mapper.AdminRoleMapper;
import games.highping.server.mapper.RoleMapper;
import games.highping.server.pojo.Admin;
import games.highping.server.pojo.AdminRole;
import games.highping.server.pojo.RespBean;
import games.highping.server.pojo.Role;
import games.highping.server.service.IAdminService;
import games.highping.server.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ????Ա? 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    // 登录之后返回token
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        //TODO update
        /*String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码填写错误");
        }*/
        // 登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员");
        }

        // 更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    // 根据用户名获取用户
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", 1));
    }

    // 根据用户id获取角色列表
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(), keywords);
    }

    @Override
    @Transactional // 开启事务
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        // 先删除全部，后调用方法重新全部添加
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("admin_id", adminId));
        Integer result = adminRoleMapper.updateAdminRole(adminId, rids);
        if (rids.length == result) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        // 密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPass, admin.getPassword())) {
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if (result == 1) {
                return RespBean.success("更新成功！");
            }
        }
        return RespBean.error("更新失败！");
    }

}
