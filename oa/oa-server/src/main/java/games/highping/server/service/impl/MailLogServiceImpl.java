package games.highping.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.server.mapper.MailLogMapper;
import games.highping.server.pojo.MailLog;
import games.highping.server.service.IMailLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件日志 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
