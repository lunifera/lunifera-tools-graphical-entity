package huhu;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("all")
public class Bean1Dto implements Serializable {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  private boolean disposed;
  
  private String pid;
  
  private Date pdate;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  public boolean isDisposed() {
    return this.disposed;
  }
  
  /**
   * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
   */
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }
  
  /**
   * @see PropertyChangeSupport#addPropertyChangeListener(String, PropertyChangeListener)
   */
  public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }
  
  /**
   * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
   */
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
  
  /**
   * @see PropertyChangeSupport#removePropertyChangeListener(String, PropertyChangeListener)
   */
  public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
  
  /**
   * @see PropertyChangeSupport#firePropertyChange(String, Object, Object)
   */
  public void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
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
   * Sets the <code>pid</code> property to this instance.
   * 
   * @param pid - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setPid(final String pid) {
    firePropertyChange("pid", this.pid, this.pid = pid );
  }
  
  /**
   * Returns the pdate property or <code>null</code> if not present.
   */
  public Date getPdate() {
    checkDisposed();
    return this.pdate;
  }
  
  /**
   * Sets the <code>pdate</code> property to this instance.
   * 
   * @param pdate - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setPdate(final Date pdate) {
    firePropertyChange("pdate", this.pdate, this.pdate = pdate );
  }
}
