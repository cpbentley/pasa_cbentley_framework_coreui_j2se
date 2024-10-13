package pasa.cbentley.framework.core.ui.j2se.ctx;

import pasa.cbentley.byteobjects.src4.ctx.ConfigAbstractBO;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.framework.core.ui.src4.tech.ITechInputConstants;

public class ConfigCoreUiDefault extends ConfigAbstractBO implements IConfigCoreUiJ2se {

   private int h;

   protected int keyMappingType = 0;

   private int w;

   public ConfigCoreUiDefault(UCtx uc, int w, int h) {
      super(uc);
      this.w = w;
      this.h = h;
   }

   public int getAllerRetourMinAmplitudePixel() {
      return ITechInputConstants.BF_ALLER_RETOUR_MIN_AMPLITUDE;
   }

   public int getDefaultCanvasH() {
      return h;
   }

   public int getDefaultCanvasW() {
      return w;
   }

   public String getIconPathDefault() {
      return null;
   }

   public int getKeyMappingTypeJ2se() {
      return keyMappingType;
   }

   public boolean isFullscreen() {
      return false;
   }

   public int getDefaultCanvasX() {
      return 0;
   }

   public int getDefaultCanvasY() {
      return 0;
   }

   public boolean isCenterPosition() {
      return true;
   }

   public boolean isRatioSize() {
      return true;
   }
}
