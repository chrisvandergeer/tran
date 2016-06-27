package nl.cge.tran.web.wicket.ui;

import nl.cge.tran.web.ui.homepage.Transaktiepage;
import nl.cge.tran.web.ui.importpage.ImportPage;
import nl.cge.tran.web.ui.tagpage.TagPage;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BootstrapPage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	public BootstrapPage(final PageParameters parameters) {
		super(parameters);
		add(new BookmarkablePageLink<WebPage>("transactiesPagina", Transaktiepage.class));
		add(new BookmarkablePageLink<WebPage>("importPagina", ImportPage.class));
		add(new BookmarkablePageLink<WebPage>("tagPagina", TagPage.class));
	}

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(BootstrapCss.BOOTSTRAP));
        response.render(JavaScriptReferenceHeaderItem.forReference(Chartjs.CHARTJS));
    }
}
