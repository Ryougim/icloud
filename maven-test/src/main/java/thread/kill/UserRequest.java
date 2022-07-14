package thread.kill;

public class UserRequest {
    private int userId;
    private int count;

    public UserRequest(int userId, int count) {
        this.userId = userId;
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userId=" + userId +
                ", count=" + count +
                '}';
    }
}
