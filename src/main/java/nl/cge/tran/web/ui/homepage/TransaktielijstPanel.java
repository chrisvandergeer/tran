package nl.cge.tran.web.ui.homepage;

import java.util.ArrayList;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.web.wicket.labels.Currencylabel;
import nl.cge.tran.web.wicket.labels.Datelabel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;

public class TransaktielijstPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private PageableListView<Transaktie> transaktielijst;

	public TransaktielijstPanel(String id) {
		super(id);
		transaktielijst = new PageableListView<Transaktie>("transakties", new ArrayList<Transaktie>(), 25) {
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
		add(new PagingNavigator("pager", transaktielijst));
		add(transaktielijst);
	}
	
	public PageableListView<Transaktie> getTransaktielijst() {
		return transaktielijst;
	}

}
