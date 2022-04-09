package nl.utwente.TCApplication3.consumers;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class MessageConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                System.out.println("Message is received by MessageConsumer : " + msg);
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }

}
