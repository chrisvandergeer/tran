package nl.cge.tran.web.panels;

import java.util.Map;

import nl.cge.tran.domein.Money;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.Response;

public class MaandgrafiekPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private MaandTotaalData grafiekData;

	public MaandgrafiekPanel(String id, MaandTotaalData data) {
		super(id);
		grafiekData = data;
		add(newGrafiekdataContainer("chartdata"));
	}
	
	@Override
	public boolean isVisible() {
		return grafiekData.hasData();
	}

	private WebMarkupContainer newGrafiekdataContainer(String id) {
		return new WebMarkupContainer(id) {
			private static final long serialVersionUID = 1L;
			@Override
			public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				grafiekData.calculate();
				Response response = getRequestCycle().getResponse();
				StringBuilder builder = new StringBuilder();
				builder.append("<script>");
				builder.append("var barChartData = {");
				builder.append(getChartLabels());
				builder.append(",");
				builder.append("datasets : [ ");
				if (grafiekData.hasValuesPositief()) {
					builder.append(getValuesPositief());
					builder.append(",");	
				}
				if (grafiekData.hasValuesNegatief()) {
					builder.append(getValuesNegatief());	
				}
				builder.append("] };");
				builder.append("var options = {  };");
				builder.append("var myLine = new Chart(document.getElementById(\"canvas\").getContext(\"2d\")).Bar(barChartData, options);");
				builder.append("</script>");
				response.write(builder.toString());
			}
		};
	}
	
	private String getChartLabels() {
		StringBuilder labels = new StringBuilder("labels : [ ");	
		for (String key : grafiekData.getNegatiefTotaal().keySet()) {
			labels.append("\"").append(key).append("\",");
		}
		return labels.substring(0, labels.length() - 1) + "]";
	}
	
	private String getValuesPositief() {		
		StringBuilder labels = new StringBuilder("{ ");
		labels.append("fillColor : \"rgba(34,139,34,0.5)\",");
		labels.append("strokeColor : \"rgba(34,139,34,1)\",");
		labels.append("data : [");
		Map<String, Money> data = grafiekData.getPositiefTotaal();
		for (String key : data.keySet()) {
			Money value = data.get(key);
			labels.append(value.integerValue()).append(",");
		}
		return labels.substring(0, labels.length() - 1) + "] }";
	}
	
	private String getValuesNegatief() {		
		StringBuilder labels = new StringBuilder("{ ");
		labels.append("fillColor : \"rgba(255,0,0,0.5)\",");
		labels.append("strokeColor : \"rgba(255,0,0,1)\",");
		labels.append("data : [");
		Map<String, Money> data = grafiekData.getNegatiefTotaal();
		for (String key : data.keySet()) {
			Money value = data.get(key);
			labels.append(value.integerValue()).append(",");
		}
		return labels.substring(0, labels.length() - 1) + "] }";
	}
	
	@Override
	protected void onBeforeRender() {
		grafiekData.calculate();
		Map<String, Money> data = grafiekData.getNegatiefTotaal();
		for (String maand : data.keySet()) {
			System.out.println(maand + " - " + data.get(maand).doubleValue());
		}
		super.onBeforeRender();
	}

}
