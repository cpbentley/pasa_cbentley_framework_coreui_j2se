package pasa.cbentley.framework.core.ui.j2se.ctx;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.ui.src4.ctx.ConfigCoreUiSettable;

public class ConfigCoreUiJ2seSettable extends ConfigCoreUiSettable implements IConfigCoreUiJ2se {

   /**
    * If value is configurable
    */
   protected int     w          = 800;

   protected int     h          = 600;

   protected int     x          = 0;

   protected int     y          = 0;

   protected boolean isCentered = true;

   public ConfigCoreUiJ2seSettable(UCtx uc) {
      super(uc);
      isCentered = true;
   }

   public int getDefaultCanvasW() {
      return w;
   }

   public void setDefaultCanvasH(int h) {
      this.h = h;
   }

   public void setDefaultCanvasW(int w) {
      this.w = w;
   }

   public int getDefaultCanvasH() {
      return h;
   }

   public int getDefaultCanvasX() {
      return x;
   }

   public int getDefaultCanvasY() {
      return y;
   }

   public boolean isCentered() {
      return isCentered;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, ConfigCoreUiJ2seSettable.class, 50);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, ConfigCoreUiJ2seSettable.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
