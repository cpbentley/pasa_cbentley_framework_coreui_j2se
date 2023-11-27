package pasa.cbentley.framework.coreui.j2se.ctx;

import pasa.cbentley.framework.coreui.src4.ctx.ITechCtxSettingsCoreUI;

public interface ITechCtxSettingsCoreUIJ2se extends ITechCtxSettingsCoreUI {

   public static final int CTX_COREUIJ2_BASIC_SIZE       = CTX_COREUI_BASIC_SIZE + 9;

   public static final int CTX_COREUIJ2_OFFSET_01_FLAG1  = CTX_COREUI_BASIC_SIZE;

   /**
    * The default width of a new canvas.
    * Irrelevant if fullscreen
    */
   public static final int CTX_COREUIJ2_OFFSET_02_DEF_W2 = CTX_COREUI_BASIC_SIZE + 1;

   /**
    *  The default height of a new canvas.
    *  Irrelevant if fullscreen
    */
   public static final int CTX_COREUIJ2_OFFSET_03_DEF_H2 = CTX_COREUI_BASIC_SIZE + 3;

   /**
    * The default x of a new canvas.
    * Irrelevant if fullscreen
    */
   public static final int CTX_COREUIJ2_OFFSET_04_DEF_X2 = CTX_COREUI_BASIC_SIZE + 5;

   /**
    *  The default y of a new canvas.
    *  Irrelevant if fullscreen
    */
   public static final int CTX_COREUIJ2_OFFSET_05_DEF_Y2 = CTX_COREUI_BASIC_SIZE + 7;
}
