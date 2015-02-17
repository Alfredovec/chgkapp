package models.entities;

/**
 * Created by Sergey on 17.02.2015.
 */
public class SvoyakQuestion extends Question
{
    private String themeName;

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
