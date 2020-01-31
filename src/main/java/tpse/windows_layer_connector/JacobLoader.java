package tpse.windows_layer_connector;

import com.jacob.com.LibraryLoader;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import tpse.log.Log;
import tpse.log.LogSubTypes;
import tpse.log.LogTypes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static tpse.log.Log.LOG_UPDATE_URL;

public class JacobLoader {

    public static final String DLL_PATH_FOR_32_BIT_SYSTEM = "/external_libraries/jacob-1.14.3-x86.dll";
    public static final String DLL_PATH_FOR_64_BIT_SYSTEM = "/external_libraries/jacob-1.14.3-x64.dll";
    public static final String LOG_DLL_LOADED = "Jacob dll was loaded into memory";
    public static final String LOG_DLL_NOT_LOADED = "Library could not be loaded";

    private File temporaryDll;
    private String systemArchitecture;
    private final Log loadLibraryLog = new Log(
            "Desktop app",
            "DLL loader",
            "N/A",
            0,
            "N/A",
            null,
            LogSubTypes.SYSTEM,
            null);

    public void loadLibrary() {
        try {
            String libraryPath = supplyLibraryPath();

            InputStream inputStream = getClass().getResource(libraryPath).openStream();
            temporaryDll = File.createTempFile(LibraryLoader
                    .getPreferredDLLName(), ".dll");

            FileOutputStream outputStream = new FileOutputStream(temporaryDll);

            byte[] array = new byte[8192];
            for (int i = inputStream.read(array); i != -1; i = inputStream.read(array)) {
                outputStream.write(array, 0, i);
            }
            outputStream.close();

            System.load(temporaryDll.getAbsolutePath());
            System.setProperty(LibraryLoader.JACOB_DLL_PATH, temporaryDll.getPath());

            createInfoLog();

        } catch (Throwable e) {
            createErrorLog(e);
            e.printStackTrace();
        }
    }

    private void createErrorLog(Throwable throwable) {
        loadLibraryLog.setType(LogTypes.ERROR);
        loadLibraryLog.setAdditionalDescription(String.format("%s %s %s", LOG_DLL_NOT_LOADED, throwable.toString(), throwable.getStackTrace()[0].toString()));
        submitLog();
    }

    private void createInfoLog() {
        loadLibraryLog.setType(LogTypes.INFO);
        loadLibraryLog.setAdditionalDescription(String.format("%s (X%s bit)", LOG_DLL_LOADED, systemArchitecture));
        submitLog();
    }

    private void submitLog() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Log> logRequest = new HttpEntity<>(loadLibraryLog);
        restTemplate.postForObject(LOG_UPDATE_URL, logRequest, Log.class);
    }


    /**
     * depending on SDK architecture it supplies system with proper dll
     * either 64 or 32 bit
     */
    private String supplyLibraryPath() throws IOException {
        systemArchitecture = System.getProperty("sun.arch.data.model");

        switch (systemArchitecture) {
            case "32":
                return DLL_PATH_FOR_32_BIT_SYSTEM;
            case "64":
                return DLL_PATH_FOR_64_BIT_SYSTEM;
            default:
                throw new IOException("System architecture is unknown");
        }
    }
}
