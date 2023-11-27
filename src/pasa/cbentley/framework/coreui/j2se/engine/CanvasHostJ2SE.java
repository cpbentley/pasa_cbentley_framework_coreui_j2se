package pasa.cbentley.framework.coreui.j2se.engine;

import java.awt.event.KeyEvent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.framework.coreui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.coreui.j2se.ctx.IConfigCoreUiJ2se;
import pasa.cbentley.framework.coreui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.coreui.src4.engine.WrapperAbstract;
import pasa.cbentley.framework.coreui.src4.event.SenseEvent;
import pasa.cbentley.framework.coreui.src4.event.VoiceEvent;
import pasa.cbentley.framework.coreui.src4.interfaces.ISenses;

public abstract class CanvasHostJ2SE extends CanvasHostAbstract {

   protected CoreUiJ2seCtx       cj2c;

   private float                 light     = 0.5f;

   /**
    * Modifies it to simulate several pointers
    */
   protected int                 pointerID = 0;

   protected WrapperAbstractJ2SE wrapperJ2SE;

   public CanvasHostJ2SE(CoreUiJ2seCtx j2seCtx, ByteObject tech) {
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
    * The X position of the Host Canvas in the host referential.
    * @return
    */
   public int getICX() {
      return wrapperJ2SE.getX();
   }

   /**
    * The Y position of the Host Canvas in the host referential. Wrapper decides what is X
    * @return
    */
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
      if (feature == SUP_ID_04_ALIAS) {
         return true;
      }
      //ask the canvas implementation if the feature is supported
      return false;
   }

   /**
    * Deals wih 
    * <li> {@link ITechHost#SUP_ID_27_FULLSCREEN}
    */
   public boolean isCanvasFeatureSupported(int feature) {
      if (feature == SUP_ID_27_FULLSCREEN) {
         return true;
      }
      return false;
   }

   @Override
   public boolean setCanvasFeature(int feature, boolean mode) {
      if (feature == SUP_ID_27_FULLSCREEN) {
         setFullScreenMode(mode);
         return true;
      } else if (feature == SUP_ID_04_ALIAS) {
         toggleAlias();
         return true;
      }
      return false;
   }

   public void setDefaultStartPosition() {
      //size it by default using config values
      IConfigCoreUiJ2se configUIJ2se = cj2c.getConfigUIJ2se();
      int w = configUIJ2se.getDefaultCanvasW();
      int h = configUIJ2se.getDefaultCanvasH();

      //#debug
      toDLog().pFlow("width=" + w + " height=" + h, this, CanvasHostJ2SE.class, "setDefaultStartPosition@line82", LVL_05_FINE, true);

      icSetSize(w, h);
      int id = 0;
      canvasSizeChangedBridge(id, w, h);
      //position it on the center of the screen      
      //ask wrapper to set to default
      wrapperJ2SE.setDefaultStartPosition();
   }

   public void setWrapper(WrapperAbstract wrapper) {
      if (wrapper instanceof WrapperAbstractJ2SE) {
         this.wrapperJ2SE = (WrapperAbstractJ2SE) wrapper;
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

      final CoreUiJ2seCtx cac = cj2c;
      if (bentleyKeyCode == KeyEvent.VK_F12) {
         //simulate shake
         cac.runGUI(new Runnable() {
            public void run() {
               SenseEvent ge = new SenseEvent(cac, ISenses.GESTURE_TYPE_05_SHAKE);
               eventBridge(ge);
            }
         });
      } else if (bentleyKeyCode == KeyEvent.VK_F11) {
         cac.runGUI(new Runnable() {
            public void run() {
               VoiceEvent ge = new VoiceEvent(cac);
               ge.setMatches(new String[] { "SimulatedWord1", "SimulatedWord2" });
               eventBridge(ge);
            }
         });
      } else if (bentleyKeyCode == KeyEvent.VK_F10) {
         cac.runGUI(new Runnable() {
            public void run() {
               SenseEvent ge = new SenseEvent(cac, ISenses.GESTURE_TYPE_08_LIGHT);
               light += 0.1f;
               if (light > 1) {
                  light = 0;
               }
               ge.setValue(light);
               eventBridge(ge);
            }
         });
      } else if (bentleyKeyCode == KeyEvent.VK_F9) {
         cac.runGUI(new Runnable() {
            public void run() {
               SenseEvent ge = new SenseEvent(cac, ISenses.GESTURE_TYPE_07_MOVE);
               ge.setValues(new float[] { 2, 5, 6 });
               eventBridge(ge);
            }
         });
      }
   }

   public void stateReadFrom(StatorReader state) {
      super.stateReadFrom(state);
   }

   public void stateWriteTo(StatorWriter state) {
      super.stateWriteTo(state);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, CanvasHostJ2SE.class, 205);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "CanvasHostJ2SE");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
