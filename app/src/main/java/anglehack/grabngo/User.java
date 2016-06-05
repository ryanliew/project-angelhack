package anglehack.grabngo;

/**
 * Created by SLW2 on 6/4/2016.
 */
public class User {
    private String username, password, name, ic, email, contactnumber, address, carPlate, licenseNo;
    private int status, carType;
    private boolean isDriver;
    private double credit;

    public User(){}

    public User(String username, String password, String name, String ic, String email, String contactnumber, String address, String carPlate, String licenseNo, int status, int carType, boolean isDriver, double credit) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.ic = ic;
        this.email = email;
        this.contactnumber = contactnumber;
        this.address = address;
        this.carPlate = carPlate;
        this.licenseNo = licenseNo;
        this.status = status;
        this.carType = carType;
        this.isDriver = isDriver;
        this.credit = credit;
    }

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }
}
