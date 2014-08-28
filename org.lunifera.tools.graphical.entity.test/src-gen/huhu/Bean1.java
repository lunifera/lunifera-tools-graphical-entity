package huhu;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@SuppressWarnings("all")
public class Bean1 implements Serializable {
  private boolean disposed;
  
  @Basic
  private String pid;
  
  @Basic
  private Date pdate;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  @Transient
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
   * Returns the pid property or <code>null</code> if not present.
   */
  public String getPid() {
    checkDisposed();
    return this.pid;
  }
  
  /**
   * Sets the pid property to this instance.
   */
  public void setPid(final String pid) {
    checkDisposed();
    this.pid = pid;
  }
  
  /**
   * Returns the pdate property or <code>null</code> if not present.
   */
  public Date getPdate() {
    checkDisposed();
    return this.pdate;
  }
  
  /**
   * Sets the pdate property to this instance.
   */
  public void setPdate(final Date pdate) {
    checkDisposed();
    this.pdate = pdate;
  }
}
