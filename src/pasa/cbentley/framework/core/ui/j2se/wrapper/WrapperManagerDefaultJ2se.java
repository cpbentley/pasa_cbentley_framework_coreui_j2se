package pasa.cbentley.framework.core.ui.j2se.wrapper;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.ui.src4.ctx.CoreUiCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.ObjectCUC;
import pasa.cbentley.framework.core.ui.src4.interfaces.IWrapperManager;
import pasa.cbentley.framework.core.ui.src4.wrapper.WrapperAbstract;
import pasa.cbentley.framework.core.ui.src4.wrapper.WrapperManagerAbstract;

public abstract class WrapperManagerDefaultJ2se extends WrapperManagerAbstract implements IWrapperManager {

   public WrapperManagerDefaultJ2se(CoreUiCtx cuc) {
      super(cuc);
   }

   public boolean setPosition(WrapperAbstract wrapper, int x, int y) {
      return false;
   }

   public boolean setSize(WrapperAbstract wrapper, int w, int h) {
      return false;
   }

   public void setTitle(WrapperAbstract wrapper, String title) {

   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, WrapperManagerDefaultJ2se.class, 29);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, WrapperManagerDefaultJ2se.class, 29);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
      
   }
   //#enddebug
   

}
