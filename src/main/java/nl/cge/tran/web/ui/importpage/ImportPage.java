package nl.cge.tran.web.ui.importpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.domein.helper.RaboTransaktieFile;
import nl.cge.tran.service.TransaktieService;
import nl.cge.tran.web.wicket.ui.BootstrapPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ImportPage extends BootstrapPage {
	private static final long serialVersionUID = 1L;
	
	private FileUploadField fileUploadField;
	private Label bestaandLabel;
	private Label nieuwLabel;

	public ImportPage(PageParameters parameters) {
		super(parameters);
		Form<Void> form = newFileuploadForm("uploadForm");
		form.add(fileUploadField = new FileUploadField("fileUpload"));
		add(form);
		add(bestaandLabel = new Label("bestaand", Model.of(new Integer(0))));
		add(nieuwLabel = new Label("nieuw", Model.of(new Integer(0))));
	}

	private Form<Void> newFileuploadForm(String id) {
		return new Form<Void>(id) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit() {
				TransaktieService service = TransaktieService.Instance;
				Set<Transaktie> transactieHashtable = TransaktieService.Instance.createHashTable();
				FileUpload fileUpload = fileUploadField.getFileUpload();
				if (fileUpload != null) {
					String content = new String(fileUpload.getBytes());
					RaboTransaktieFile raboTransaktieFile = RaboTransaktieFile.newInstance(content);
					List<Transaktie> transakties = raboTransaktieFile.parse();
					List<Transaktie> transaktiesToInsert = new ArrayList<Transaktie>();
					Integer aantalNieuw = 0;
					Integer aantalOud = 0;
					for (Transaktie t : transakties) {
						if (transactieHashtable.contains(t)) {
							aantalOud++;
						} else {
							transaktiesToInsert.add(t);
							aantalNieuw++;
						}
					}
					service.insert(transaktiesToInsert);
					bestaandLabel.setDefaultModelObject(aantalOud);
					nieuwLabel.setDefaultModelObject(aantalNieuw);
				}
			}
		};
	}

}
