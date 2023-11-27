package pasa.cbentley.framework.coreui.j2se.engine;

import pasa.cbentley.framework.coreui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.coreui.src4.engine.WrapperAbstract;
import pasa.cbentley.framework.coreui.src4.tech.ITechFeaturesUI;

/**
 * Wrapper of a J2SECanvas
 * @author Charles Bentley
 *
 */
public abstract class WrapperAbstractJ2SE extends WrapperAbstract implements ITechFeaturesUI {
   /**
    * 
    * @param cac
    */
   protected WrapperAbstractJ2SE(CoreUiJ2seCtx cac) {
      super(cac);
   }

   /**
    * Could be null if no parent wrapper.
    * <br>
    * A Parent wrapper can typically control several children.
    */
   protected WrapperAbstractJ2SE parent;

   /**
    * 
    * @param parent
    */
   public void setParentWrapper(WrapperAbstractJ2SE parent) {
      this.parent = parent;

   }

   /**
    * Retursn X coordinate on the screen?
    * @return
    */
   public abstract int getX();

   /**
    * 
    * @return
    */
   public abstract int getY();

}
