package gateways.auth;

import unit.core.contracts.gateways.auth.IAuthService;

public class AuthServiceMemory implements IAuthService {

    public boolean isAuthenticated(String studentId) {
        // For the sake of this exercise it is ok to return always true
        return true;
    }

}
