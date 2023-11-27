package games.highping.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.server.mapper.MenuMapper;
import games.highping.server.pojo.Admin;
import games.highping.server.pojo.Menu;
import games.highping.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByAdminId() {
        return menuMapper.getMenuByAdminId(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
