package games.highping.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.server.mapper.MenuRoleMapper;
import games.highping.server.pojo.MenuRole;
import games.highping.server.service.IMenuRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单角色中间表 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

}
