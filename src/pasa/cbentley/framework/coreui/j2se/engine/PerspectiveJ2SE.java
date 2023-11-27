package pasa.cbentley.framework.coreui.j2se.engine;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.stator.IStatorable;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.framework.coreui.j2se.ctx.CoreUiJ2seCtx;

/**
 * Describes a set of frames and their positions
 * 
 * @author Charles Bentley
 *
 */
public class PerspectiveJ2SE implements IStatorable {

   private String        name;

   private CoreUiJ2seCtx cuc;

   public PerspectiveJ2SE(CoreUiJ2seCtx cuc, String name) {
      this.cuc = cuc;
      this.name = name;

   }

   public void stateWriteTo(StatorWriter state) {
      // TODO Auto-generated method stub

   }

   public void stateReadFrom(StatorReader state) {
      // TODO Auto-generated method stub

   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PerspectiveJ2SE");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PerspectiveJ2SE");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return cuc.getUCtx();
   }

   //#enddebug

}
