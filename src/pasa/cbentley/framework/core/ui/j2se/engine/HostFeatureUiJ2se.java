package pasa.cbentley.framework.core.ui.j2se.engine;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import pasa.cbentley.core.src4.interfaces.IHostFeature;
import pasa.cbentley.framework.core.draw.j2se.engine.HostFeatureDrawJ2se;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.ObjectCUC;
import pasa.cbentley.framework.core.ui.src4.tech.ITechHostFeatureDrawUI;

/**
 * This class deals with all HostData, from Draw, UI and Core.
 * 
 * If you want a class limited to Draw, you 
 * @author Charles Bentley
 *
 */
public class HostFeatureUiJ2se extends ObjectCUC implements IHostFeature, ITechHostFeatureDrawUI {

   private HostFeatureDrawJ2se hostFeatureDrawJ2se;

   public HostFeatureUiJ2se(CoreUiJ2seCtx cuc) {
      super(cuc);

      hostFeatureDrawJ2se = cuc.getCoreDrawJ2seCtx().getHostFeatureDrawJ2se();
   }

   public boolean isHostFeatureEnabled(int featureID) {
      switch (featureID) {
         case SUP_ID_11_KEY_MAJ_TOGGLE:
            return isMajOn();
         case ITechHostFeatureDrawUI.SUP_ID_50_SENSOR_ACCELEROMETER:
            return false;
         default:
            return hostFeatureDrawJ2se.isHostFeatureEnabled(featureID);
      }
   }

   public boolean isHostFeatureFactoryEnabled(int featureID) {
      switch (featureID) {
         default:
            return hostFeatureDrawJ2se.isHostFeatureFactoryEnabled(featureID);
      }
   }

   public boolean isHostFeatureSupported(int featureID) {
      switch (featureID) {
         case SUP_ID_01_KEYBOARD:
         case SUP_ID_11_KEY_MAJ_TOGGLE:
         case SUP_ID_02_POINTERS:
         case SUP_ID_03_OPEN_GL:
         case SUP_ID_24_MULTIPLE_WINDOWS:
            return true;
         case SUP_ID_05_SCREEN_ROTATIONS:
         case SUP_ID_50_SENSOR_ACCELEROMETER:
            return false;
         default:
            return hostFeatureDrawJ2se.isHostFeatureSupported(featureID);
      }
   }

   public boolean isMajOn() {
      return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
   }

   public boolean setHostFeatureEnabled(int featureID, boolean b) {
      switch (featureID) {
         case SUP_ID_11_KEY_MAJ_TOGGLE:
            setMajOn(b);
            return true;
         default:
            return hostFeatureDrawJ2se.setHostFeatureEnabled(featureID, b);
      }
   }

   public boolean setHostFeatureEnabledFactory(int featureID, boolean b) {
      switch (featureID) {
         case ITechHostFeatureDrawUI.SUP_ID_50_SENSOR_ACCELEROMETER:
            return false;
         default:
            return hostFeatureDrawJ2se.setHostFeatureEnabledFactory(featureID, b);
      }
   }

   public boolean setHostFeatureFactoryOff(int featureID) {
      return setHostFeatureEnabledFactory(featureID, false);
   }

   public boolean setHostFeatureFactoryOn(int featureID) {
      return setHostFeatureEnabledFactory(featureID, true);
   }

   public boolean setHostFeatureOff(int featureID) {
      return this.setHostFeatureEnabled(featureID, false);
   }

   public boolean setHostFeatureOn(int featureID) {
      return this.setHostFeatureEnabled(featureID, true);
   }

   public void setMajOn(boolean b) {
      Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, b);
   }

}
