package games.highping.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import games.highping.server.pojo.Admin;
import games.highping.server.pojo.AdminRole;
import games.highping.server.pojo.RespBean;
import games.highping.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @since 2023-11-20
 */
public interface IAdminService extends IService<Admin> {

    // 登录之后返回token
    RespBean login(String username, String password, String code, HttpServletRequest request);

    // 根据用户名获取用户
    Admin getAdminByUserName(String username);

    // 根据用户id查询角色列表
    List<Role> getRoles(Integer adminId);

    // 获取所有操作员
    List<Admin> getAllAdmins(String keywords);

    // 更新操作员信息
    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}
