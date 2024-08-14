package pasa.cbentley.framework.core.ui.j2se.engine;

import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.stator.IStatorable;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.ObjectCUC;

/**
 * Describes a set of frames and their positions
 * 
 * @author Charles Bentley
 *
 */
public class PerspectiveJ2se extends ObjectCUC implements IStatorable {

   private String        name;

   private CoreUiJ2seCtx cuc;

   public PerspectiveJ2se(CoreUiJ2seCtx cuc, String name) {
      super(cuc);
      this.cuc = cuc;
      this.name = name;

   }
   public ICtx getCtxOwner() {
      return cuc;
   }
   public int getStatorableClassID() {
      throw new RuntimeException("Must be implemented by subclass");
   }

   public void stateWriteTo(StatorWriter state) {
      // TODO Auto-generated method stub

   }

   public void stateReadFrom(StatorReader state) {
      // TODO Auto-generated method stub

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, PerspectiveJ2se.class, 50);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, PerspectiveJ2se.class, 50);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
      
   }
   //#enddebug
   


}
