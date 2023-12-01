package games.highping.server.utils;

public class MailConstants {

    public static final Integer DELIVERING = 0;
    public static final Integer SUCCESS = 1;
    public static final Integer FAILURE = 2;
    public static final Integer MAX_TRY_COUNT = 3;
    public static final Integer MSG_TIMEOUT = 1;
    public static final String MAIL_QUEUE_NAME = "highping.mail.queue";
    public static final String MAIL_EXCHANGE_NAME = "highping.mail.exchange";
    public static final String MAIL_ROUTING_KEY_NAME = "highping.mail.routing.key";

}
