public class Waiter {
    int waiter_id;
    String waiter;

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter =  waiter;
    }

    public void setId(int id) {
        this.waiter_id = id;
    }

    public int getId() {
        return waiter_id;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "waiter_id=" + waiter_id +
                ", waiter='" + waiter + '\'' +
                '}';
    }
}
