package seguridad;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encriptador {

    public SealedObject encriptar(Serializable objeto, String algoritmo, String clave) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, IOException {
        SealedObject sealedObject;

        SecretKey secretKey = crearClave(clave, algoritmo);

        Cipher cipher = Cipher.getInstance(algoritmo);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        sealedObject = new SealedObject(objeto, cipher);
        
        return sealedObject;
    }


    public SecretKey crearClave(String clave, String algoritmo) throws UnsupportedEncodingException, NoSuchAlgorithmException{

        byte[] claveEncriptacion = clave.getBytes("UTF-8");
        
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

        claveEncriptacion = messageDigest.digest(claveEncriptacion);

        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);

        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, algoritmo);

        return secretKey;

    }


    public <E> E desencriptar(SealedObject objeto, Class<E> claseObjeto, String clave) throws NoSuchAlgorithmException, InvalidKeyException, ClassNotFoundException, IOException {

        E obj = null;

        SecretKey secretKey = crearClave(clave, objeto.getAlgorithm());

        obj = (E) objeto.getObject(secretKey);

        return obj;
    }







    
}
