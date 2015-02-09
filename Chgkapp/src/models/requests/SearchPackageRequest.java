package models.requests;

import java.io.Serializable;

/**
 * Created by Sergey on 09.02.2015.
 */
public class SearchPackageRequest extends BasicRequest implements Serializable
{
    private String textRequest;

    public SearchPackageRequest(String textRequest)
    {
        this.setTextRequest(textRequest);
    }

    public String getTextRequest() {
        return textRequest;
    }

    public void setTextRequest(String textRequest) {
        this.textRequest = textRequest;
    }
}
