package games.highping.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import games.highping.server.pojo.Employee;
import games.highping.server.pojo.MailLog;
import games.highping.server.service.IEmployeeService;
import games.highping.server.service.IMailLogService;
import games.highping.server.utils.MailConstants;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask(){
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>().eq("status", 0).lt("try_time", LocalDateTime.now()));
        list.forEach(mailLog -> {
            if(3 <= mailLog.getCount()){
                mailLogService.update(new UpdateWrapper<MailLog>()
                        .set("status", 2)
                        .eq("msg_id", mailLog.getMsgId()));
            }
            mailLogService.update(new UpdateWrapper<MailLog>()
                    .set("count", mailLog.getCount() + 1)
                    .set("update_time", LocalDateTime.now())
                    .set("try_time", LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT))
                    .eq("msg_id", mailLog.getMsgId()));
            Employee emp = employeeService.exportEmployee(mailLog.getEid()).get(0);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(mailLog.getMsgId()));
        });
    }

}
