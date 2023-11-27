package games.highping.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import games.highping.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuByAdminId(Integer id);

    List<Menu> getMenusWithRole();
}
