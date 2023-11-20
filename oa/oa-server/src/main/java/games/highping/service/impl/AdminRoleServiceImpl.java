package games.highping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.mapper.AdminRoleMapper;
import games.highping.pojo.AdminRole;
import games.highping.service.IAdminRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员角色中间表 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

}
