package huhu;

import huhu.Bean1;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

@Embeddable
@SuppressWarnings("all")
public class Bean3 implements Serializable {
  private boolean disposed;
  
  @Basic
  private String firstname;
  
  @Basic
  @Embedded
  private Bean1 myreference;
  
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
   * Returns the firstname property or <code>null</code> if not present.
   */
  public String getFirstname() {
    checkDisposed();
    return this.firstname;
  }
  
  /**
   * Sets the firstname property to this instance.
   */
  public void setFirstname(final String firstname) {
    checkDisposed();
    this.firstname = firstname;
  }
  
  /**
   * Returns the myreference property or <code>null</code> if not present.
   */
  public Bean1 getMyreference() {
    checkDisposed();
    return this.myreference;
  }
  
  /**
   * Sets the myreference property to this instance.
   */
  public void setMyreference(final Bean1 myreference) {
    checkDisposed();
    this.myreference = myreference;
  }
}
