package classes;
import br.edu.ifsp.androidStorageTypes.MainActivity;
import android.app.Activity;
import android.content.ContentProvider;
import android.content.SharedPreferences;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import static android.app.PendingIntent.getActivity;
import static android.content.Context.MODE_PRIVATE;

public final class MethodsSharedPreferences{

    public static final String PREF = "br.edu.ifsp.androidStorageTypes";
    private SharedPreferences preferences;

    private String[] stringsValues = {};
    private int[] intsValues = {};
    private float[] floatsValues = {};
    private boolean[] booleansValues = {};
    private long[] longsValues = {};

    private String[] stringsKeys = {};
    private String[] stringsKeysForInt = {};
    private String[] stringsKeysForFloat = {};
    private String[] stringsKeysForBols = {};
    private String[] stringsKeysForLong = {};

    private Activity activity;
    public MethodsSharedPreferences(Activity activity)
    {
        this.activity = activity;
    }

    public String[] getStringsValues() {
        return stringsValues;
    }

    public void setStringsValues(String[] stringsValues) {
        this.stringsValues = stringsValues;
    }

    public int[] getIntsValues() {
        return intsValues;
    }

    public void setIntsValues(int[] intsValues) {
        this.intsValues = intsValues;
    }

    public float[] getFloatsValues() {
        return floatsValues;
    }

    public void setFloatsValues(float[] floatsValues) {
        this.floatsValues = floatsValues;
    }

    public boolean[] getBooleansValues() {
        return booleansValues;
    }

    public void setBooleansValues(boolean[] booleansValues) {
        this.booleansValues = booleansValues;
    }

    public long[] getLongsValues() {
        return longsValues;
    }

    public void setLongsValues(long[] longsValues) {
        this.longsValues = longsValues;
    }

    public String[] getStringsKeys() {
        return stringsKeys;
    }

    public void setStringsKeys(String[] stringsKeys) {
        this.stringsKeys = stringsKeys;
    }

    public String[] getStringsKeysForInt() {
        return stringsKeysForInt;
    }

    public void setStringsKeysForInt(String[] stringsKeysForInt) {
        this.stringsKeysForInt = stringsKeysForInt;
    }

    public String[] getStringsKeysForFloat() {
        return stringsKeysForFloat;
    }

    public void setStringsKeysForFloat(String[] stringsKeysForFloat) {
        this.stringsKeysForFloat = stringsKeysForFloat;
    }

    public String[] getStringsKeysForBols() {
        return stringsKeysForBols;
    }

    public void setStringsKeysForBols(String[] stringsKeysForBols) {
        this.stringsKeysForBols = stringsKeysForBols;
    }

    public String[] getStringsKeysForLong() {
        return stringsKeysForLong;
    }

    public void setStringsKeysForLong(String[] stringsKeysForLong) {
        this.stringsKeysForLong = stringsKeysForLong;
    }

    private void setPreference(){
        preferences = activity.getSharedPreferences(PREF,Context.MODE_PRIVATE);
    }
    private SharedPreferences.Editor createEditor(){
        SharedPreferences.Editor editor = preferences.edit();
        return editor;
    }
    public boolean sharedPreferencesSaving(){
        try{
            setPreference();
            SharedPreferences.Editor editor = createEditor();

            setStringsEditor(editor,stringsValues);
            setIntEditor(editor, intsValues);
            setFloatEditor(editor,floatsValues);
            setBooleanEditor(editor,booleansValues);
            setLongEditor(editor,longsValues);

            editor.commit();
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public SharedPreferences sharedPreferencesLoading(){
        setPreference();
        return preferences;
    }

    public boolean setStringsEditor(SharedPreferences.Editor edt, String[] stringsValues){
        int i=0;
        try{
            if (stringsValues.length>1){
                for(String item : stringsValues){
                    edt.putString(stringsKeys[i],item);
                    i++;
                }
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public boolean setIntEditor(SharedPreferences.Editor edt, int[] intsValues ){
        int i=0;
        try{
            for (int item: intsValues){
                edt.putInt(stringsKeysForInt[i],item);
                i++;
            }
            return true;
        }catch (Exception e){
            System.out.println("ERRO AKI SET 2");
            e.printStackTrace();
            return false;
        }
    }
    public boolean setFloatEditor(SharedPreferences.Editor edt, float[] floatsValues){
        int i=0;
        try{
            for (float item: floatsValues){
                edt.putFloat(stringsKeysForFloat[i],item);
                i++;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean setBooleanEditor(SharedPreferences.Editor edt, boolean[]booleansValues){
        int i=0;
        try{
            for (boolean item:booleansValues ){
                edt.putBoolean(stringsKeysForBols[i],item);
                i++;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean setLongEditor(SharedPreferences.Editor edt, long[]longsValues){
        int i=0;
        try{
            for (long item:longsValues ){
                edt.putLong(stringsKeysForLong[i],item);
                i++;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
