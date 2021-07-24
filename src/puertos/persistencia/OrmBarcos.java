package puertos.persistencia;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import puertos.entidades.Barco;

/**
 * Usa una base de datos como repositorio de los datos de los barcos,
 * y ofrece los servicios definidos en RepositorioBarcos.
 * 
 * A diferencia de "BaseDatosBarcos", usa JPA y EclipseLink
 * para el trabajo con la base de datos (es decir, un ORM). 
 * La base de datos es "barcosOrm.db" (motor SQLite),
 * y es creada por el Orm con una tabla: Barco.
 * 
 * Usa una sola instancia del EntityManager para todos los métodos,
 * y solo se cierra cuando se destruye el objeto
 * 
 * @version 1.0
 */
public class OrmBarcos implements RepositorioBarcos {
	
	private EntityManager gestorBd;
	
	public OrmBarcos() {
		// Se hace referencia a la unidad de persistencia definida en persistence.xml
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpaBarcos");
		gestorBd = fabrica.createEntityManager();
	}

	@Override
	public boolean adicionarBarco(Barco barco) {
		try	{
			gestorBd.getTransaction().begin();
			gestorBd.persist(barco);
			gestorBd.getTransaction().commit();
		}
		catch (Exception errorCrear)	{
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Barco> consultarBarcos() {
		Query query = gestorBd.createQuery("select b from Barco b");
		List<Barco> barcos = query.getResultList();
		return barcos;
	}

	@Override
	public Barco buscarBarco(String matricula) {
		Barco barco = gestorBd.find(Barco.class, matricula);
		return barco;
	}
	
	/**
	 * Cierra el EntitiyManager cuando se vaya a destruir este objeto
	 */
	@Override
	protected void finalize() {
		gestorBd.close();
	}
}
