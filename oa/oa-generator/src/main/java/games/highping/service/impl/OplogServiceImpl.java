package games.highping.service.impl;

import games.highping.pojo.Oplog;
import games.highping.mapper.OplogMapper;
import games.highping.service.IOplogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
