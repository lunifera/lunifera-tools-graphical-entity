package huhu.mapper;

import huhu.Bean1Dto;
import java.util.Date;

/**
 * This class maps the dto {@link Bean1Dto} to and from the entity {@link Bean1}.
 * 
 */
@SuppressWarnings("all")
public class Bean1DtoMapper implements org.lunifera.dsl.dto.lib.IMapper {
  private org.lunifera.dsl.dto.lib.IMapperAccess mapperAccess;
  
  /**
   * Returns the mapper instance that may map between the given dto and entity. Or <code>null</code> if no mapper is available.
   * 
   * @param dtoClass - the class of the dto that should be mapped
   * @param entityClass - the class of the entity that should be mapped
   * @return the mapper instance or <code>null</code>
   */
  protected <D, E> org.lunifera.dsl.dto.lib.IMapper getMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getMapper(dtoClass, entityClass);
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void bindMapperAccess(final org.lunifera.dsl.dto.lib.IMapperAccess mapperAccess) {
    this.mapperAccess = mapperAccess;
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void unbindMapperAccess(final org.lunifera.dsl.dto.lib.IMapperAccess mapperAccess) {
    this.mapperAccess = null;
  }
  
  /**
   * Maps the entity {@link Bean1} to the dto {@link Bean1Dto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final Bean1Dto dto, final huhu.Bean1 entity) {
    
    dto.setPid(toDto_pid(entity));
    dto.setPdate(toDto_pdate(entity));
  }
  
  /**
   * Maps the dto {@link Bean1Dto} to the entity {@link Bean1}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final Bean1Dto dto, final huhu.Bean1 entity) {
    
    entity.setPid(toEntity_pid(dto));
    
    entity.setPdate(toEntity_pdate(dto));
    
  }
  
  /**
   * Maps the property pid from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_pid(final huhu.Bean1 in) {
    return in.getPid();
  }
  
  /**
   * Maps the property pid from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_pid(final Bean1Dto in) {
    return in.getPid();
  }
  
  /**
   * Maps the property pdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toDto_pdate(final huhu.Bean1 in) {
    return in.getPdate();
  }
  
  /**
   * Maps the property pdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toEntity_pdate(final Bean1Dto in) {
    return in.getPdate();
  }
}
