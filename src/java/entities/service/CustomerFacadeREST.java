package entities.service;

import entities.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("entities.customer")
public class CustomerFacadeREST extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "CustomerDBPU")
    private EntityManager em;

    public CustomerFacadeREST() {
        super(Customer.class);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public CustomerAdapter[] multiEdit(CustomerAdapter[] entityAdapters) {
        for (CustomerAdapter ea : entityAdapters) {
            if ("modified".equals(ea.updateStatus)) {
                Customer[] entity = {new Customer(), new Customer()};
                ea.initNewDataTo(entity[0], em);
                ea.initOldDataTo(entity[1], em);
//                CustomerAdapter customerAdapter = new CustomerAdapter();
                String error = super.edit(entity, entity[0].getCustomerId());
                if (error == null) {
                    ea.initFrom(entity[0]);
                } else {
                    ea.updateError = error;
                }
            }
        }
        return entityAdapters;
    }

    @PUT
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Customer[] entity) {
        super.edit(entity, entity[0].getCustomerId());
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Customer find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Customer> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Customer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
