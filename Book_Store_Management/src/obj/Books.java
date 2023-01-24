package obj;

public class Books extends Publishers{
    private String bookID;
    private String bookName;
    private double bookPrice;
    private int quantity;
    private String status;
    private double subTotal;

    public Books(){
        super();
    } 

    public Books(String bookID, String bookName,double bookPrice,int quantity,double subTotal, String pubID, String status) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.subTotal = subTotal;
        super.publisherID = pubID;
        this.status = status;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
         return String.format("| %-14s | %-16s | %-16.2f | %-14d | %-16.2f | %-16s | %-14s |\n",bookID,bookName,bookPrice,quantity,quantity*bookPrice,publisherID,status);
    }   
    
}
