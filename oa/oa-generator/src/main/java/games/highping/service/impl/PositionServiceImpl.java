package games.highping.service.impl;

import games.highping.pojo.Position;
import games.highping.mapper.PositionMapper;
import games.highping.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
