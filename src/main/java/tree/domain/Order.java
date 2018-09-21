package tree.domain;

public class Order {
    private int id;
    private String goods;
    private int price;
    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "order: "+goods+" $"+price+" customer: "+customer;
    }
}
