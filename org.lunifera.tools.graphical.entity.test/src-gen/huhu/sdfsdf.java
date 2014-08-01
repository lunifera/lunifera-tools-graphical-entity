package huhu;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SDFSDF")
@DiscriminatorValue(value = "SDFSDF")
@SuppressWarnings("all")
public class sdfsdf {
  @Transient
  private boolean disposed;
  
  @Id
  @GeneratedValue
  private String id;
  
  @Column(name = "DATE")
  private Date date;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  public boolean isDisposed() {
    return this.disposed;
  }
  
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
    disposed = true;
  }
  
  /**
   * Returns the id property or <code>null</code> if not present.
   */
  public String getId() {
    checkDisposed();
    return this.id;
  }
  
  /**
   * Sets the id property to this instance.
   */
  public void setId(final String id) {
    checkDisposed();
    this.id = id;
  }
  
  /**
   * Returns the date property or <code>null</code> if not present.
   */
  public Date getDate() {
    checkDisposed();
    return this.date;
  }
  
  /**
   * Sets the date property to this instance.
   */
  public void setDate(final Date date) {
    checkDisposed();
    this.date = date;
  }
}
