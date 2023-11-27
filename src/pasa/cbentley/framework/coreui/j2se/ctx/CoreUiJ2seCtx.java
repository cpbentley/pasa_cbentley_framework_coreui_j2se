package pasa.cbentley.framework.coreui.j2se.ctx;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.IConfigBO;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.coredraw.j2se.ctx.CoreDrawJ2seCtx;
import pasa.cbentley.framework.coreui.j2se.engine.J2SEGestures;
import pasa.cbentley.framework.coreui.src4.ctx.CoreUiCtx;
import pasa.cbentley.framework.coreui.src4.event.GestureArea;
import pasa.cbentley.framework.coreui.src4.interfaces.IHostGestures;
import pasa.cbentley.framework.coreui.src4.tech.ITechCanvasHost;
import pasa.cbentley.framework.coreui.src4.tech.ITechFeaturesUI;

/**
 * Template for {@link CoreUiCtx} implementations on the J2SE platform.
 * 
 * <li>Swing
 * <li>Swt
 * <li>JavaFx
 * 
 * @author Charles Bentley
 *
 */
public abstract class CoreUiJ2seCtx extends CoreUiCtx implements ITechCtxSettingsCoreUIJ2se {

   protected final J2SEGestures      j2seGestures;

   private boolean                   isNumPadInvert = false;

   protected final IConfigCoreUiJ2se configUIJ2SE;

   public CoreUiJ2seCtx(IConfigCoreUiJ2se configUI, CoreDrawJ2seCtx cdc) {
      super(configUI, cdc);

      //#debug
      cdc.toStringCheckNull(configUI);

      this.configUIJ2SE = configUI;
      j2seGestures = new J2SEGestures(this);
   }

   public IHostGestures getHostGestures() {
      return j2seGestures;
   }

   public IConfigCoreUiJ2se getConfigUIJ2se() {
      return configUIJ2SE;
   }

   public boolean isInverseNumPad28() {
      return true;
   }

   public int getBOCtxSettingSize() {
      return CTX_COREUIJ2_BASIC_SIZE;
   }

   public boolean isMajOn() {
      boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
      return isOn;
   }

   /**
    * {@link ITechFeaturesUI}
    * 
    * numLock feature
    * @return
    */
   public boolean isNumLockOn() {
      boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK);
      return isOn;
   }

   public void setMajOn(boolean b) {
      Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, b);
   }

   protected void matchConfig(IConfigBO config, ByteObject settings) {
      super.matchConfig(config, settings);
      IConfigCoreUiJ2se config2 = (IConfigCoreUiJ2se) config;
      settings.set2(CTX_COREUIJ2_OFFSET_02_DEF_W2, config2.getDefaultCanvasW());
      settings.set2(CTX_COREUIJ2_OFFSET_03_DEF_H2, config2.getDefaultCanvasH());
   }

   /**
    * Most will use the Bentley framework values which correspond to a
    * 1200*800 screen
    */
   public int getHostInt(int dataID) {
      switch (dataID) {
         case DATA_ID_04_POINTER_DRAG_SLOP:
            return 50;
         case DATA_ID_02_POINTER_NUPLE_SLOP:
            return 50;
         case DATA_ID_09_SLIDE_MIN_AMPLITUDE:
            return 30;
         case DATA_ID_11_FLING_SPEED_MAX:
            return 500;
         case DATA_ID_12_FLING_SPEED_MIN:
            return 5;
         case DATA_ID_17_NUMBER_OF_SCREENS:
            return getNumOfScreens();
         case DATA_ID_18_SCREEN_ORIENTATION:
            //we only have one screen orientation in desktop
            return ITechCanvasHost.SCREEN_0_TOP_NORMAL;
         default:
            break;
      }
      return 0;
   }

   public int getNumOfScreens() {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice[] screens = ge.getScreenDevices();
      int n = screens.length;
      return n;
   }

   public GestureArea[] getScreens() {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice[] gds = ge.getScreenDevices();
      GestureArea[] gas = new GestureArea[gds.length];
      for (int i = 0; i < gds.length; i++) {
         Rectangle r = gds[i].getDefaultConfiguration().getBounds();
         gas[i] = new GestureArea(r.x, r.y, r.width, r.height);
      }
      return gas;
   }

   public boolean isNumPadInvert() {
      return isNumPadInvert;
   }

   public void setNumPadInvert(boolean isNumPadInvert) {
      this.isNumPadInvert = isNumPadInvert;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, CoreUiJ2seCtx.class, "@line101");
      toStringPrivate(dc);
      super.toString(dc.sup());

      dc.appendVarWithSpace("getNumOfScreens", getNumOfScreens());

      dc.appendVarWithSpace("isNumPadInvert", isNumPadInvert);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, CoreUiJ2seCtx.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
