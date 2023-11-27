package games.highping.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import games.highping.server.pojo.Menu;

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

    List<Menu> getMenuByAdminId();

}
