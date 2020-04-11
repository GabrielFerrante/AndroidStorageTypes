package classes;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

public class Permissions extends AppCompatActivity {

    //Global Permissions variables
    public static final int PERMISSION_SDCARD = 0;

    //Global Parameters
    private Activity activity;
    private String[] permissions;
    private int requestCode;



    //Constructor
    public Permissions(Activity activity, String[] permissions, int requestCode) {
        this.activity = activity;
        this.permissions = permissions;
        this.requestCode = requestCode;
    }

    public Boolean checkPermission(){
        try{
            for(String item : permissions){
                if (ActivityCompat.checkSelfPermission(this.activity,item)!= PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean askPermission(){
        try{
            ActivityCompat.requestPermissions(this.activity,this.permissions,this.requestCode);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults){
        switch (this.requestCode){
            case PERMISSION_SDCARD:{
                if (grantResults[0] ==  PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this.activity, "Permissão concedida!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this.activity, "Permissão negada!",Toast.LENGTH_LONG).show();
                }
            }

        }
    }

}
