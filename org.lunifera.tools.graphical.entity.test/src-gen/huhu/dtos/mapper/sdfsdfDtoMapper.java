package huhu.dtos.mapper;

import huhu.dtos.sdfsdfDto;
import huhu.sdfsdf;
import java.util.Date;
import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;

/**
 * This class maps the dto {@link sdfsdfDto} to and from the entity {@link sdfsdf}.
 * 
 */
@SuppressWarnings("all")
public class sdfsdfDtoMapper implements IMapper<sdfsdfDto, sdfsdf> {
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
   * Maps the entity {@link sdfsdf} to the dto {@link sdfsdfDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final sdfsdfDto dto, final sdfsdf entity) {
    
    dto.setPid(toDto_pid(entity));
    dto.setPdate(toDto_pdate(entity));
  }
  
  /**
   * Maps the dto {@link sdfsdfDto} to the entity {@link sdfsdf}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final sdfsdfDto dto, final sdfsdf entity) {
    
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
  protected String toDto_pid(final sdfsdf in) {
    return in.getPid();
  }
  
  /**
   * Maps the property pid from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_pid(final sdfsdfDto in) {
    return in.getPid();
  }
  
  /**
   * Maps the property pdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toDto_pdate(final sdfsdf in) {
    return in.getPdate();
  }
  
  /**
   * Maps the property pdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toEntity_pdate(final sdfsdfDto in) {
    return in.getPdate();
  }
}