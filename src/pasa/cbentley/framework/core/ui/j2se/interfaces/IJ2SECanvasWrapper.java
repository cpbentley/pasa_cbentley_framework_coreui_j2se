package pasa.cbentley.framework.core.ui.j2se.interfaces;

import pasa.cbentley.framework.core.ui.j2se.engine.CanvasHostJ2se;

public interface IJ2SECanvasWrapper {
   /**
    * null if the wrapper was not set a Canvas yet.
    * <br>
    * @return
    */
   public CanvasHostJ2se getCanvas();
}
