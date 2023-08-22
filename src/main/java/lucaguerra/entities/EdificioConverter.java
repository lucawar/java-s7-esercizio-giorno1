package lucaguerra.entities;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import jakarta.persistence.AttributeConverter;

public class EdificioConverter implements AttributeConverter<String, String> {

	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final String secret = "mysup3rs3cr3tttt";

	@Override
	public String convertToDatabaseColumn(String Edificio) {
		try {
			Key key = new SecretKeySpec(secret.getBytes(), "AES");
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);

			return Base64.getMimeEncoder().encodeToString(c.doFinal(Edificio.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public String convertToEntityAttribute(String encryptedEdificio) {
		try {
			Key key = new SecretKeySpec(secret.getBytes(), "AES");

			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			return new String(c.doFinal(Base64.getDecoder().decode(encryptedEdificio)));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
