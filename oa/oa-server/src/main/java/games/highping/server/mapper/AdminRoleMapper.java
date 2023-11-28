package games.highping.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import games.highping.server.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 管理员角色中间表 Mapper 接口
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    Integer updateAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
