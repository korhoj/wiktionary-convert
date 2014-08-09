package wiktionary.to.xml.full.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * Utility for making deep copies (vs. clone()'s shallow copies) of
 * objects. Objects are first serialized and then deserialized. Error
 * checking is fairly minimal in this implementation. If an object is
 * encountered that cannot be serialized (or that references an object
 * that cannot be serialized) an error is printed to System.err and
 * null is returned. Depending on your specific application, it might
 * make more sense to have copy(...) re-throw the exception.
 */
public class DeepCopy {

    /**
     * Returns a copy of the object, or null if the object cannot
     * be serialized.
     */
    public static Object copy(Object orig) {
        Object obj = null;
        try {
            // Write the object out to a byte array
            FastByteArrayOutputStream fbos =
                    new FastByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(fbos);
            out.writeObject(orig);
            out.flush();
            out.close();
            out = null;

            // Retrieve an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in =
                new ObjectInputStream(fbos.getInputStream());
            obj = in.readObject();
            in = null;
            fbos = null;
        }
        catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException("DeepCopy failed: " + e.getMessage());
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            throw new RuntimeException("DeepCopy failed: " + cnfe.getMessage());
        } 
        return obj;
    }
}