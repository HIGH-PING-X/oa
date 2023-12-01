package games.highping.server.config.websocket;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import games.highping.server.config.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    // 注册STOMP协议的节点(endpoint),并映射指定的URL
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /** 1.将ws/ep路径注册为STOMP的端点，用户连接了这个端点就可以进行websocket通信，支持socketJS
         * 2.setAllowedOrigins("*")表示可以跨域
         * 3.withSockJS()表示支持socketJS访问
         */
        registry.addEndpoint("/ws/ep")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    // 输入通道参数配置,正常情况不需要配置,项目使用了jwt,需要配置
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                // 判断是否为连接，如果是，需要获取token并且设置用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    // 获取token
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    if (!StringUtils.isEmpty(token)){
                        String authToken = token.substring(tokenHead.length());
                        String username = jwtTokenUtil.getUsernameFromToken(authToken);
                        // 校验token是否正确
                        if (!StringUtils.isEmpty(username)){
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            if (jwtTokenUtil.validateToken(authToken, userDetails)){
                                // 获取用户权限列表
                                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    // 配置消息代理(Message Broker)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置消息代理，消息代理将消息转发给指定的目标
        registry.enableSimpleBroker("/queue");
    }

}
