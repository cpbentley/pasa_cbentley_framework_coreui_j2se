package pasa.cbentley.framework.coreui.j2se.engine;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.coreui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.coreui.src4.engine.GraphicsAbstract;

public abstract class GraphicsJ2SE extends GraphicsAbstract {

   protected GraphicsJ2SE(CoreUiJ2seCtx cac) {
      super(cac);
   }
   //#mdebug

 

   @Override
   public void toString(Dctx dc) {
      dc.root(this, "GraphicsJ2SE");
      super.toString(dc.sup());
   }

   @Override
   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "GraphicsJ2SE");
   }
   //#enddebug
}
