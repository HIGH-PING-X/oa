package games.highping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.mapper.MailLogMapper;
import games.highping.pojo.MailLog;
import games.highping.service.IMailLogService;
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
