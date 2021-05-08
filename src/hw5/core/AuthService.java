package hw5.core;

public interface AuthService {
    void register(String email, String password);
    void login(String email, String password);	
}
