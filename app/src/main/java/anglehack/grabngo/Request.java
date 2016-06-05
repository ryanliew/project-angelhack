package anglehack.grabngo;

import com.directions.route.Routing;

import java.util.Date;

/**
 * Created by SLW2 on 6/4/2016.
 */
public class Request {
    private int customerId, driverId, noOfItem, status, weight;
    private double minPrice, maxPrice, finalPrice, driverPrice;
    private String origin, acceptTime, requestTime;
    private String destination;
    private String paymentId;

    public Request(){}

    public Request(String paymentStatus, String paymentId, String destination, String requestTime, String acceptTime, String origin, double driverprice, double finalprice, double maxprice, double minprice, int weight, int status, int noOfItem, int driverId, int customerId) {
        this.paymentStatus = paymentStatus;
        this.paymentId = paymentId;
        this.destination = destination;
        this.requestTime = requestTime;
        this.acceptTime = acceptTime;
        this.origin = origin;
        this.driverPrice = driverprice;
        this.finalPrice = finalprice;
        this.maxPrice = maxprice;
        this.minPrice = minprice;
        this.weight = weight;
        this.status = status;
        this.noOfItem = noOfItem;
        this.driverId = driverId;
        this.customerId = customerId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    private String paymentStatus;

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(int noOfItem) {
        this.noOfItem = noOfItem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getDriverPrice() {
        return driverPrice;
    }

    public void setDriverPrice(double driverPrice) {
        this.driverPrice = driverPrice;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void calculatePrice() {
        String request = "https://maps.googleapis.com/maps/api/directions/json?origin="+ this.origin +"&destination="+ this.destination +"&key=" + Constants.googleMapAPIKey();
//        Routing routing = new Routing.Builder()
//                            .travelMode()
    }
}
