package games.highping.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import games.highping.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 菜单角色中间表 Mapper 接口
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
