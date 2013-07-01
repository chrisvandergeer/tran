package nl.cge.tran;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: NOHi
 * Date: 4-6-13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class SearchCriteria implements Serializable {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean hasText() {
        return text != null && !"".equals(text.trim());
    }
}
