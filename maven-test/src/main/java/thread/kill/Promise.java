package thread.kill;

public class Promise {
    private UserRequest userRequest;
    private Result result;

    public Promise(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Promise{" +
                "userRequest=" + userRequest +
                ", result=" + result +
                '}';
    }
}
