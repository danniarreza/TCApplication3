package nl.utwente.TCApplication3.model;

public class Client {
    private String companyName;
    private int branchId;
    private String branchAddress;

    public Client(String companyName, int branchId, String branchAddress) {
        this.companyName = companyName;
        this.branchId = branchId;
        this.branchAddress = branchAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getBranchAddress() {
        return branchAddress;
    }




}
