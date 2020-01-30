package tpse.windows_layer_connector;

import com.jacob.com.LibraryLoader;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JacobLoader {

    public static final String DLL_PATH_FOR_32_BIT_SYSTEM ="/external_libraries/jacob-1.14.3-x86.dll";
    public static final String DLL_PATH_FOR_64_BIT_SYSTEM ="/external_libraries/jacob-1.14.3-x64.dll";

    private File temporaryDll;

    public boolean loadLibrary() {
        try {
            System.out.println(supplyLibraryPath());
            InputStream inputStream = getClass().getResource(supplyLibraryPath()).openStream();
            temporaryDll = File.createTempFile(LibraryLoader
                    .getPreferredDLLName(), ".dll");
            FileOutputStream outputStream = new FileOutputStream(temporaryDll);
            byte[] array = new byte[8192];
            for (int i = inputStream.read(array); i != -1; i = inputStream
                    .read(array)) {
                outputStream.write(array, 0, i);
            }
            outputStream.close();
            temporaryDll.deleteOnExit();

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

    /**
     * depending on SDK architecture it supplies system with proper dll
     * either 64 or 32 bit
     */
    private String supplyLibraryPath() throws IOException {
        String systemArchitectureProp = System.getProperty("sun.arch.data.model");

        switch (systemArchitectureProp) {
            case "32":
                return DLL_PATH_FOR_32_BIT_SYSTEM;
            case "64":
                return DLL_PATH_FOR_64_BIT_SYSTEM;
            default:
                throw new IOException("System architecture is unknown");
        }
    }
}
