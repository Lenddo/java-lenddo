package com.lenddo.javaapi.utils;

import com.lenddo.javaapi.models.EncryptedResponse;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;

public class DecryptionUtil {

    public String decryptData(EncryptedResponse encryptedResponse, String privateKey) {

        String key = encryptedResponse.getEncryptionBody().getKey();
        String data = encryptedResponse.getEncryptionBody().getData();
        String initVector = encryptedResponse.getEncryptionBody().getIv();

        byte[] decodedKey = java.util.Base64.getDecoder().decode(key);
        byte[] decodedIv = java.util.Base64.getDecoder().decode(initVector);

        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            PEMParser pemParser;
            pemParser = new PEMParser(new FileReader(new File(privateKey)));
            Object object = pemParser.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            KeyPair keyPair;
            PEMKeyPair ukp = (PEMKeyPair) object;
            keyPair = converter.getKeyPair(ukp);

            // RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateCrtKeySpec.class);
            PrivateKey rsaPrivateKey = keyFactory.generatePrivate(privateKeySpec);
            Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            final byte[] decryptedKeyBytes = cipher.doFinal(decodedKey);
            final byte[] decryptedIvBytes = cipher.doFinal(decodedIv);

            // AES
            IvParameterSpec ivParameterSpec = new IvParameterSpec(decryptedIvBytes);
            SecretKeySpec secretKeySpec = new SecretKeySpec(decryptedKeyBytes, "AES");
            Cipher cipherAes = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipherAes.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] dataBytes = cipherAes.doFinal(Base64.getDecoder().decode(data));
            String result = new String(dataBytes);
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (PEMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
