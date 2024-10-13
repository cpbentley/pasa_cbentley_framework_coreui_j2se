package pasa.cbentley.framework.core.ui.j2se.ctx;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.IConfigBO;
import pasa.cbentley.core.j2se.ctx.J2seCoreCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.draw.j2se.ctx.CoreDrawJ2seCtx;
import pasa.cbentley.framework.core.ui.j2se.engine.GesturesJ2se;
import pasa.cbentley.framework.core.ui.j2se.engine.HostDataUiJ2se;
import pasa.cbentley.framework.core.ui.j2se.engine.HostFeatureUiJ2se;
import pasa.cbentley.framework.core.ui.j2se.engine.HostServiceUiJ2se;
import pasa.cbentley.framework.core.ui.src4.ctx.CoreUiCtx;
import pasa.cbentley.framework.core.ui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.core.ui.src4.interfaces.IHostGestures;
import pasa.cbentley.framework.core.ui.src4.tech.ITechHostUI;
import pasa.cbentley.framework.core.ui.src4.wrapper.WrapperAbstract;

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
public abstract class CoreUiJ2seCtx extends CoreUiCtx implements ITechCtxSettingsCoreUiJ2se {

   private boolean              isNumPadInvert = false;

   protected final GesturesJ2se j2seGestures;

   public CoreUiJ2seCtx(IConfigCoreUiJ2se configUI, CoreDrawJ2seCtx cdc) {
      super(configUI, cdc);

      //#debug
      cdc.toStringCheckNull(configUI);

      j2seGestures = new GesturesJ2se(this);
   }

   public abstract CanvasHostAbstract createCanvasHost(WrapperAbstract wrapper, ByteObject boCanvasHost);

   public int getBOCtxSettingSize() {
      return CTX_COREUIJ2_BASIC_SIZE;
   }

   public IConfigCoreUiJ2se getConfigUIJ2se() {
      return (IConfigCoreUiJ2se) config;
   }

   public CoreDrawJ2seCtx getCoreDrawJ2seCtx() {
      return (CoreDrawJ2seCtx) cdc;
   }

   public abstract HostDataUiJ2se getHostDataUIJ2se();

   public abstract HostFeatureUiJ2se getHostFeatureUiJ2se();

   public IHostGestures getHostGestures() {
      return j2seGestures;
   }

   public abstract HostServiceUiJ2se getHostServiceUiJ2se();


   public J2seCoreCtx getJ2C() {
      return getCoreDrawJ2seCtx().getJ2C();
   }

   public boolean isInverseNumPad28() {
      return true;
   }

   public boolean isMajOn() {
      boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
      return isOn;
   }

   /**
    * {@link ITechHostUI}
    * 
    * numLock feature
    * @return
    */
   public boolean isNumLockOn() {
      boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK);
      return isOn;
   }

   public boolean isNumPadInvert() {
      return isNumPadInvert;
   }

   protected void matchConfig(IConfigBO config, ByteObject settings) {
      super.matchConfig(config, settings);
      IConfigCoreUiJ2se config2 = (IConfigCoreUiJ2se) config;
      settings.set2(CTX_COREUIJ2_OFFSET_02_DEF_W2, config2.getDefaultCanvasW());
      settings.set2(CTX_COREUIJ2_OFFSET_03_DEF_H2, config2.getDefaultCanvasH());
   }

   public void setMajOn(boolean b) {
      Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, b);
   }

   public void setNumPadInvert(boolean isNumPadInvert) {
      this.isNumPadInvert = isNumPadInvert;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, CoreUiJ2seCtx.class, 146);
      toStringPrivate(dc);
      super.toString(dc.sup());

      dc.appendVarWithSpace("isNumPadInvert", isNumPadInvert);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, CoreUiJ2seCtx.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
