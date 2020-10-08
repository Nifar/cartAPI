package ODL.study.cartAPI.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ODL.study.cartAPI.entity.AbstractEntity;
import ODL.study.cartAPI.service.mapper.EntityToDTOMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@Lazy
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrudImpl<Entity extends AbstractEntity, Repository extends JpaRepository<Entity, Long>, DTO, DTOMapper extends EntityToDTOMapper<DTO, Entity, DTO>>
        implements CrudService<Entity, DTO> {

    @Autowired
    Repository repository;

    @Autowired
    DTOMapper mapper;

    @Override
    public DTO create(Entity entity) {
        return mapper.toDTO(repository.saveAndFlush(entity));
    }

    @Override
    public DTO read(long id) throws EntityNotFoundException {
        Entity entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return mapper.toDTO(entity);
    }

    @Override
    public DTO update(Entity entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

}
