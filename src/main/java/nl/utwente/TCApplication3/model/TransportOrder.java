package nl.utwente.TCApplication3.model;

import java.util.Date;
import java.util.List;

public class TransportOrder {

    private int transportOrderId;
    private int branchId;
    private int transportCompanyId;
    private int truckId;
    private double orderWeight;
    private Date creationDate ;
    private Date pickupDate;
    private Date proposedDeliveryDate;
    private Date confirmedDeliveryDate;
    private String status;

    public TransportOrder(int transportOrderId, int branchId, int transportCompanyId, int truckId, double orderWeight, Date creationDate, Date pickupDate, Date proposedDeliveryDate, String status) {
        this.transportOrderId = transportOrderId;
        this.branchId = branchId;
        this.transportCompanyId = transportCompanyId;
        this.truckId = truckId;
        this.orderWeight = orderWeight;
        this.creationDate = creationDate;
        this.pickupDate = pickupDate;
        this.proposedDeliveryDate = proposedDeliveryDate;
        this.status = status;
    }

    public TransportOrder(){}


    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getTransportCompanyId() {
        return this.transportCompanyId;
    }


    public int getTruckId() {
        return this.truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public double getOrderWeight() {
        return this.orderWeight;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getProposedDeliveryDate() {
        return this.proposedDeliveryDate;
    }

    public void setProposedDeliveryDate(Date proposedDeliveryDate) {
        this.proposedDeliveryDate = proposedDeliveryDate;
    }

    public Date getConfirmedDeliveryDate() {
        return this.confirmedDeliveryDate;
    }

    public void setConfirmedDeliveryDate(Date confirmedDeliveryDate) {
        this.confirmedDeliveryDate = confirmedDeliveryDate;
    }

    public void setTransportOrderId(int transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    public int getTransportOrderId() {
        return this.transportOrderId;
    }

    public void setTransportCompanyId(int transportCompanyId) {
        this.transportCompanyId = transportCompanyId;
    }

    public void setOrderWeight(double orderWeight) {
        this.orderWeight = orderWeight;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public int getBranchId() {
        return branchId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String orderStatus) {
        this.status = orderStatus;
    }
}
