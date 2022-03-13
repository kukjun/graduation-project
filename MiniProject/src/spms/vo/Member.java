package spms.vo;

public class Member {
    private int manageId;
    private String id;
    private String password;
    private String email;
    private String name;
    private int locationCode;

    public int getManageId() {
        return manageId;
    }

    public Member setManageId(int manageId) {
        this.manageId = manageId;
        return this;
    }

    public String getId() {
        return id;
    }

    public Member setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Member setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public Member setLocationCode(int locationCode) {
        this.locationCode = locationCode;
        return this;
    }
}
