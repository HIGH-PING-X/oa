package games.highping.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.server.mapper.PositionMapper;
import games.highping.server.pojo.Position;
import games.highping.server.service.IPositionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职位 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
