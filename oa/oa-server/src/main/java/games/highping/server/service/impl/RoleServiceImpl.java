package games.highping.server.service.impl;

import games.highping.server.pojo.Role;
import games.highping.server.mapper.RoleMapper;
import games.highping.server.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
