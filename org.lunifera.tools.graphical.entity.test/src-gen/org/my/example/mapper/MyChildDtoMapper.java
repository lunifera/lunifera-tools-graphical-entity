package org.my.example.mapper;

/**
 * This class maps the dto {@link MyChildDto} to and from the entity {@link MyChild}.
 * 
 */
@SuppressWarnings("all")
public class MyChildDtoMapper implements org.my.example.mapper.MyParentDtoMapper {
  /**
   * Maps the entity {@link MyChild} to the dto {@link MyChildDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final org.my.example.MyChildDto dto, final org.my.example.MyChild entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setName(toDto_name(entity));
  }
  
  /**
   * Maps the dto {@link MyChildDto} to the entity {@link MyChild}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final org.my.example.MyChildDto dto, final org.my.example.MyChild entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setName(toEntity_name(dto));
    
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_name(final org.my.example.MyChild in) {
    return in.getName();
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_name(final org.my.example.MyChildDto in) {
    return in.getName();
  }
}
