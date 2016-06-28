package nl.cge.tran.web.ui.homepage;

import java.util.List;

import nl.cge.tran.domein.TaggedQuery;
import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.service.TransaktieService;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TagformPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private IModel<List<? extends Transaktie>> transakties;
	private TextField<String> tag;

	public TagformPanel(String id, final IModel<List<? extends Transaktie>> transakties) {
		super(id);
		this.transakties = transakties;
		Form<Void> form = new Form<Void>("tagForm");
		tag = new TextField<String>("tag", Model.of(""));
		form.add(tag);
		form.add(newOpslaanButton("opslaanBtn"));
		add(form);
	}

	private Button newOpslaanButton(String id) {
		return new Button(id) {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				TaggedQuery taggedQuery = TaggedQuery.create("myquery", tag.getModelObject());
				TransaktieService.Instance.addTag(transakties.getObject(), taggedQuery);
			}
		};
	}

}
