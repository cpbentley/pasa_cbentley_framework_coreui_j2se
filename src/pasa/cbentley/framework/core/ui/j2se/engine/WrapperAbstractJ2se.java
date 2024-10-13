package pasa.cbentley.framework.core.ui.j2se.engine;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.src4.wrapper.WrapperAbstract;

/**
 * Wrapper of a J2SECanvas
 * @author Charles Bentley
 *
 */
public abstract class WrapperAbstractJ2se extends WrapperAbstract {
   /**
    * Could be null if no parent wrapper.
    * <br>
    * A Parent wrapper can typically control several children.
    */
   protected WrapperAbstractJ2se parent;

   /**
    * 
    * @param cac
    */
   protected WrapperAbstractJ2se(CoreUiJ2seCtx cac) {
      super(cac);
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

   /**
    * 
    * @param parent
    */
   public void setParentWrapper(WrapperAbstractJ2se parent) {
      this.parent = parent;

   }

   public void stateReadFrom(StatorReader state) {
      super.stateReadFrom(state);
      //in j2se we have a framepos
   }

   public void stateWriteTo(StatorWriter state) {
      super.stateWriteTo(state);
   }

   public void stateWriteToParamSub(StatorWriter state) {
      super.stateWriteToParamSub(state);

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, WrapperAbstractJ2se.class, 55);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, WrapperAbstractJ2se.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
