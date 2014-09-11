package org.my.example.mapper;

/**
 * This class maps the dto {@link MyEntityDto} to and from the entity {@link MyEntity}.
 * 
 */
@SuppressWarnings("all")
public class MyEntityDtoMapper implements org.my.example.mapper.MyParentDtoMapper {
  /**
   * Maps the entity {@link MyEntity} to the dto {@link MyEntityDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final org.my.example.MyEntityDto dto, final org.my.example.MyEntity entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setValue(toDto_value(entity));
    dto.setChilds(toDto_childs(entity));
  }
  
  /**
   * Maps the dto {@link MyEntityDto} to the entity {@link MyEntity}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final org.my.example.MyEntityDto dto, final org.my.example.MyEntity entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setValue(toEntity_value(dto));
    
    entity.setChilds(toEntity_childs(dto));
    
  }
  
  /**
   * Maps the property value from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_value(final org.my.example.MyEntity in) {
    return in.getValue();
  }
  
  /**
   * Maps the property value from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_value(final org.my.example.MyEntityDto in) {
    return in.getValue();
  }
  
  /**
   * Maps the property childs from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected org.my.example.MyChildDto toDto_childs(final org.my.example.MyEntity in) {
    org.lunifera.dsl.dto.lib.IMapper<MyChildDto, MyChild> mapper = getMapper(MyChildDto.class, MyChild.class);
    if(mapper != null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    MyChildDto dto = new MyChildDto();
    mapper.mapToDTO(dto, in.getChilds());	
    
    return dto;
  }
  
  /**
   * Maps the property childs from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected org.my.example.MyChild toEntity_childs(final org.my.example.MyEntityDto in) {
    org.lunifera.dsl.dto.lib.IMapper<MyChildDto, MyChild> mapper = getMapper(MyChildDto.class, MyChild.class);
    if(mapper != null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    MyChild entity = new MyChild();
    mapper.mapToEntity(in.getChilds(), entity);	
    
    return entity;
  }
}
