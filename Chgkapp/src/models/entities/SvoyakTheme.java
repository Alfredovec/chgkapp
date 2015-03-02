package models.entities;

import java.util.ArrayList;

/**
 * Created by Sergey on 17.02.2015.
 */
public class SvoyakTheme extends Question
{
    private String themeName;

    private String themeInfo;

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeInfo() {
        return themeInfo;
    }

    public void setThemeInfo(String themeInfo) {
        this.themeInfo = themeInfo;
    }
}
