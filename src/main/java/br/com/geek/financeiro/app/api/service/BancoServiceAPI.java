package br.com.geek.financeiro.app.api.service;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import br.com.geek.financeiro.app.business.BancoBusiness;
import br.com.geek.financeiro.app.entity.BancoEntity;

/**
 * Api de Cadastro de Bancos
 * @author Ramon Mendes
 *
 */
@Api(name = "bancoapi", 
	version = "v1",
	description = "Api de informações de Banco",
	namespace = @ApiNamespace(ownerDomain = "br.com.geek.financeiro.bancos",ownerName = "br.com.geek.financeiro.bancos", packagePath =""))
public class BancoServiceAPI {

	//Injeção de business
	private BancoBusiness business = BancoBusiness.getInstance();
	
	/**
	 * Obtém os dados de bancos sem parametro
	 * @param chave
	 * @return
	 */
	@ApiMethod(name = "bancos.BuscarTodos",
			httpMethod = ApiMethod.HttpMethod.GET,
			path = "bancos")
	public List<BancoEntity> getItens() {		
		return business.buscarTodos();
	}
	
	/**
	 * Incluir um novo item
	 * @param chave
	 * @return
	 */
	@ApiMethod(name = "bancos.Incluir",
			httpMethod = ApiMethod.HttpMethod.POST,
			path = "bancos")
	public BancoEntity inserirIten(BancoEntity bancoEntity) {		
		return business.incluirNovoItem(bancoEntity);
	}
	
	/**
	 * Apaga todos os dados
	 * @param chave
	 * @return
	 */
	@ApiMethod(name = "bancos.ExcluirTodos",
			httpMethod = ApiMethod.HttpMethod.DELETE,
			path = "bancos")
	public Response excluirTodos() {		
		return new Response(business.excluirTudo());
	}
	
	/**
	 * Obtém os dados de bancos por parametro
	 * @param chave
	 * @return
	 */
	@ApiMethod(name = "bancos.BuscarItem",
			httpMethod = ApiMethod.HttpMethod.GET,
			path = "bancos/{chave}")
	public BancoEntity getItem(@Named("chave") final String chave) {		
		return business.buscarItens(chave);
	}
	
	/**
	 * Atualiza o item
	 * @param chave
	 * @return
	 */
	@ApiMethod(name = "bancos.Atualizar",
			httpMethod = ApiMethod.HttpMethod.PUT,
			path = "bancos/{chave}")
	public BancoEntity atualizarIten(@Named("chave") final String chave, BancoEntity bancoEntity) {		
		return business.atualizarNovoItem(chave,bancoEntity);
	}
	
	/**
	 * Obtém os dados de bancos por parametro
	 * @param chave
	 * @return
	 */
	@ApiMethod(name = "bancos.ExcluirItem",
			httpMethod = ApiMethod.HttpMethod.DELETE,
			path = "bancos/{chave}")
	public Response excluirItem(@Named("chave") final String chave) {		
		return new Response(business.excluirItem(chave));
	}	

	/**
	 * Retorna objeto do tipo comun quando necessário.
	 * @author ramon
	 *
	 */
	@SuppressWarnings("unused")
	private class Response{
		
		private String status;
		
		public Response(String status) {
			super();
			this.status = status;
		}
		
		public Response(boolean status) {
			super();
			if(status){
				this.status = "OK";
			}else{
				this.status = "NOK";
			}
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
	}
}