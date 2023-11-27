package pasa.cbentley.framework.coreui.j2se.engine;

import pasa.cbentley.framework.coreui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.coreui.src4.ctx.ToStringStaticCoreUi;
import pasa.cbentley.framework.coreui.src4.engine.AbstractGestures;
import pasa.cbentley.framework.coreui.src4.interfaces.ISenses;

public class J2SEGestures extends AbstractGestures  {


   public J2SEGestures(CoreUiJ2seCtx cuc) {
      super(cuc);
   }

   @Override
   public void enableGesture(int flag) {
      //#debug
      toDLog().pEvent("" + ToStringStaticCoreUi.getStringGestureType(flag), null, J2SEGestures.class, "enableGesture");
      
      if (flag == ISenses.GESTURE_TYPE_05_SHAKE) {

      } else if (flag == ISenses.GESTURE_TYPE_07_MOVE) {

      }
   }

   public boolean isGestureSupported(int flag) {
      return false;
   }

   @Override
   public void disableGesture(int flag) {
      //#debug
      toDLog().pEvent("" + ToStringStaticCoreUi.getStringGestureType(flag), null, J2SEGestures.class, "disableGesture");
   }


}
