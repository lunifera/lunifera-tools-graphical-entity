package huhu.dtos.mapper;

import huhu.asdf;
import huhu.dtos.asdfDto;
import java.util.Date;
import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;

/**
 * This class maps the dto {@link asdfDto} to and from the entity {@link asdf}.
 * 
 */
@SuppressWarnings("all")
public class asdfDtoMapper implements IMapper<asdfDto, asdf> {
  private IMapperAccess mapperAccess;
  
  /**
   * Returns the mapper instance that may map between the given dto and entity. Or <code>null</code> if no mapper is available.
   * 
   * @param dtoClass - the class of the dto that should be mapped
   * @param entityClass - the class of the entity that should be mapped
   * @return the mapper instance or <code>null</code>
   */
  protected <D, E> IMapper<D, E> getMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getMapper(dtoClass, entityClass);
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void bindMapperAccess(final IMapperAccess mapperAccess) {
    this.mapperAccess = mapperAccess;
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void unbindMapperAccess(final IMapperAccess mapperAccess) {
    this.mapperAccess = null;
  }
  
  /**
   * Maps the entity {@link asdf} to the dto {@link asdfDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final asdfDto dto, final asdf entity) {
    
    dto.setBdate(toDto_bdate(entity));
  }
  
  /**
   * Maps the dto {@link asdfDto} to the entity {@link asdf}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final asdfDto dto, final asdf entity) {
    
    entity.setBdate(toEntity_bdate(dto));
    
  }
  
  /**
   * Maps the property bdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toDto_bdate(final asdf in) {
    return in.getBdate();
  }
  
  /**
   * Maps the property bdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toEntity_bdate(final asdfDto in) {
    return in.getBdate();
  }
}
