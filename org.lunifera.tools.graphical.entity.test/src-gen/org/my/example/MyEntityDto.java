package org.my.example;

import java.io.Serializable;
import org.my.example.MyChildDto;
import org.my.example.MyParentDto;

@SuppressWarnings("all")
public class MyEntityDto extends MyParentDto implements Serializable {
  private String value;
  
  private MyChildDto childs;
  
  /**
   * Checks whether the object is disposed.
   * @throws RuntimeException if the object is disposed.
   */
  private void checkDisposed() {
    if (isDisposed()) {
      throw new RuntimeException("Object already disposed: " + this);
    }
  }
  
  /**
   * Calling dispose will destroy that instance. The internal state will be 
   * set to 'disposed' and methods of that object must not be used anymore. 
   * Each call will result in runtime exceptions.<br/>
   * If this object keeps composition containments, these will be disposed too. 
   * So the whole composition containment tree will be disposed on calling this method.
   */
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    super.dispose();
  }
  
  /**
   * Returns the value property or <code>null</code> if not present.
   */
  public String getValue() {
    checkDisposed();
    return this.value;
  }
  
  /**
   * Sets the <code>value</code> property to this instance.
   * 
   * @param value - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setValue(final String value) {
    firePropertyChange("value", this.value, this.value = value );
  }
  
  /**
   * Returns the childs property or <code>null</code> if not present.
   */
  public MyChildDto getChilds() {
    checkDisposed();
    return this.childs;
  }
  
  /**
   * Sets the <code>childs</code> property to this instance.
   * 
   * @param childs - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setChilds(final MyChildDto childs) {
    checkDisposed();
    firePropertyChange("childs", this.childs, this.childs = childs);
  }
}
