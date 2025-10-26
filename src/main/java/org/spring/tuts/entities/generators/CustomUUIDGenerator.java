package org.spring.tuts.entities.generators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.security.*;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class CustomUUIDGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        int randomKey = (int) (Math.random() * 10 + 1);
        return sign("Employee" + randomKey + ":" + new Date().getTime());
    }

    public String sign(String primaryKey) {
        try {
            KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
            kg.initialize(2048);
            KeyPair kp = kg.generateKeyPair();
            PrivateKey pk = kp.getPrivate();

            Signature sg = Signature.getInstance("SHA256withRSA");
            sg.initSign(pk);
            sg.update(primaryKey.getBytes());
            byte[] result = sg.sign();
            return primaryKey + "." + Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }

    }
}
