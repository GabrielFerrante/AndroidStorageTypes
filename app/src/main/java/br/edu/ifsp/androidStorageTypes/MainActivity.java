package br.edu.ifsp.androidStorageTypes;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import classes.MethodsExternalStorage;
import classes.Pessoa;
import classes.MethodsSharedPreferences;
import classes.MethodsInternalStorage;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    public static final String FILENAMEinternal = "internoMainFILENAME";

    public static final String nameKEY = "Name";
    public static final String lastKEY = "Lastname";
    public static final String ageKEY = "Age";
    public static final String sexKEY = "Sex";

    public static final String[] keyListForStrings= {nameKEY,lastKEY};
    public static final String[] keyListForInts = {ageKEY};
    public static final String[] keyListForBols={sexKEY};

    EditText name,lastName,age;
    RadioButton sexFemale, sexMale, sharedSave, internalSave, externalSave,
            sharedLoad,internalLoad,externalLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pegando os valores do itens acima

        name = findViewById(R.id.txtName);
        lastName = findViewById(R.id.txtLastName);
        age = findViewById(R.id.txtAge);
        sexFemale = findViewById(R.id.rbFemale);
        sexMale = findViewById(R.id.rbMale);

        sharedSave = findViewById(R.id.rbSharedSave);
        internalSave = findViewById(R.id.rbInternalSave);
        externalSave = findViewById(R.id.rbExternalSave);

        sharedLoad = findViewById(R.id.rbSharedLoad);
        internalLoad = findViewById(R.id.rbInternalLoad);
        externalLoad = findViewById(R.id.rbExternalLoad);

        MethodsSharedPreferences msp = new MethodsSharedPreferences(this);
        this.preferences = msp.sharedPreferencesLoading();
    }
    protected void resetFields(){
        name.setText("");
        lastName.setText("");
        age.setText("0");
        sexMale.setChecked(true);
        sharedSave.setChecked(true);
        sharedLoad.setChecked(true);
    }
    protected boolean verifyEmptys(){
        if (name.getText().toString().equals("")
            || lastName.getText().toString().equals("")
            || age.getText().toString().equals("")
            ||(sexFemale.isChecked() == false && sexMale.isChecked() == false)
        ){
            return true;
        }
        return false;
    }
    public Pessoa creatingPessoa(){
        //Creating object
        return new Pessoa(
                name.getText().toString(),
                lastName.getText().toString(),
                Integer.parseInt(age.getText().toString()),
                sexMale.isChecked());

    }
    public boolean savingSharedPreferences(Pessoa p){
        MethodsSharedPreferences msp = new MethodsSharedPreferences(this);

        msp.setStringsKeys(keyListForStrings);// Keys for strings datas
        msp.setStringsKeysForInt(keyListForInts);//Keys for int datas
        msp.setStringsKeysForBols(keyListForBols ); // Keys for boolean datas

        msp.setStringsValues(new String[]{p.getName(),p.getLastName()});//Strings datas
        msp.setIntsValues(new int[]{p.getAge()}); //Integers datas
        msp.setBooleansValues(new boolean[]{p.isSex()});// Boolean datas

        if(msp.sharedPreferencesSaving()){
            this.preferences = msp.sharedPreferencesLoading();
            return true;
        }else{
            return false;
        }
    }
    public boolean loadingSharedPreferences(){
        try{
            name.setText(preferences.getString(nameKEY,""));
            lastName.setText(preferences.getString(lastKEY,""));
            age.setText(String.valueOf(preferences.getInt(ageKEY,0)));
            if(preferences.getBoolean(sexKEY,true)){
                sexMale.setChecked(true);
                sexFemale.setChecked(false);
            }else{
                sexMale.setChecked(false);
                sexFemale.setChecked(true);
            }
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public boolean savingInternalStorage(Pessoa p){
        try{
            MethodsInternalStorage mis = new MethodsInternalStorage(this,p,FILENAMEinternal);
            mis.savingOutput();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean loadingInternalStorage(){
        try{

            MethodsInternalStorage mis = new MethodsInternalStorage(this,creatingPessoa(),FILENAMEinternal);

            Pessoa p = (Pessoa) mis.loadingInput();

            name.setText(p.getName());
            lastName.setText(p.getLastName());
            age.setText(String.valueOf(p.getAge()));

            if (p.isSex()){
                sexMale.setChecked(true);
                sexFemale.setChecked(false);
            }else{
                sexMale.setChecked(false);
                sexFemale.setChecked(true);
            }
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    public int savingExternalStorage(Pessoa p){
        try{
            if(MethodsExternalStorage.savingOutput(this,p) == 1 ){
                return 1;
            }else if(MethodsExternalStorage.savingOutput(this,p) == 2){
                return 2;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 3;
    }
    public boolean loadingExternalStorage(){
        try{
            Pessoa p = (Pessoa) MethodsExternalStorage.loadingInput(this);

            name.setText(p.getName());
            lastName.setText(p.getLastName());
            age.setText(String.valueOf(p.getAge()));

            if (p.isSex()){
                sexMale.setChecked(true);
                sexFemale.setChecked(false);
            }else{
                sexMale.setChecked(false);
                sexFemale.setChecked(true);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public void saving(View v){
        if (!verifyEmptys()){
            Pessoa p = creatingPessoa();
            if(sharedSave.isChecked()){
                if(savingSharedPreferences(p)){
                    Toast.makeText(this,"Succefully - SharedPreferences!",Toast.LENGTH_SHORT).show();
                    resetFields();
                }else{
                    Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
                }
            }else if(internalSave.isChecked()){
                if (savingInternalStorage(p)){
                    Toast.makeText(this,"Succefully - Internal Storage!",Toast.LENGTH_SHORT).show();
                    resetFields();
                }else{
                    Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
                }
            }else{
                if(savingExternalStorage(p)==1){
                    Toast.makeText(this,"Succefully - External Storage!",Toast.LENGTH_SHORT).show();
                    resetFields();
                }else if(savingExternalStorage(p)==2){
                    Toast.makeText(this,"Please, accept the permission for continue!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"ERROR!",Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this,"Empty Fields or missing, fill the fields please!",Toast.LENGTH_SHORT).show();
        }

    }
    public void loading(View v){
        if(sharedLoad.isChecked()){
            if(loadingSharedPreferences()){
                Toast.makeText(this,"Recovered datas - SharedPreferences",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Not found datas or not exists",Toast.LENGTH_SHORT).show();
            }
        }else if(internalLoad.isChecked()){
            if(loadingInternalStorage()){
                Toast.makeText(this,"Recovered datas - Internal Storage",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Not found datas or not exists",Toast.LENGTH_SHORT).show();
            }
        }else{
            if(loadingExternalStorage()){
                Toast.makeText(this,"Recovered datas - External Storage",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Not found datas or not exists",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
