package huhu.mapper;

import huhu.sdfsdfDto;
import huhu.secondDto;

/**
 * This class maps the dto {@link secondDto} to and from the entity {@link second}.
 * 
 */
@SuppressWarnings("all")
public class secondDtoMapper implements org.lunifera.dsl.dto.lib.IMapper {
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
   * Maps the entity {@link second} to the dto {@link secondDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final secondDto dto, final huhu.second entity) {
    
    dto.setPid(toDto_pid(entity));
    dto.setMyreference(toDto_myreference(entity));
  }
  
  /**
   * Maps the dto {@link secondDto} to the entity {@link second}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final secondDto dto, final huhu.second entity) {
    
    entity.setPid(toEntity_pid(dto));
    
    entity.setMyreference(toEntity_myreference(dto));
    
  }
  
  /**
   * Maps the property pid from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Long toDto_pid(final huhu.second in) {
    return in.getPid();
  }
  
  /**
   * Maps the property pid from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Long toEntity_pid(final secondDto in) {
    return in.getPid();
  }
  
  /**
   * Maps the property myreference from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected sdfsdfDto toDto_myreference(final huhu.second in) {
    org.lunifera.dsl.dto.lib.IMapper<sdfsdfDto, sdfsdf> mapper = getMapper(sdfsdfDto.class, sdfsdf.class);
    if(mapper != null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    sdfsdfDto dto = new sdfsdfDto();
    mapper.mapToDTO(dto, in.getMyreference());	
    
    return dto;
  }
  
  /**
   * Maps the property myreference from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected huhu.sdfsdf toEntity_myreference(final secondDto in) {
    org.lunifera.dsl.dto.lib.IMapper<sdfsdfDto, sdfsdf> mapper = getMapper(sdfsdfDto.class, sdfsdf.class);
    if(mapper != null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    sdfsdf entity = new sdfsdf();
    mapper.mapToEntity(in.getMyreference(), entity);	
    
    return entity;
  }
}
