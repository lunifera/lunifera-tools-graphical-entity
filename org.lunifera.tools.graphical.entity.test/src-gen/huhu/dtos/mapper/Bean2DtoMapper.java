package huhu.dtos.mapper;

import huhu.Bean2;
import huhu.dtos.Bean2Dto;
import huhu.dtos.mapper.Bean1DtoMapper;
import java.util.Date;

/**
 * This class maps the dto {@link Bean2Dto} to and from the entity {@link Bean2}.
 * 
 */
@SuppressWarnings("all")
public class Bean2DtoMapper extends Bean1DtoMapper {
  /**
   * Maps the entity {@link Bean2} to the dto {@link Bean2Dto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final Bean2Dto dto, final Bean2 entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setPdate(toDto_pdate(entity));
  }
  
  /**
   * Maps the dto {@link Bean2Dto} to the entity {@link Bean2}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final Bean2Dto dto, final Bean2 entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setPdate(toEntity_pdate(dto));
    
  }
  
  /**
   * Maps the property pdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toDto_pdate(final Bean2 in) {
    return in.getPdate();
  }
  
  /**
   * Maps the property pdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toEntity_pdate(final Bean2Dto in) {
    return in.getPdate();
  }
}
