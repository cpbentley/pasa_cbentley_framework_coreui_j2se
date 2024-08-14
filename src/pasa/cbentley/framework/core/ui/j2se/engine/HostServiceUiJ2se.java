package pasa.cbentley.framework.core.ui.j2se.engine;

import pasa.cbentley.core.src4.api.ApiManager;
import pasa.cbentley.core.src4.interfaces.IAPIService;
import pasa.cbentley.core.src4.interfaces.IHostService;
import pasa.cbentley.framework.core.draw.j2se.engine.HostServiceDrawJ2se;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.ObjectCUC;
import pasa.cbentley.framework.core.ui.src4.tech.ITechHostServiceDrawUI;

/**
 * This class deals with all HostData, from Draw, UI and Core.
 * 
 * If you want a class limited to Draw, you 
 * @author Charles Bentley
 *
 */
public class HostServiceUiJ2se extends ObjectCUC implements IHostService, ITechHostServiceDrawUI {

   private HostServiceDrawJ2se hostServiceDrawJ2se;

   public HostServiceUiJ2se(CoreUiJ2seCtx cuc) {
      super(cuc);

      hostServiceDrawJ2se = cuc.getCoreDrawJ2seCtx().getHostServiceDrawJ2se();
   }

   public boolean setHostServiceActive(int serviceID, boolean isActive) {
      switch (serviceID) {
         case SERVICE_ID_37_JINPUT:
         case SERVICE_ID_38_GAMEPADS:
            ApiManager apiManager = cuc.getApiManager();
            IAPIService isa = apiManager.getAPIService(serviceID);
            if (isa != null) {
               if (isActive) {
                  isa.startService(serviceID, null);
               } else {
                  isa.stopService(serviceID, null);
               }
               return isa.isServiceRunning(serviceID, null);
            }
         default:
            return hostServiceDrawJ2se.setHostServiceActive(serviceID, isActive);
      }
   }

   public boolean setHostServiceOff(int serviceID) {
      return setHostServiceActive(serviceID, false);
   }

   public boolean setHostServiceOn(int serviceID) {
      return setHostServiceActive(serviceID, true);
   }

   public boolean isHostServiceSupported(int serviceID) {
      switch (serviceID) {
         case SERVICE_ID_37_JINPUT:
         case SERVICE_ID_38_GAMEPADS:
            ApiManager apiManager = cuc.getApiManager();
            return apiManager.getAPIService(serviceID) != null;
         default:
            return hostServiceDrawJ2se.isHostServiceSupported(serviceID);
      }
   }

   public boolean isHostServiceActive(int serviceID) {
      switch (serviceID) {
         case SERVICE_ID_37_JINPUT:
         case SERVICE_ID_38_GAMEPADS:
            ApiManager apiManager = cuc.getApiManager();
            IAPIService isa = apiManager.getAPIService(serviceID);
            if (isa != null) {
               return isa.isServiceRunning(serviceID, null);
            }
            return false;
         default:
            return hostServiceDrawJ2se.isHostServiceActive(serviceID);
      }
   }

}
