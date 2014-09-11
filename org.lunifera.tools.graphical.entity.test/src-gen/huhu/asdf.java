package huhu;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ASDF")
@DiscriminatorValue(value = "ASDF")
@SuppressWarnings("all")
public class asdf {
  @Transient
  private boolean disposed;
  
  @Transient
  private Date bdate;
  
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
   * Returns the bdate property or <code>null</code> if not present.
   */
  public Date getBdate() {
    checkDisposed();
    return this.bdate;
  }
  
  /**
   * Sets the bdate property to this instance.
   */
  public void setBdate(final Date bdate) {
    checkDisposed();
    this.bdate = bdate;
  }
}
