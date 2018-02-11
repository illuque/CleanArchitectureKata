package gateways.auth;

import core.usecases.contracts.gateways.auth.IAuthService;

public class AuthServiceMemory implements IAuthService {

    public boolean isAuthenticated() {
        // For the sake of this exercise it is ok to return always true
        return true;
    }

}
