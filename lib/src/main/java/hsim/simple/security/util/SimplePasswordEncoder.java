package hsim.simple.security.util;

import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The type Simple password encoder.
 */
public class SimplePasswordEncoder {

    private static PasswordEncoder passwordEncoder;

    /**
     * Init password encoder.
     *
     * @param pe the pe
     */
    public static void initPasswordEncoder(PasswordEncoder pe) {
        passwordEncoder = pe;
    }

    /**
     * Encode password string.
     *
     * @param plainPassword the plain password
     * @return the string
     */
    public static String encodePassword(@NonNull String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
