package pasa.cbentley.framework.core.ui.j2se.ctx;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.framework.core.ui.j2se.ctx.IConfigCoreUiJ2se;
import pasa.cbentley.framework.core.ui.src4.ctx.ConfigCoreUiDefault;

public class ConfigCoreUIJ2SEDefault extends ConfigCoreUiDefault implements IConfigCoreUiJ2se {

   /**
    * If value is configurable
    */
   protected int     w = 800;

   protected int     h = 600;

   protected int     x          = 0;

   protected int     y          = 0;

   protected boolean isCentered = true;

   public ConfigCoreUIJ2SEDefault(UCtx uc) {
      super(uc);
   }

   public int getDefaultCanvasW() {
      return w;
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
}
