package nl.cge.tran.web.ui.oldhomepage;

import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.service.TransaktieService;
import nl.cge.tran.web.wicket.ui.BootstrapPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends BootstrapPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
        add(new SearchForm("searchForm"));
        PageableListView<Transaktie> listView = createTransaktieListView("transakties");
		add(new PagingNavigator("pager", listView));
		add(listView);
    }

    private PageableListView<Transaktie> createTransaktieListView(final String id) {
        List<Transaktie> transakties = TransaktieService.Instance.findAll();
        return new PageableListView<Transaktie>(id, transakties, 25) {
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<Transaktie> item) {
                Transaktie transaktie = item.getModelObject();
                item.add(new Label("datum", transaktie.getDatum()));
                item.add(new Label("tegenrekeningnaam", transaktie.getTegenrekening() + " " + transaktie.getTegenrekeningnaam()));
                item.add(new Label("bedrag", transaktie.getBedrag()));
                item.add(new Label("omschrijving", transaktie.getOmschrijving1()));
            }
        };
    }    

	@Override
    protected void onBeforeRender() {
        SearchCriteria crit = ((SearchForm) get("searchForm")).getModelObject();
        List<Transaktie> transakties = TransaktieService.Instance.findAll(crit);
        PageableListView<Transaktie> listView = (PageableListView<Transaktie>) get("transakties");
        listView.setDefaultModelObject(transakties);
        super.onBeforeRender();
    }

}
