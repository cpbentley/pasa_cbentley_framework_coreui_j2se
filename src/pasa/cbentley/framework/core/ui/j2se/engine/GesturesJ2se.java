package pasa.cbentley.framework.core.ui.j2se.engine;

import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.ToStringStaticCoreUi;
import pasa.cbentley.framework.core.ui.src4.engine.GesturesAbstract;
import pasa.cbentley.framework.core.ui.src4.interfaces.ITechSenses;

public class GesturesJ2se extends GesturesAbstract {

   public GesturesJ2se(CoreUiJ2seCtx cuc) {
      super(cuc);
   }

   public void disableGesture(int flag) {
      //#debug
      toDLog().pEvent("" + ToStringStaticCoreUi.toStringGestureType(flag), null, GesturesJ2se.class, "disableGesture");
   }

   public void enableGesture(int flag) {
      //#debug
      toDLog().pEvent("" + ToStringStaticCoreUi.toStringGestureType(flag), null, GesturesJ2se.class, "enableGesture");

      if (flag == ITechSenses.GESTURE_TYPE_05_SHAKE) {

      } else if (flag == ITechSenses.GESTURE_TYPE_07_MOVE) {

      }
   }

   public boolean isGestureSupported(int flag) {
      return false;
   }

}
