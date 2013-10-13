package nl.cge.tran.web.ui.homepage;

import java.util.ArrayList;
import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.web.wicket.labels.Currencylabel;
import nl.cge.tran.web.wicket.labels.Datelabel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class TransaktielijstPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private PageableListView<Transaktie> transaktielijst;

	private IModel<List<? extends Transaktie>> transakties;

	public TransaktielijstPanel(String id, IModel<List<? extends Transaktie>> transakties) {
		super(id);
		this.transakties = transakties;
		transaktielijst = newTransactieListview("transakties");
		add(new PagingNavigator("pager", transaktielijst));
		add(transaktielijst);
	}

	private PageableListView<Transaktie> newTransactieListview(String id) {
		return new PageableListView<Transaktie>(id, transakties, 25) {
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
	
//	public PageableListView<Transaktie> getTransaktielijst() {
//		return transaktielijst;
//	}

}
