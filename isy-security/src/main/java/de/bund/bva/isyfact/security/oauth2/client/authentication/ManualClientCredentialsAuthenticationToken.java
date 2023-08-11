package de.bund.bva.isyfact.security.oauth2.client.authentication;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * AuthenticationToken holding parameters required for creating a Client to use with Client Credentials Flow authentication.
 */
public class ManualClientCredentialsAuthenticationToken extends UsernamePasswordAuthenticationToken {

    /** Issuer location to use for manual client credentials grant authentication. */
    private final String issuerLocation;

    /** The BHKNZ to send as part of the authentication request (optional). */
    @Nullable
    private final String bhknz;

    public ManualClientCredentialsAuthenticationToken(String issuerLocation, String clientId, String clientSecret, @Nullable String bhknz) {
        super(clientId, clientSecret);
        this.issuerLocation = issuerLocation;
        this.bhknz = bhknz;
        setAuthenticated(false);
    }

    public String getIssuerLocation() {
        return issuerLocation;
    }

    public String getClientId() {
        return getPrincipal().toString();
    }

    public String getClientSecret() {
        return getCredentials().toString();
    }

    @Nullable
    public String getBhknz() {
        return bhknz;
    }
}
