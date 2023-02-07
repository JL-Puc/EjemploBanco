package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;

import seguridad.Encriptador;


public class SerializarObjeto {
    
    public static boolean serializarObjeto(String direccionArchivo, Serializable objeto) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException {
        boolean sw = false;

        Encriptador encrip = new Encriptador();
        SealedObject sealedObject = encrip.encriptar(objeto, "AES", direccionArchivo);

        try (FileOutputStream fos = new FileOutputStream(direccionArchivo);
                ObjectOutputStream salida = new ObjectOutputStream(fos);) {
            salida.writeObject(sealedObject);
            sw = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw;
    }

    public static <E> E deserializarObjeto(String direccionArchivo, Class<E> claseObjeto) {
        E objeto = null;

        try (FileInputStream fis = new FileInputStream(direccionArchivo);
                ObjectInputStream entrada = new ObjectInputStream(fis);) {
            objeto = (E) entrada.readObject();

            Encriptador encrip = new Encriptador();
            objeto = encrip.desencriptar((SealedObject)objeto, claseObjeto, direccionArchivo);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return objeto;
    }





}
