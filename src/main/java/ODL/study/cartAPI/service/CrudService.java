package ODL.study.cartAPI.service;

import javax.persistence.EntityNotFoundException;

/**
 * Create, Read, Update, Delete.
 * 
 * @author <a href="https://github.com/nifar/"> Nifar </a>
 * 
 */
public interface CrudService<Entity, DTO> {

    DTO create(Entity entity);

    DTO read(long id) throws EntityNotFoundException;

    DTO update(Entity entity);

    void delete(long id);
}