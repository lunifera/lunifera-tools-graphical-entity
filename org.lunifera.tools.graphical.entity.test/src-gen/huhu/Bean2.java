package huhu;

import huhu.Bean1;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("all")
public class Bean2 extends Bean1 {
  @Basic
  private Date pdate;
  
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
