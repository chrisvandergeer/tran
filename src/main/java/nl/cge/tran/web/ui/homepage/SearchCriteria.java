package nl.cge.tran.web.ui.homepage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: NOHi
 * Date: 4-6-13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class SearchCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String text;
	private String tag;

    public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

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
