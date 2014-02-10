package nl.cge.tran.web.panels;

import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.web.wicket.labels.Currencylabel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TotaaltabelPanel extends Panel {
	private static final long serialVersionUID = 4874068632825335418L;
	private IModel<List<? extends Transaktie>> transaktieList;

	public TotaaltabelPanel(String id, IModel<List<? extends Transaktie>> transakties) {
		super(id);
		this.transaktieList = transakties;
		add(newHeaderList("headerList"));
		add(newTotaalbedragList("bedragList"));
		add(newTotaalbedragPositief("bedragPosList"));
		add(new ListView<Double>("bedragNegList") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(final ListItem<Double> item) {
				Currencylabel currencylabel = new Currencylabel("bedrag", item.getModelObject()) {
					@Override
					public boolean isVisible() {
						return item.getModelObject().intValue() != 0;
					}
				};
				item.add(currencylabel);
			}
			@Override
			protected void onBeforeRender() {
				setList(new MaandTotaalData(transaktieList).getTotalenNegatief());
				super.onBeforeRender();
			}
		});
	}

	private ListView<Double> newTotaalbedragPositief(String id) {
		return new ListView<Double>(id) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(final ListItem<Double> item) {
				Currencylabel currencylabel = new Currencylabel("bedrag", item.getModelObject()) {
					@Override
					public boolean isVisible() {
						return item.getModelObject().intValue() != 0;
					}
				};
				item.add(currencylabel);
			}
			@Override
			protected void onBeforeRender() {
				setList(new MaandTotaalData(transaktieList).getTotalenPositief());
				super.onBeforeRender();
			}
		};
	}

	private ListView<Double> newTotaalbedragList(String id) {
		return new ListView<Double>(id) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(final ListItem<Double> item) {
				Currencylabel currencylabel = new Currencylabel("bedrag", item.getModelObject()) {
					@Override
					public boolean isVisible() {
						return item.getModelObject().intValue() != 0;
					}
				};
				item.add(currencylabel);
			}
			@Override
			protected void onBeforeRender() {
				setList(new MaandTotaalData(transaktieList).getTotalen());
				super.onBeforeRender();
			}
		};
	}

	private ListView<String> newHeaderList(String id) {
		ListView<String> headerList = new ListView<String>(id) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<String> item) {
				item.add(new Label("headerValue", Model.of(item.getModelObject())));
			}
			@Override
			protected void onBeforeRender() {
				setList(new MaandTotaalData(transaktieList).getMaanden());
				super.onBeforeRender();
			}
		};
		return headerList;
	}

}
