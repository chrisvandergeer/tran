package nl.cge.tran.web.ui.homepage;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.service.TransaktieService;
import nl.cge.tran.web.wicket.ui.BootstrapPage;

public class Transaktiepage extends BootstrapPage {
	private static final long serialVersionUID = 1L;
	
	public Transaktiepage(PageParameters parameters) {
		super(parameters);
		add(new SearchForm("searchForm"));
		add(tagForm("tagForm"));
		PageableListView<Transaktie> listView = createTransaktieListView("transakties");
		add(new PagingNavigator("pager", listView));
		add(listView);
	}

	private Form<Void> tagForm(String id) {
		Form<Void> form = new Form<Void>(id);
		form.add(new TextField<String>("tag"));
		form.add(new Button("opslaanBtn"));
		return form;
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
                item.add(new Label("omschrijving1", transaktie.getOmschrijving1()));
                item.add(new Label("omschrijving2", transaktie.getOmschrijving2()));
                item.add(new Label("omschrijving3", transaktie.getOmschrijving3()));
                item.add(new Label("omschrijving4", transaktie.getOmschrijving4()));
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
