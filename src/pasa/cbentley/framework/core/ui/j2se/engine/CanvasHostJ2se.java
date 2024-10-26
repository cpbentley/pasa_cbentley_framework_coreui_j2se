package pasa.cbentley.framework.core.ui.j2se.engine;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.stator.StatorWriterBO;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.stator.ITechStator;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.j2se.ctx.IConfigCoreUiJ2se;
import pasa.cbentley.framework.core.ui.src4.ctx.IBOTypesCoreUi;
import pasa.cbentley.framework.core.ui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.core.ui.src4.event.SenseEvent;
import pasa.cbentley.framework.core.ui.src4.event.VoiceEvent;
import pasa.cbentley.framework.core.ui.src4.interfaces.ITechSenses;
import pasa.cbentley.framework.core.ui.src4.tech.IBOCanvasHost;
import pasa.cbentley.framework.core.ui.src4.tech.IBOFramePos;
import pasa.cbentley.framework.core.ui.src4.wrapper.WrapperAbstract;

public abstract class CanvasHostJ2se extends CanvasHostAbstract {

   protected CoreUiJ2seCtx       cj2c;

   private float                 light     = 0.5f;

   /**
    * Modifies it to simulate several pointers
    */
   protected int                 pointerID = 0;

   protected WrapperAbstractJ2se wrapperJ2SE;

   public CanvasHostJ2se(CoreUiJ2seCtx j2seCtx, ByteObject boCanvasHost) {
      super(j2seCtx, boCanvasHost);
      this.cj2c = j2seCtx;
   }

   public void canvasHide() {
      wrapperJ2SE.canvasHide();
   }

   public void canvasShow() {
      wrapperJ2SE.canvasShow();
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

   public void setStartPositionAndSize() {
      //api method generates an event
      setStartPositionAndSize(false);
   }

   /**
    * Must be called once the wrapper has been set
    * @param isGenerateEvent false when init first time
    */
   public void setStartPositionAndSize(boolean isGenerateEvent) {
      if (wrapperJ2SE == null) {
         throw new NullPointerException();
      }

      //wrapper is known already
      
      //size it by default using config values
      IConfigCoreUiJ2se configUIJ2se = cj2c.getConfigUIJ2se();
      int w = configUIJ2se.getDefaultCanvasW();
      int h = configUIJ2se.getDefaultCanvasH();
      int x = configUIJ2se.getDefaultCanvasX();
      int y = configUIJ2se.getDefaultCanvasY();
      ByteObject boCanvasHost = getBOCanvasHost();
      boolean isCustomPosition = boCanvasHost.hasFlag(IBOCanvasHost.CANVAS_HOST_OFFSET_02_FLAX1, IBOCanvasHost.CANVAS_HOST_FLAGX_1_CUSTOM_POS);
      if (isCustomPosition) {
         ByteObject framePos = boCanvasHost.getSubIndexed1(IBOCanvasHost.CANVAS_HOST_OFFSET_14_FRAMEPOS1);
         if (framePos != null) {
            if (framePos.getType() == IBOTypesCoreUi.TYPE_8_FRAME_POS) {

               //
               x = framePos.get2Signed(IBOFramePos.FPOS_OFFSET_02_X2);
               y = framePos.get2Signed(IBOFramePos.FPOS_OFFSET_03_Y2);

               w = framePos.get2(IBOFramePos.FPOS_OFFSET_04_W2);
               h = framePos.get2(IBOFramePos.FPOS_OFFSET_05_H2);

            }
         } else {
            //#debug
            toDLog().pNull("ByteObject framePos is null", this, CanvasHostJ2se.class, "setStartPositionAndSize@126", LVL_05_FINE, true);
         }
      } else {
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         boolean sizeRatio = configUIJ2se.isRatioSize();
         if (sizeRatio) {
            w = screenSize.width / 2;
            h = screenSize.height / 2;
         }

         boolean b = configUIJ2se.isCenterPosition();
         if (b) {
            x = screenSize.width / 2 - w / 2;
            y = screenSize.height / 2 - h / 2;
         }
      }

      //#debug
      toDLog().pFlow("width=" + w + " height=" + h, this, CanvasHostJ2se.class, "setStartPositionAndSize@144", LVL_05_FINE, true);

      icSetSize(w, h);
      if (isGenerateEvent) {
         canvasSizeChangedBridge(w, h);
      }

      icSetXY(x, y);
      if (isGenerateEvent) {
         int screenID = 0;
         canvasPositionChangedBridge(screenID, x, y);
      }

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
            //#debug
            toDLog().pCmd("F1 changes pointerID to "+ pointerID, this, toStringGetLine(CanvasHostJ2se.class, "simulatePointer", 175), LVL_05_FINE, true);
            break;
         case KeyEvent.VK_F2:
            pointerID = 1;
            //#debug
            toDLog().pCmd("F2 changes pointerID to "+ pointerID, this, toStringGetLine(CanvasHostJ2se.class, "simulatePointer", 175), LVL_05_FINE, true);
            break;
         case KeyEvent.VK_F3:
            pointerID = 2;
            //#debug
            toDLog().pCmd("F3 changes pointerID to "+ pointerID, this, toStringGetLine(CanvasHostJ2se.class, "simulatePointer", 175), LVL_05_FINE, true);
            break;
         case KeyEvent.VK_F4:
            pointerID = 3;
            //#debug
            toDLog().pCmd("F4 changes pointerID to "+ pointerID, this, toStringGetLine(CanvasHostJ2se.class, "simulatePointer", 175), LVL_05_FINE, true);
            break;
         case KeyEvent.VK_F5:
            pointerID = 4;
            //#debug
            toDLog().pCmd("F5 changes pointerID to "+ pointerID, this, toStringGetLine(CanvasHostJ2se.class, "simulatePointer", 175), LVL_05_FINE, true);
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

         //#debug
         toDLog().pEvent("F12: Simulating Shake SenseEvent", ge, toStringGetLine(CanvasHostJ2se.class, "simulationKeys", 40), LVL_05_FINE, true);
         
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

   /**
    * Method for those wrappers that just use the default frame positions.
    * 
    * This is only called by host implementations that have movable frames
    * 
    */
   protected ByteObject getFramePosNewCurrentState() {
      //sets current pos. This is the absolute position

      //delegate to the wrapper how to set the ui state
      ByteObject framePos = cuc.createBOFrameDefault();
      CanvasHostAbstract ch = this;

      //wrapper size

      int cx = ch.getICX(); // what is this? the wrapper position or the component position which is 0

      framePos.set2(IBOFramePos.FPOS_OFFSET_02_X2, cx);
      framePos.set2(IBOFramePos.FPOS_OFFSET_03_Y2, ch.getICY());
      framePos.set2(IBOFramePos.FPOS_OFFSET_04_W2, ch.getICWidth());
      framePos.set2(IBOFramePos.FPOS_OFFSET_05_H2, ch.getICHeight());

      boolean isFullScreen = ch.isCanvasFeatureEnabled(SUP_ID_27_FULLSCREEN);
      framePos.setFlag(IBOFramePos.FPOS_OFFSET_01_FLAG, IBOFramePos.FPOS_FLAG_1_FULLSCREEN, isFullScreen);

      boolean isAlwaysOnTop = ch.isCanvasFeatureEnabled(SUP_ID_28_ALWAYS_ON_TOP);
      framePos.setFlag(IBOFramePos.FPOS_OFFSET_01_FLAG, IBOFramePos.FPOS_FLAG_5_ALWAYS_ON_TOP, isAlwaysOnTop);

      return framePos;
   }

   public void stateWriteTo(StatorWriter state) {
      super.stateWriteTo(state);

      //#debug
      toDLog().pStator("Writing", state, CanvasHostJ2se.class, "stateWriteTo@130", LVL_04_FINER, true);
   }

   public void stateWriteToParamSub(StatorWriter state) {

      //write framepos to can
      boCanvasHost.setFlag(IBOCanvasHost.CANVAS_HOST_OFFSET_02_FLAX1, IBOCanvasHost.CANVAS_HOST_FLAGX_1_CUSTOM_POS, true);
      ByteObject boFramePos = getFramePosNewCurrentState();
      boCanvasHost.setSubIndexed1(boFramePos, IBOCanvasHost.CANVAS_HOST_OFFSET_14_FRAMEPOS1);

      //#debug
      toDLog().pStator("boFramePos", boFramePos, CanvasHostJ2se.class, "stateWriteToParamSub@274", LVL_04_FINER, true);

      //now call super for writing it
      super.stateWriteToParamSub(state);
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
