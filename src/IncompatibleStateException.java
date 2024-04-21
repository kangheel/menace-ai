public class IncompatibleStateException extends Exception { 
    public IncompatibleStateException(int cur_state, int req_state) {
        super("Current state is " + cur_state + ", when state required for this function is " + req_state+".");
    }
}