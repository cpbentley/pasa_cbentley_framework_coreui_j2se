package pasa.cbentley.framework.core.ui.j2se.ctx;

import pasa.cbentley.framework.core.ui.src4.ctx.IConfigCoreUi;

/**
 * 
 * @author Charles Bentley
 *
 */
public interface IConfigCoreUiJ2se extends IConfigCoreUi {

   /**
    * Override computed value
    * @return
    */
   public int getDefaultCanvasW();

   public int getDefaultCanvasH();
   
   public int getDefaultCanvasX();

   public int getDefaultCanvasY();
   
   /**
    * When True, ignored config X and Y
    * @return
    */
   public boolean isCenterPosition();
   
   /**
    * When true, ignores config w and h.
    * 
    * Every new canvas will have its size computed based on half the screen size.
    * @return
    */
   public boolean isRatioSize();
   
   /**
    * The type of keyboard mapping to use.
    * <li>regular one to one
    * <li>phone base emulation
    * @return
    */
   public int getKeyMappingTypeJ2se();
}
