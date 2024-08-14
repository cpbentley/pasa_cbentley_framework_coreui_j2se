package pasa.cbentley.framework.core.ui.j2se.engine;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.src4.engine.WrapperAbstract;
import pasa.cbentley.framework.core.ui.src4.tech.ITechFeaturesCanvas;
import pasa.cbentley.framework.core.ui.src4.tech.ITechHostUI;

/**
 * Wrapper of a J2SECanvas
 * @author Charles Bentley
 *
 */
public abstract class WrapperAbstractJ2se extends WrapperAbstract {
   /**
    * 
    * @param cac
    */
   protected WrapperAbstractJ2se(CoreUiJ2seCtx cac) {
      super(cac);
   }

   /**
    * Could be null if no parent wrapper.
    * <br>
    * A Parent wrapper can typically control several children.
    */
   protected WrapperAbstractJ2se parent;

   
   /**
    * 
    * @param parent
    */
   public void setParentWrapper(WrapperAbstractJ2se parent) {
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
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, WrapperAbstractJ2se.class, 55);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, WrapperAbstractJ2se.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   


}
