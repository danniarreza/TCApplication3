package nl.utwente.TCApplication3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.utwente.TCApplication3.model.TransportOrder;
import nl.utwente.TCApplication3.publisher.MessageProducer;
import nl.utwente.TCApplication3.repository.TransportOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@Tag(name = "Transport Order", description = "This is the Transport Order REST API")
public class TransportOrderController {

    // we have added this part to trigger the messageProducer without overriding run
    // method:
    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    TransportOrderRepository transportOrderRepository;

    @GetMapping("/transportOrder")
    public List<TransportOrder> getTransportOrders() {
        return transportOrderRepository.findAll();
    }

    @GetMapping("/transportOrderPublish/")
    public void sendToQueue() {
        Map<String, Object> transportOrder = new HashMap<>();
        transportOrder.put("transportOrderId", "22");
        transportOrder.put("branchId", "17");

        String message = transportOrder.toString();
        messageProducer.sendMessage(message);
    }

    @GetMapping("/processTransportOrder/{transportOrderId}")
    public void processTransportOrder(@PathVariable int transportOrderId) {

        TransportOrder transportOrder = transportOrderRepository.findById(transportOrderId);

        transportOrder.setConfirmedDeliveryDate(transportOrder.getPickupDate());
        transportOrder.setTransportCompanyId(3);
        transportOrder.setTruckId(random(1, 100));
        transportOrder.setStatus("Transportation Accepted");

        String message = convertObjectIntoString(transportOrder);
        messageProducer.sendMessage(message);
    }

    private int random(int i, int j) {
        Random r = new Random();
        int result = r.nextInt(j - i) + i;
        return result;
    }

    private String convertObjectIntoString(TransportOrder transportOrder) {
        Map<String, Object> transportOrderMessage = new HashMap<>();
        transportOrderMessage.put("transportOrderId", transportOrder.getTransportOrderId());
        transportOrderMessage.put("transportCompanyId", transportOrder.getTransportCompanyId());
        transportOrderMessage.put("truckId", transportOrder.getTruckId());
        transportOrderMessage.put("confirmedDeliveryDate", transportOrder.getConfirmedDeliveryDate());
        transportOrderMessage.put("status", transportOrder.getStatus());

        String message = transportOrderMessage.toString();
        return message;
    }

}
