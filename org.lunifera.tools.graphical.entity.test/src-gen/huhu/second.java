package huhu;

import huhu.sdfsdf;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SECOND")
@DiscriminatorValue(value = "SECOND")
@SuppressWarnings("all")
public class second {
  @Transient
  private boolean disposed;
  
  @Id
  @GeneratedValue
  private Long pid;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MYREFERENCE")
  private sdfsdf myreference;
  
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
   * Returns the pid property or <code>null</code> if not present.
   */
  public Long getPid() {
    checkDisposed();
    return this.pid;
  }
  
  /**
   * Sets the pid property to this instance.
   */
  public void setPid(final Long pid) {
    checkDisposed();
    this.pid = pid;
  }
  
  /**
   * Returns the myreference property or <code>null</code> if not present.
   */
  public sdfsdf getMyreference() {
    checkDisposed();
    return this.myreference;
  }
  
  /**
   * Sets the myreference property to this instance.
   */
  public void setMyreference(final sdfsdf myreference) {
    checkDisposed();
    this.myreference = myreference;
  }
}
