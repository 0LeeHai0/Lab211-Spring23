package obj;

public class Publishers {
    protected String publisherID;
    private  String publisherName;
    private String phoneNum;

    public Publishers() {
    }

    public Publishers(String publisherID, String publisherName, String phoneNum) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.phoneNum = phoneNum;
    }
    
    

    public String getpublisherID() {
        return publisherID;
    }

    public void setpublisherID(String publisherID) {
            this.publisherID = publisherID; 
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void printPublisher(){
        System.out.println();
        System.out.println("ID: "+this.publisherID);
        System.out.println("Name: "+this.publisherName);
        System.out.println("Phone Number: "+this.phoneNum);
    }
    
    @Override
    public String toString() {
        return String.format("| %-8s| %-16s| %-16s |\n",publisherID,publisherName,phoneNum);
    }
}
