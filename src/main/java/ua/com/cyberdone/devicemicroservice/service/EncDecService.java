package ua.com.cyberdone.devicemicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;


@Slf4j
@Service
public class EncDecService {
    @Value("${security.aes-key}")
    private byte[] securityKey;
    private static final String CIPHER = "AES/ECB/NoPadding";
    private static final Charset CHARSET = StandardCharsets.ISO_8859_1;

    private SecretKeySpec generateKey() throws UnsupportedEncodingException {
        return new SecretKeySpec(securityKey, "AES");
    }

    public String encrypt(String message) throws GeneralSecurityException {
        try {
            var cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey());
            return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(CHARSET)));
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public String decrypt(String b64Text) throws GeneralSecurityException {
        try {
            var cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, generateKey());
            return new String(cipher.doFinal(Base64.getDecoder().decode(b64Text)), CHARSET).replaceAll("}%.+$", "}");
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }
}
