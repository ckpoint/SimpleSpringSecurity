package hsim.simple.security.util;

import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePasswordEncoder {

    private static PasswordEncoder passwordEncoder;

    public static void initPasswordEncoder(PasswordEncoder pe) {
        passwordEncoder = pe;
    }

    public static String encodePassword(@NonNull String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
