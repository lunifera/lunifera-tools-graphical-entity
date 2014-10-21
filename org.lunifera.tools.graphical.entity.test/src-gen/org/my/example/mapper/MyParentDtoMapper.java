package org.my.example.mapper;

import org.my.example.MyParentDto;

/**
 * This class maps the dto {@link MyParentDto} to and from the entity {@link MyParent}.
 * 
 */
@SuppressWarnings("all")
public class MyParentDtoMapper<DTO extends MyParentDto, ENTITY extends org.my.example.MyParent> implements org.lunifera.dsl.dto.lib.IMapper {
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
   * Maps the entity {@link MyParent} to the dto {@link MyParentDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final MyParentDto dto, final org.my.example.MyParent entity) {
    
    dto.setPid(toDto_pid(entity));
  }
  
  /**
   * Maps the dto {@link MyParentDto} to the entity {@link MyParent}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final MyParentDto dto, final org.my.example.MyParent entity) {
    
    entity.setPid(toEntity_pid(dto));
    
  }
  
  /**
   * Maps the property pid from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected long toDto_pid(final org.my.example.MyParent in) {
    return in.getPid();
  }
  
  /**
   * Maps the property pid from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected long toEntity_pid(final MyParentDto in) {
    return in.getPid();
  }
}
