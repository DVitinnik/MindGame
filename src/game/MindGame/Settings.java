package game.MindGame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.widget.ImageView;


/**
 * Created by Maxis55 on 20-Oct-15.
 */
public class Settings implements Parcelable {
    public static final Creator<Settings> CREATOR = new SettingsCreator();
    String fileName = "settings.xml";
    int levelDifficulty;


    @Override
    public String toString() {
        return "Settings{" +
                "fileName='" + fileName + '\'' +
                ", levelDifficulty=" + levelDifficulty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        if (levelDifficulty != settings.levelDifficulty) return false;

        return !(fileName != null ? !fileName.equals(settings.fileName) : settings.fileName != null);

    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + levelDifficulty;
        return result;
    }

    public Settings() {
        Initialize();
    }

    public Settings(int levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }

    private void Initialize() {

    }

    public int getDifficultyLevel() {
        return levelDifficulty;
    }

    public void setDifficultyLevel(int levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }

    //public SharedPreferences getSharedPreferences (Context context) {
    //    this.context = context;

    //    return context.getSharedPreferences("FILE", 0);
    //}

    public int[] getLevelNumbers() {
        //1 - DotLevel
        //2 - MathLevel
        //3 - FigureLevel
        int k = 1;
        Context applicationContext = SettingsActivity.getContextOfApplication();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        boolean b1checked = prefs.getBoolean("Button 1",false);
        boolean b2checked = prefs.getBoolean("Button 2",false);
        boolean b3checked = prefs.getBoolean("Button 3",false);
        if (b1checked)
            k=1;
        if (b2checked)
            k=2;
        if (b3checked)
            k=3;
        int spin1 = prefs.getInt("Spinner 1",0);
        int spin2 = prefs.getInt("Spinner 2",1);
        int spin3 = prefs.getInt("Spinner 3",2);
        if (k==1)
            return new int[]{spin1+1};
        if (k==2)
            return new int[]{spin2+1,spin3+1};
        if (k==3)
            return new int[]{1,2,3};
        return new int[]{1};
    }


    public Level getLevel(int number, ImageView imageView) {
        switch (number) {
            case 1:
                return new DotLevel(imageView);
            case 2:
                return new MathLevel(imageView);
            case 3:
                return new ColorLevel(imageView);
            default:
                return null;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(String.valueOf(levelDifficulty));
    }

    private static class SettingsCreator implements Creator<Settings> {

        @Override
        public Settings createFromParcel(Parcel parcel) {
            int levelDifficulty = Integer.valueOf(parcel.readString());
            return new Settings(levelDifficulty);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    }
}
