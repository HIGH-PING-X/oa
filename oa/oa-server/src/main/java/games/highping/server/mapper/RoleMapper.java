package games.highping.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import games.highping.server.pojo.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author noob
 * @since 2023-11-27
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoles(Integer adminId);
}
