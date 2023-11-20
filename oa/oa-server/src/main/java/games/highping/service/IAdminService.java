package games.highping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import games.highping.pojo.Admin;
import games.highping.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * ????Ա? 服务类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password, HttpServletRequest request);
}
