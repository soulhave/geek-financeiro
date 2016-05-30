package br.com.geek.financeiro.app.config.ofy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.googlecode.objectify.ObjectifyService;

import br.com.geek.financeiro.app.entity.BancoEntity;

public class OfyContextListener implements ServletContextListener {
	
	/** Define all entities first. */
	static {
		ObjectifyService.register(BancoEntity.class);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}
}