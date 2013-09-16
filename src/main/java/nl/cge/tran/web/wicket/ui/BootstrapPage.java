package nl.cge.tran.web.wicket.ui;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BootstrapPage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	public BootstrapPage(final PageParameters parameters) {
		super(parameters);
	}

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(BootstrapCss.BOOTSTRAP));
    }
}
