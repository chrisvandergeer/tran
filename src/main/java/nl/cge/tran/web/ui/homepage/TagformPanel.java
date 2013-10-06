package nl.cge.tran.web.ui.homepage;

import nl.cge.tran.service.TransaktieService;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class TagformPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public TagformPanel(String id, final TransaktielijstPanel transaktielijstPanel) {
		super(id);
		Form<Void> form = new Form<Void>("tagForm");
		final TextField<String> tag = new TextField<String>("tag", Model.of(""));
		form.add(tag);
		form.add(new Button("opslaanBtn") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				TransaktieService.Instance.saveAll(transaktielijstPanel.getTransaktielijst().getModelObject(), tag.getModelObject());
			}
		});
		add(form);
	}

}
