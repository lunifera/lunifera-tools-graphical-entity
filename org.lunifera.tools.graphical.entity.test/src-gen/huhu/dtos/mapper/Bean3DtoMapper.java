package huhu.dtos.mapper;

import huhu.Bean3;
import huhu.dtos.Bean3Dto;
import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;

/**
 * This class maps the dto {@link Bean3Dto} to and from the entity {@link Bean3}.
 * 
 */
@SuppressWarnings("all")
public class Bean3DtoMapper implements IMapper<Bean3Dto, Bean3> {
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
   * Maps the entity {@link Bean3} to the dto {@link Bean3Dto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final Bean3Dto dto, final Bean3 entity) {
    
    dto.setFirstname(toDto_firstname(entity));
  }
  
  /**
   * Maps the dto {@link Bean3Dto} to the entity {@link Bean3}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final Bean3Dto dto, final Bean3 entity) {
    
    entity.setFirstname(toEntity_firstname(dto));
    
  }
  
  /**
   * Maps the property firstname from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_firstname(final Bean3 in) {
    return in.getFirstname();
  }
  
  /**
   * Maps the property firstname from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_firstname(final Bean3Dto in) {
    return in.getFirstname();
  }
}
