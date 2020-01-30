package tpse.windows_layer_connector;

import com.jacob.com.LibraryLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class JacobLoader {


    public boolean loadLibrary() {
        try {
            InputStream inputStream = getClass().getResource("/external_libraries/jacob-1.14.3-x86.dll").openStream();
            File temporaryDll = File.createTempFile(LibraryLoader
                    .getPreferredDLLName(), ".dll");
            FileOutputStream outputStream = new FileOutputStream(temporaryDll);
            byte[] array = new byte[8192];
            for (int i = inputStream.read(array); i != -1; i = inputStream
                    .read(array)) {
                outputStream.write(array, 0, i);
            }
            outputStream.close();
            temporaryDll.deleteOnExit();
            // Ask LibraryLoader to load the dll for us based on the path we
            // set
            System.out.println("Loading " + temporaryDll.getAbsolutePath());
            System.load(temporaryDll.getAbsolutePath());
            System.setProperty(LibraryLoader.JACOB_DLL_PATH, temporaryDll
                    .getPath());
//            LibraryLoader.loadJacobLibrary();
            System.out.println("Deleting temp dll: " + temporaryDll.delete());
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }
}
