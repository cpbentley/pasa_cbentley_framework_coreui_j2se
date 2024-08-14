package pasa.cbentley.framework.core.ui.j2se.engine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.core.draw.j2se.engine.HostDataDrawJ2se;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.ObjectCUC;
import pasa.cbentley.framework.core.ui.src4.event.GestureArea;
import pasa.cbentley.framework.core.ui.src4.tech.ITechHostDataDrawUi;
import pasa.cbentley.framework.core.ui.src4.tech.ITechHostUI;

public abstract class HostDataUiJ2se extends ObjectCUC implements ITechHostDataDrawUi {

   private HostDataDrawJ2se hostDataDrawJ2se;

   public HostDataUiJ2se(CoreUiJ2seCtx cuc) {
      super(cuc);

      hostDataDrawJ2se = cuc.getCoreDrawJ2seCtx().getHostDataDrawJ2SE();
   }

   public float getHostDataFloat(int dataID) {
      switch (dataID) {

         default:
            return hostDataDrawJ2se.getHostDataFloat(dataID);
      }
   }

   public int getNumOfScreens() {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice[] screens = ge.getScreenDevices();
      int n = screens.length;
      return n;
   }
   
   public int getHostDataInt(int dataID) {
      switch (dataID) {
         case DATA_ID_01_POINTER_LONG_TIMEOUT:
            return 200;
         case DATA_ID_02_POINTER_NUPLE_SLOP:
            return 50;
         case DATA_ID_03_POINTER_NUPLE_TIMEOUT:
            return 200;
         case DATA_ID_04_POINTER_DRAG_SLOP:
            return 50;
         case DATA_ID_09_SLIDE_MIN_AMPLITUDE:
            return 30;
         case DATA_ID_11_FLING_SPEED_MAX:
            return 500;
         case DATA_ID_12_FLING_SPEED_MIN:
            return 5;
         case DATA_ID_10_SIMULTANEOUS_TIMEOUT:
            return 50;
         case DATA_ID_14_ALLER_RETOUR_SLOP:
            return 50;
         case DATA_ID_15_ALLER_RETOUR_MIN_AMPLITUDE:
            return 150;
         case DATA_ID_17_NUMBER_OF_SCREENS:
            return getNumOfScreens();
         case DATA_ID_18_SCREEN_ORIENTATION:
            //we only have one screen orientation in desktop
            return ITechHostUI.SCREEN_0_TOP_NORMAL;
         case DATA_ID_19_DRAG_CONTROLLED:
            return 1;
         case DATA_ID_20_DPI:
            return 0;
         case DATA_ID_23_KEYBOARD_TYPE:
            return ITechHostUI.KB_TYPE_1_FULL;
         case DATA_ID_24_KEY_REPEAT_TIMEOUT:
            return 100;
         case DATA_ID_25_KEY_REPEAT_DELAY:
            return 50;
           
         case DATA_ID_26_KEY_FAST_TYPE_TIMEOUT:
            return 100;
         case DATA_ID_27_KEY_NUPLE_TIMEOUT:
            return 80;
       
         default:
            return hostDataDrawJ2se.getHostDataInt(dataID);
      }
   }
   
   /**
    * Returns actual live screen config creates a new object.
    * <br>
    * Type is {@link ITypesCore#TYPE_007_LIT_ARRAY_INT}.
    * The first value being number of screens.
    * and then x,y,w,h tuple for each screens.
    * <br>
    * Thus. This config object does not represent screen positioning relative to each other.
    * @return
    */
   public ByteObject getScreenConfigLive() {
      GestureArea[] val = (GestureArea[]) getScreens();

      int[] ar = new int[1 + (val.length * 2)];
      ar[0] = val.length;
      int index = 1;
      for (int i = 0; i < val.length; i++) {
         ar[index++] = val[i].x;
         ar[index++] = val[i].y;
         ar[index++] = val[i].w;
         ar[index++] = val[i].h;
      }
      ByteObject bo = cuc.getBOC().getLitteralIntFactory().getIntArrayBO(ar);
      return bo;
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


   public Object getHostDataObject(int dataID) {
      switch (dataID) {
         case DATA_ID_OBJ_01_SCREENS:
            return getScreens();
         case DATA_ID_OBJ_02_SCREENCONFIG:
            return getScreenConfigLive();
         default:
            return hostDataDrawJ2se.getHostDataObject(dataID);
      }
   }

   public String getHostDataString(int dataID) {
      switch (dataID) {
         default:
            return hostDataDrawJ2se.getHostDataString(dataID);
      }
   }
}
