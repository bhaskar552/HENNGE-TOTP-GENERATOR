import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class TOTP {
    private static final long STEP_SECONDS = 30;
    private static final String CRYPTO_ALGORITHM = "HmacSHA512";
    private static final int DIGITS = 10;

    public static String generateTOTP(String userId) {
        String secret = userId + "HENNGECHALLENGE003";
        long currentTime = new Date().getTime() / 1000L;
        return generateTOTP(secret, currentTime, STEP_SECONDS, DIGITS, CRYPTO_ALGORITHM);
    }

    private static String generateTOTP(String key, long time, long stepSeconds, int digits, String cryptoAlgorithm) {
        byte[] msg = ByteBuffer.allocate(8).putLong(time / stepSeconds).array();
        byte[] hash = hmacSha(cryptoAlgorithm, key.getBytes(), msg);

        int offset = hash[hash.length - 1] & 0xf;
        long binary = ((hash[offset] & 0x7f) << 24) |
                ((hash[offset + 1] & 0xff) << 16) |
                ((hash[offset + 2] & 0xff) << 8) |
                (hash[offset + 3] & 0xff);

        long otp = binary % (long) Math.pow(10, digits);
        return String.format("%0" + digits + "d", otp);
    }

    private static byte[] hmacSha(String crypto, byte[] keyBytes, byte[] text) {
        try {
            Mac hmac = Mac.getInstance(crypto);
            SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
            hmac.init(macKey);
            return hmac.doFinal(text);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String userId = "YOUR REGISTERED EMAIL";
        String totp = generateTOTP(userId);
        System.out.println("Generated TOTP: " + totp);
    }
}
