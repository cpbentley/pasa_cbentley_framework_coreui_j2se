package pasa.cbentley.framework.core.ui.j2se.ctx;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.ConfigCoreUiDefault;

public class ConfigCoreUiJ2seDefault extends ConfigCoreUiDefault implements IConfigCoreUiJ2se {

   protected int     h              = 600;

   protected boolean isCentered     = true;

   private boolean   isRatioSize;

   protected int     keyMappingType = 0;

   /**
    * If value is configurable
    */
   protected int     w              = 800;

   protected int     x              = 0;

   protected int     y              = 0;

   public ConfigCoreUiJ2seDefault(UCtx uc) {
      super(uc);
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
      return isCentered;
   }

   public boolean isRatioSize() {
      return isRatioSize;
   }
}
