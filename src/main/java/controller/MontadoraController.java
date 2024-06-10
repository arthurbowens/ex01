package controller;

import exception.CarrosException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Montadora;
import service.MontadoraService;

@Path("/montadora")
public class MontadoraController {

	private MontadoraService service = new MontadoraService();

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Montadora salvar(Montadora novaMontadora) throws CarrosException {
		return service.salvar(novaMontadora);
	}


	@GET
	@Path("/{id}")
	public Montadora consultarPorId(@PathParam("id") int id){
		 return service.consultarPorId(id);
	}
}
