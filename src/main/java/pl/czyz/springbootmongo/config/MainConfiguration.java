package pl.czyz.springbootmongo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ThreadPoolRejectedPolicy;
import org.apache.camel.spi.ThreadPoolProfile;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

@Configuration
public class MainConfiguration {

    @Bean
    CamelContextConfiguration camelContextConfiguration(@Value("${activemq.user}") String user,
                                                        @Value("${activemq.password}") String password,
                                                        @Value("${activemq.url}") String url) {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext camelContext) {
                ThreadPoolProfile threadPoolProfile = new ThreadPoolProfile();
                threadPoolProfile.setId("someId");
                threadPoolProfile.setPoolSize(6);
                threadPoolProfile.setMaxPoolSize(10);
                threadPoolProfile.setMaxQueueSize(250);
                threadPoolProfile.setKeepAliveTime(25L);
                threadPoolProfile.setRejectedPolicy(ThreadPoolRejectedPolicy.Abort);
                camelContext.getExecutorServiceManager().registerThreadPoolProfile(threadPoolProfile);
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
                camelContext.addComponent("activemq", jmsComponentAutoAcknowledge(connectionFactory));
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {

            }
        };
    }
}
