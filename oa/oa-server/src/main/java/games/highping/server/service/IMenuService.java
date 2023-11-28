package games.highping.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import games.highping.server.pojo.Menu;
import games.highping.server.pojo.Role;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface IMenuService extends IService<Menu> {

    // 根据用户id查询菜单列表
    List<Menu> getMenuByAdminId();

    // 根据角色获取菜单列表
    List<Menu> getMenusWithRole();

    // 查询所有菜单
    List<Role> getAllMenus();
}
