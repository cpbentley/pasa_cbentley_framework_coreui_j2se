package pasa.cbentley.framework.core.ui.j2se.engine;

import java.awt.event.KeyEvent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.stator.StatorBO;
import pasa.cbentley.byteobjects.src4.stator.StatorWriterBO;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.stator.ITechStator;
import pasa.cbentley.core.src4.stator.Stator;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.j2se.ctx.IConfigCoreUiJ2se;
import pasa.cbentley.framework.core.ui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.core.ui.src4.engine.WrapperAbstract;
import pasa.cbentley.framework.core.ui.src4.event.SenseEvent;
import pasa.cbentley.framework.core.ui.src4.event.VoiceEvent;
import pasa.cbentley.framework.core.ui.src4.interfaces.ITechSenses;
import pasa.cbentley.framework.core.ui.src4.tech.IBOFramePos;

public abstract class CanvasHostJ2se extends CanvasHostAbstract {

   protected CoreUiJ2seCtx       cj2c;

   private float                 light     = 0.5f;

   /**
    * Modifies it to simulate several pointers
    */
   protected int                 pointerID = 0;

   protected WrapperAbstractJ2se wrapperJ2SE;

   public CanvasHostJ2se(CoreUiJ2seCtx j2seCtx, ByteObject tech) {
      super(j2seCtx, tech);
      this.cj2c = j2seCtx;
   }

   public void canvasHide() {
      wrapperJ2SE.canvasHide();
   }

   public void canvasShow() {
      wrapperJ2SE.canvasShow();
   }

   /**
    * Method for those wrappers that just use the default frame positions.
    * 
    * This is only called by host implementations that have movable frames
    * 
    * @param state
    */
   protected void stateWriteHelperFrame(StatorWriter state) {
      //sets current pos. This is the absolute position

      //delegate to the wrapper how to set the ui state
      ByteObject framePos = cuc.createBOFrameDefault();
      CanvasHostAbstract ch = this;
      int cx = ch.getICX(); // what is this? the wrapper position or the component position which is 0
      framePos.set2(IBOFramePos.FPOS_OFFSET_02_X2, cx);
      framePos.set2(IBOFramePos.FPOS_OFFSET_03_Y2, ch.getICY());
      framePos.set2(IBOFramePos.FPOS_OFFSET_04_W2, ch.getICWidth());
      framePos.set2(IBOFramePos.FPOS_OFFSET_05_H2, ch.getICHeight());

      boolean isFullScreen = ch.isCanvasFeatureEnabled(SUP_ID_27_FULLSCREEN);
      framePos.setFlag(IBOFramePos.FPOS_OFFSET_01_FLAG, IBOFramePos.FPOS_FLAG_1_FULLSCREEN, isFullScreen);
   }

   public int getICX() {
      return wrapperJ2SE.getX();
   }

   public int getICY() {
      return wrapperJ2SE.getY();
   }

   public void icSetSize(int w, int h) {
      wrapperJ2SE.setSize(w, h);
   }

   public void icSetXY(int x, int y) {
      wrapperJ2SE.setPosition(x, y);
   }

   /**
    * Check for..
    */
   public boolean isCanvasFeatureEnabled(int feature) {
      return false;
   }

   /**
    * J2SE supports 
    * 
    * <li> {@link ITechHost#SUP_ID_27_FULLSCREEN}
    */
   public boolean isCanvasFeatureSupported(int feature) {
      if (feature == SUP_ID_27_FULLSCREEN) {
         return true;
      }
      return false;
   }

   public boolean setCanvasFeature(int feature, boolean mode) {
      return false;
   }

   public void setDefaultStartPosition() {
      //api method generates an event
      setDefaultStartPosition(false);
   }

   /**
    * Must be called once the wrapper has been set
    * @param isGenerateEvent false when init first time
    */
   public void setDefaultStartPosition(boolean isGenerateEvent) {
      if (wrapperJ2SE == null) {
         throw new NullPointerException();
      }
      //size it by default using config values
      IConfigCoreUiJ2se configUIJ2se = cj2c.getConfigUIJ2se();
      int w = configUIJ2se.getDefaultCanvasW();
      int h = configUIJ2se.getDefaultCanvasH();

      //#debug
      toDLog().pFlow("width=" + w + " height=" + h, this, CanvasHostJ2se.class, "setDefaultStartPosition@line82", LVL_05_FINE, true);

      icSetSize(w, h);
      if (isGenerateEvent) {
         canvasSizeChangedBridge(w, h);
      }

      //position it on the center of the screen      
      //ask wrapper to set to default
      wrapperJ2SE.setDefaultStartPosition();
   }

   public void setWrapper(WrapperAbstract wrapper) {
      if (wrapper instanceof WrapperAbstractJ2se) {
         this.wrapperJ2SE = (WrapperAbstractJ2se) wrapper;
         super.setWrapper(wrapper);
      } else {
         throw new IllegalArgumentException();
      }
   }

   private void simulatePointer(int keyCode) {
      switch (keyCode) {
         case KeyEvent.VK_F1:
            pointerID = 0;
            break;
         case KeyEvent.VK_F2:
            pointerID = 1;
            break;
         case KeyEvent.VK_F3:
            pointerID = 2;
            break;
         case KeyEvent.VK_F4:
            pointerID = 3;
            break;
         case KeyEvent.VK_F5:
            pointerID = 4;
            break;
         default:
            break;
      }
   }

   private void simulateWord() {

   }

   //simulate pointer
   protected void simulationKeys(int bentleyKeyCode) {
      simulatePointer(bentleyKeyCode);

      final CoreUiJ2seCtx cuc = cj2c;
      if (bentleyKeyCode == KeyEvent.VK_F12) {
         //simulate shake
         SenseEvent ge = new SenseEvent(cuc, ITechSenses.GESTURE_TYPE_05_SHAKE);
         eventBridgeGuiLater(ge);
      } else if (bentleyKeyCode == KeyEvent.VK_F11) {
         VoiceEvent ge = new VoiceEvent(cuc);
         ge.setMatches(new String[] { "SimulatedWord1", "SimulatedWord2" });
         eventBridgeGuiLater(ge);
      } else if (bentleyKeyCode == KeyEvent.VK_F10) {
         SenseEvent ge = new SenseEvent(cuc, ITechSenses.GESTURE_TYPE_08_LIGHT);
         light += 0.1f;
         if (light > 1) {
            light = 0;
         }
         ge.setValue(light);
         eventBridgeGuiLater(ge);
      } else if (bentleyKeyCode == KeyEvent.VK_F9) {
         SenseEvent ge = new SenseEvent(cuc, ITechSenses.GESTURE_TYPE_07_MOVE);
         ge.setValues(new float[] { 2, 5, 6 });
         eventBridgeGuiLater(ge);
      }
   }

   public void stateReadFrom(StatorReader state) {
      super.stateReadFrom(state);

      //look for a position
   }

   public void stateWriteTo(StatorWriter state) {
      super.stateWriteTo(state);

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, CanvasHostJ2se.class, 205);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, CanvasHostJ2se.class, 251);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
