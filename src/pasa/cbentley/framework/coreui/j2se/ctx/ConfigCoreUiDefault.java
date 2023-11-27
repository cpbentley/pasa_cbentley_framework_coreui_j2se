package pasa.cbentley.framework.coreui.j2se.ctx;

import pasa.cbentley.byteobjects.src4.ctx.ConfigAbstractBO;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.framework.coreui.src4.tech.IInputConstants;

public class ConfigCoreUiDefault extends ConfigAbstractBO implements IConfigCoreUiJ2se {

   private int w;

   private int h;

   public ConfigCoreUiDefault(UCtx uc, int w, int h) {
      super(uc);
      this.w = w;
      this.h = h;
   }

   public boolean isFullscreen() {
      return false;
   }

   public String getIconPathDefault() {
      return null;
   }

   public int getDefaultCanvasW() {
      return w;
   }

   public int getDefaultCanvasH() {
      return h;
   }

   public int getAllerRetourMinAmplitudePixel() {
      return IInputConstants.BF_ALLER_RETOUR_MIN_AMPLITUDE;
   }
}
