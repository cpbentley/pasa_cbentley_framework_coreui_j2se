package pasa.cbentley.framework.coreui.j2se.interfaces;

import pasa.cbentley.framework.coreui.j2se.engine.CanvasHostJ2SE;

public interface IJ2SECanvasWrapper {
   /**
    * null if the wrapper was not set a Canvas yet.
    * <br>
    * @return
    */
   public CanvasHostJ2SE getCanvas();
}
