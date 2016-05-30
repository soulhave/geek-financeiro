package br.com.geek.financeiro.app.dao.factory;

import br.com.geek.financeiro.app.dao.BancoDAO;
import br.com.geek.financeiro.app.dao.impl.BancoDAOImpl;
import br.com.geek.financeiro.componente.dao.impl.BaseDAOImpl;

public class AppDaoFactory {
	@SuppressWarnings("rawtypes")
	public static BaseDAOImpl create(Class clazz) {
		/*BancoDAO*/
		if(BancoDAO.class.equals(clazz)){
			return new BancoDAOImpl();
		}
		return null;
	}
}