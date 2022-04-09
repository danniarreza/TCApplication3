package nl.utwente.TCApplication3.config;

import nl.utwente.TCApplication3.consumers.MessageConsumer;
import nl.utwente.TCApplication3.consumers.MessageSubscriber;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;


@Configuration
@PropertySource(value = "classpath:application.properties")

public class JmsConfig {

    @Autowired
    private Environment env;

    @Autowired
    @Lazy(true)
    private MessageSubscriber messageSubscriber;

    // For normal queues the following @Autowired is added:
    @Autowired
    private MessageConsumer messageConsumer;

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(env.getProperty("JMS.BROKER.URL"));
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory());
        return cachingConnectionFactory;
    }

    @Bean
    public Topic topic() {
        Topic topic = new ActiveMQTopic(env.getProperty("JMS.TOPIC.NAME"));
        return topic;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());

        jmsTemplate.setDefaultDestination(topic());
        jmsTemplate.setPubSubDomain(true);

        return jmsTemplate;
    }

    @Bean(name = "messageListenerContainerOne")
    public MessageListenerContainer messageListenerContainerOne() {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();

        messageListenerContainer.setPubSubDomain(true);
        messageListenerContainer.setDestination(topic());
        messageListenerContainer.setMessageListener(messageSubscriber);
        messageListenerContainer.setConnectionFactory(connectionFactory());

        return messageListenerContainer;
    }

    // For normal queues the following @Bean is added:
    @Bean
    public Queue queue() {
        Queue queue = new ActiveMQQueue(env.getProperty("JMS.QUEUE.NAME"));
        return queue;
    }

    @Bean
    public JmsTemplate jmsTemplate2() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());

        jmsTemplate.setDefaultDestination(queue());

        return jmsTemplate;
    }

    @Bean
//    @Lazy(true)
    public MessageListenerContainer messageListenerContainer() {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();

        messageListenerContainer.setDestination(queue());
        messageListenerContainer.setMessageListener(messageConsumer);
        messageListenerContainer.setConnectionFactory(connectionFactory());

        return messageListenerContainer;
    }
}
