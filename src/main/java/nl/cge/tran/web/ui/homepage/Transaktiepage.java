package nl.cge.tran.web.ui.homepage;

import java.util.ArrayList;
import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.service.TotalCalculator;
import nl.cge.tran.service.TransaktieService;
import nl.cge.tran.web.wicket.labels.Currencylabel;
import nl.cge.tran.web.wicket.labels.Datelabel;
import nl.cge.tran.web.wicket.ui.BootstrapPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Transaktiepage extends BootstrapPage {
	private static final long serialVersionUID = 1L;
	private PageableListView<Transaktie> listView;
	private TotalCalculator totalen = new TotalCalculator();
	
	public Transaktiepage(PageParameters parameters) {
		super(parameters);
		List<Transaktie> transakties = TransaktieService.Instance.findAll();
		add(new SearchForm("searchForm"));
		add(tagForm("tagForm"));		
		listView = createTransaktieListView("transakties");
		add(new PagingNavigator("pager", listView));
		add(listView);		
		add(new Currencylabel("totaal", new PropertyModel<TotalCalculator>(totalen, "totaal")));
		add(new Currencylabel("totaalPositief", new PropertyModel<TotalCalculator>(totalen, "totaalPositief")));
		add(new Currencylabel("totaalNegatief", new PropertyModel<TotalCalculator>(totalen, "totaalNegatief")));
	}

	private Form<Void> tagForm(String id) {
		Form<Void> form = new Form<Void>(id);
		final TextField<String> tag = new TextField<String>("tag", Model.of(""));
		form.add(tag);
		form.add(new Button("opslaanBtn") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				TransaktieService.Instance.saveAll(listView.getModelObject(), tag.getModelObject());
			}
		});
		return form;
	}
	
	private PageableListView<Transaktie> createTransaktieListView(final String id) {
        return new PageableListView<Transaktie>(id, new ArrayList<Transaktie>(), 25) {
			private static final long serialVersionUID = 1L;
			protected void populateItem(ListItem<Transaktie> item) {
                final Transaktie transaktie = item.getModelObject();
                item.add(new Datelabel("datum", transaktie.getDatum()));
                item.add(new Label("tegenrekeningnaam", transaktie.getTegenrekening() + " " + transaktie.getTegenrekeningnaam()));
                item.add(new Currencylabel("bedrag", transaktie.getBedrag()));
                item.add(new Label("omschrijving1", transaktie.getOmschrijving1()));
                item.add(new Label("omschrijving2", transaktie.getOmschrijving2()));
                item.add(new Label("omschrijving3", transaktie.getOmschrijving3()));
                item.add(new Label("omschrijving4", transaktie.getOmschrijving4()));
                ListView<String> taglist = new ListView<String>("tags", new ArrayList<String>(transaktie.getTags())) {
                	private static final long serialVersionUID = 1L;	
					@Override
					protected void populateItem(ListItem<String> item) {
						item.add(new Label("tagname", item.getModelObject()));
					}                	
                };
                item.add(taglist);
            }
        };
    } 
	
	@Override
    protected void onBeforeRender() {
        SearchCriteria crit = ((SearchForm) get("searchForm")).getModelObject();
        List<Transaktie> transakties = TransaktieService.Instance.findTransakties(crit);
        listView.setDefaultModelObject(transakties);
        totalen.reCalculate(transakties);
        super.onBeforeRender();
    }
	
}
