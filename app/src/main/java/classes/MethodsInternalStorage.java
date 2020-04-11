package classes;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class MethodsInternalStorage{

    private Serializable object;
    private Activity activity;
    private String FILENAME;

    public MethodsInternalStorage(Activity activity, Serializable object, String FILENAME) {
        this.object = object;
        this.activity = activity;
        this.FILENAME = FILENAME;
    }
    public boolean savingOutput(){
        try{
            FileOutputStream fou = this.activity.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oss = new ObjectOutputStream(fou);

            oss.writeObject(this.object);
            oss.close();
            fou.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Serializable loadingInput(){
        try{
            System.out.print("ENTROU NO LOADING MIS");
            FileInputStream fin = this.activity.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fin);
            this.object = (Serializable) ois.readObject();
            ois.close();
            fin.close();

            return this.object;

        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }
}
