package ODL.study.cartAPI.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityToDTOMapper<I, E, O> {

    O toDTO(E entity, Object... args);

    E toEntity(I dto, Object... args);

    default List<O> toDTOs(List<E> entities, Object... args) {
        return entities.stream().map(entity -> toDTO(entity, args)).collect(Collectors.toList());
    }

    default List<E> toEntities(List<I> dtos, Object... args) {
        return dtos.stream().map(dto -> toEntity(dto, args)).collect(Collectors.toList());
    }
}
