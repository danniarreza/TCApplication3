package nl.utwente.TCApplication3.repository;

import nl.utwente.TCApplication3.model.TransportOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransportOrderRepository {

    List<TransportOrder> transportOrderList = new ArrayList<>();

    public TransportOrder save(TransportOrder transportOrder){

        transportOrderList.add(transportOrder);
        return transportOrder;
    }

    public TransportOrder findById(int id){

        for (TransportOrder transportOrder:transportOrderList) {
            if (transportOrder.getTransportOrderId() == id){
                return transportOrder;
            }
        }

        return null;
    }

    public List<TransportOrder> findAll(){
        return transportOrderList;
    }

}
