package nl.cge.tran.web.wicket;


import nl.cge.tran.web.ui.homepage.Transaktiepage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see nl.cge.tran.Start#main(String[])
 */
public class WicketApplication extends WebApplication {    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return Transaktiepage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
	}
	
	
	
	
}
