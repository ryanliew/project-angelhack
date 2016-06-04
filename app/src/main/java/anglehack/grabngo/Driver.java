package anglehack.grabngo;

/**
 * Created by SLW2 on 6/4/2016.
 */
public class Driver extends User {
    private String carPlate, licenseNo;
    private int carType;

    public Driver(){}
    public Driver(String carPlate, String licenseNo, int carType) {
        this.carPlate = carPlate;
        this.licenseNo = licenseNo;
        this.carType = carType;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }
}
