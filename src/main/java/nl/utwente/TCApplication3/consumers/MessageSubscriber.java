package nl.utwente.TCApplication3.consumers;

import nl.utwente.TCApplication3.controller.TransportOrderController;
import nl.utwente.TCApplication3.model.TransportOrder;
import nl.utwente.TCApplication3.repository.TransportOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageSubscriber implements MessageListener {

    @Autowired
    TransportOrderController transportOrderController;

    @Autowired
    TransportOrderRepository transportOrderRepository;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                System.out.println("Message is received by MessageSubscriber : " + msg);
                TransportOrder transportOrder = convertToTransportOrderObject(msg);
                transportOrderRepository.save(transportOrder);
                // transportOrderController.processTransportOrder(transportOrder);

            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }

    public TransportOrder convertToTransportOrderObject(String msg) {
        msg = msg.substring(1, msg.length() - 1);
        String[] keyValuePairs = msg.split(",");
        Map<String, Object> map = new HashMap<>();

        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            map.put(entry[0].trim(), entry[1].trim());
        }

        TransportOrder transportOrder = new TransportOrder();

        transportOrder.setTransportOrderId(Integer.parseInt(map.get("transportOrderId").toString()));
        transportOrder.setBranchId(Integer.parseInt(map.get("branchId").toString()));
        transportOrder.setOrderWeight(Double.parseDouble(map.get("orderWeight").toString()));
        transportOrder.setCreationDate(stringToDate(map.get("creationDate").toString()));
        transportOrder.setProposedDeliveryDate(stringToDate(map.get("proposedDeliveryDate").toString()));
        transportOrder.setPickupDate(stringToDate(map.get("pickupDate").toString()));

        return transportOrder;
    }

    public Date stringToDate(String stringDate) {

        Date parsedDate = new Date();

        try {
            parsedDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy").parse(stringDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return parsedDate;

    }

}
