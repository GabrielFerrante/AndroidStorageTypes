package classes;

import android.Manifest;
import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

public final class MethodsExternalStorage {
    public static final String FILENAME = "externalFile";
    public static String PERMISSION =  Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private static String getState(){
        return Environment.getExternalStorageState();
    }

    private static boolean verifyMountedSD() {
        if (Environment.MEDIA_MOUNTED.equals(getState())) {
            return true;
        }
        return false;
    }
    private static boolean verifyWriteAndRead(Activity activity){
        if(verifyMountedSD()||Environment.MEDIA_MOUNTED_READ_ONLY.equals(getState())) {
            Permissions pe = new Permissions(activity, new String[]{PERMISSION}, 0);
            if (pe.checkPermission()) {
                return true;
            }
        }
        return false;
    }

    private static File getPathFile(){
        return Environment.getExternalStorageDirectory();
    }

    public static int savingOutput(Activity activity, Serializable obj){
        try{
            if(verifyMountedSD()) {
                Permissions pe = new Permissions(activity,new String[]{PERMISSION},0);
                if(pe.checkPermission()){
                    File file = new File(getPathFile(), FILENAME);
                    ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(file));
                    oss.writeObject(obj);
                    oss.close();
                    return 1;
                }else{
                    if(pe.askPermission());
                }
                return 2;
            }
            return 3;
        }catch (FileNotFoundException fe){
            fe.printStackTrace();
            return 3;
        }catch (IOException iox){
            iox.printStackTrace();
            return 3;
        }catch (Exception e){
            e.printStackTrace();
            return 3;
        }
    }
    public static Serializable loadingInput(Activity activity){
        try {
            if (verifyWriteAndRead(activity)) {
                File file = new File(getPathFile(), FILENAME);
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Serializable obj;
                obj = (Serializable) ois.readObject();

                ois.close();
                return obj;
            }
        }catch (StreamCorruptedException sce){
            sce.printStackTrace();
        }catch (IOException iox){
            iox.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
