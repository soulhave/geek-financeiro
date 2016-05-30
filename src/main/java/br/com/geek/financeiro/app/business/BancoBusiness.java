package br.com.geek.financeiro.app.business;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.geek.financeiro.app.dao.BancoDAO;
import br.com.geek.financeiro.app.dao.factory.AppDaoFactory;
import br.com.geek.financeiro.app.dao.impl.BancoDAOImpl;
import br.com.geek.financeiro.app.entity.BancoEntity;

/**
 * Classe de negócio, gerenciamento de cadastro de bancos.
 * @author ramon
 *
 */
public class BancoBusiness {

	/*Injeção dao*/
	private BancoDAO dao = (BancoDAOImpl) AppDaoFactory.create(BancoDAO.class);	
	
	/*Corpo da classe. Cuidado com encapsulamento*/
	
	/**
	 * Busca os bancos na base de dados e retorna.
	 * Através de dados informados por uma chave.
	 * @param chave
	 * @return
	 */
	public BancoEntity buscarItens(String chave){
		if (StringUtils.isNotEmpty(chave)) 
			return dao.findById(chave);;
		return null;
	}
	
	/**
	 * Busca os dados do banco de dados sem filtro.
	 * @return
	 */
	public List<BancoEntity> buscarTodos(){
		return dao.findAll();
	}
	
	/**
	 * Incluir novo banco na lista
	 * @param bancoEntity
	 * @return
	 */
	public BancoEntity incluirNovoItem(BancoEntity bancoEntity) {
		if(bancoEntity != null){
			dao.add(bancoEntity);
		}
		return bancoEntity;
	}
	
	/**
	 * Atualiza as informações do banco na lista
	 * @param chave
	 * @param bancoEntity
	 * @return
	 */
	public BancoEntity atualizarNovoItem(String chave, BancoEntity bancoEntity) {
		BancoEntity entity = null;
		//if chave vazia inclui novo item
		if (StringUtils.isEmpty(chave)) return incluirNovoItem(bancoEntity);;
		entity = dao.findById(chave);

		//if entity is null inclui novo item
		if(entity == null) return incluirNovoItem(bancoEntity);
		
		//else change other fields
		if(StringUtils.isEmpty(bancoEntity.getNome())) bancoEntity.setNome(entity.getNome());
		if(StringUtils.isEmpty(bancoEntity.getCor())) bancoEntity.setCor(entity.getCor());
		if(bancoEntity.getSaldo() == null) bancoEntity.setSaldo(entity.getSaldo());				
		
		//update in database
		dao.add(bancoEntity);
		
		return bancoEntity;
	}

	/**
	 * Excluir um banco
	 * @param chave
	 * @return
	 */
	public boolean excluirItem(String chave) {
		if (StringUtils.isNotEmpty(chave)){
			return dao.delete(chave);
		}		
		return Boolean.FALSE;
	}

	/**
	 * Excluir todos os bancos
	 * @param chave
	 * @return
	 */
	public boolean excluirTudo() {
		return dao.deleteAll();
	}
	
	
	/*Singletone*/
	private static BancoBusiness INSTANCE;
	private BancoBusiness() {}
	
	public static BancoBusiness getInstance(){
		if(INSTANCE == null)
			INSTANCE = new BancoBusiness();
		return INSTANCE;
	}
	/**/

}
