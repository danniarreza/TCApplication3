package nl.utwente.TCApplication3.publisher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate2;

    public void sendMessage(final String message) {
        jmsTemplate2.convertAndSend(message);
    }

}

