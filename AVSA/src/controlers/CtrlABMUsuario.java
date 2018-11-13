package controlers;
import java.util.ArrayList;

import data.DataPais;
import data.DataUsuario;
import entity.Pais;
import entity.Usuario;

public class CtrlABMUsuario {

	private DataUsuario dataUsu;
	private DataPais dataPais;
	
	
	public CtrlABMUsuario(){
		dataUsu = new DataUsuario();
		dataPais = new DataPais();
	}
	
	public void add(Usuario u) throws Exception{
		dataUsu.add(u);
	}
	
	public void delete(Usuario u)throws Exception{
		dataUsu.remove(u);
	}
	
	public void update(Usuario u)throws Exception{
		dataUsu.update(u);
	}
	
	public Usuario getUsuario(Usuario u) throws Exception{
		return this.dataUsu.getByUsuario(u);
	}
	
	public Usuario getById(Usuario u) throws Exception{
		return dataUsu.getById(u);
	}
	
	public Usuario getByUsuario(String usuario)throws Exception{
		Usuario u=new Usuario();
		u.setUsuario(usuario);
		return dataUsu.getByUsuario(u);
	}
	
	public Usuario getByNombreApellido(Usuario u) throws Exception{
		return dataUsu.getByNombreApellido(u);		
	}
	
	public ArrayList<Usuario> getAll()throws Exception{
		return dataUsu.getAll();
	}
	
	public String usuarioListText() throws Exception {
		String texto="Apellido\tNombre\tPais\n";
		ArrayList<Usuario> usuarios =this.getAll();
		for (Usuario u : usuarios){
			texto= texto +u.getApellido()+"\t"+u.getNombre()+"\t"+
					u.getPais().getNombre()+"\n";
		}
		return texto;
	}
	
	public ArrayList<Pais> getPaises() throws Exception{
		return dataPais.getAll();
	}
	
	public Usuario login(Usuario usu) throws Exception{
		return dataUsu.getLogedUser(usu);
	}
}