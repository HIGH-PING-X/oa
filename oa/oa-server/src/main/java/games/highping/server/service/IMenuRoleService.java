package games.highping.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import games.highping.server.pojo.MenuRole;
import games.highping.server.pojo.RespBean;

/**
 * <p>
 * 菜单角色中间表 服务类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface IMenuRoleService extends IService<MenuRole> {

    // 更新角色菜单
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
