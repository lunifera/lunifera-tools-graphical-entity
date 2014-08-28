package org.my.example;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.my.example.MyChild;
import org.my.example.MyParent;

@Entity
@Table(name = "MY_ENTITY")
@DiscriminatorValue(value = "MY_ENTITY")
@SuppressWarnings("all")
public class MyEntity extends MyParent {
  @Column(name = "VALUE")
  private String value;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CHILDS")
  private MyChild childs;
  
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
   * Sets the value property to this instance.
   */
  public void setValue(final String value) {
    checkDisposed();
    this.value = value;
  }
  
  /**
   * Returns the childs property or <code>null</code> if not present.
   */
  public MyChild getChilds() {
    checkDisposed();
    return this.childs;
  }
  
  /**
   * Sets the childs property to this instance.
   */
  public void setChilds(final MyChild childs) {
    checkDisposed();
    this.childs = childs;
  }
}
