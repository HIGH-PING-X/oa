package games.highping.mail;

import com.rabbitmq.client.Channel;
import games.highping.server.pojo.Employee;
import games.highping.server.utils.MailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

@Component
public class MailReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message msg, Channel channel){
        Employee emp = (Employee) msg.getPayload();
        MessageHeaders headers = msg.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");
        HashOperations hashOperations = redisTemplate.opsForHash();
        try {
            if (hashOperations.entries("mail_log").containsKey(msgId)){
                logger.error("消息已经被消费");
                channel.basicAck(tag,false);
                return;
            }
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(emp.getEmail());
            helper.setSubject("入职欢迎");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name",emp.getName());
            context.setVariable("posName",emp.getPosition().getName());
            context.setVariable("jobLevelName",emp.getJoblevel().getName());
            context.setVariable("departmentName",emp.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            javaMailSender.send(message);;
            logger.info("邮件发送成功");
            hashOperations.put("mail_log",msgId,"success");
            channel.basicAck(tag,false);
        } catch (Exception e) {
            try {
                channel.basicNack(tag,false,true);
            } catch (IOException ex) {
                logger.error("{}邮件发送失败",e.getMessage());
            }
            logger.error("{}邮件发送失败",e.getMessage());
        }
    }

}
