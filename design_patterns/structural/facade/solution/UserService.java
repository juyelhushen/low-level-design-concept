package design_patterns.structural.facade.solution;

public class UserService {

    public String getUserDetails(String userId) {
        return String.format("User with ID: %s", userId);
    }
}
