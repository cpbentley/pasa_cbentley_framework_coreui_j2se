package pasa.cbentley.framework.core.ui.j2se.ctx;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.ui.src4.ctx.ConfigCoreUiSettable;

public class ConfigCoreUiJ2seSettable extends ConfigCoreUiSettable implements IConfigCoreUiJ2se {

   protected int     h              = 600;

   protected boolean isCenter;

   protected boolean isSizeRatio;

   protected int     keyMappingType = 0;

   /**
    * If value is configurable
    */
   protected int     w              = 800;

   protected int     x              = 0;

   protected int     y              = 0;

   public ConfigCoreUiJ2seSettable(UCtx uc) {
      super(uc);
      isCenter = true;
      isSizeRatio = false;
   }

   public int getDefaultCanvasH() {
      return h;
   }

   public int getDefaultCanvasW() {
      return w;
   }

   public int getDefaultCanvasX() {
      return x;
   }

   public int getDefaultCanvasY() {
      return y;
   }

   public int getKeyMappingTypeJ2se() {
      return keyMappingType;
   }

   public boolean isCenterPosition() {
      return isCenter;
   }

   public boolean isRatioSize() {
      return isSizeRatio;
   }

   public void setDefaultCanvasH(int h) {
      this.h = h;
   }

   public void setDefaultCanvasW(int w) {
      this.w = w;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, ConfigCoreUiJ2seSettable.class, 50);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, ConfigCoreUiJ2seSettable.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
