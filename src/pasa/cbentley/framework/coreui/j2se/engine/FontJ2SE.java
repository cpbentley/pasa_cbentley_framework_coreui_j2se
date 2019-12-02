package pasa.cbentley.framework.coreui.j2se.engine;

import pasa.cbentley.framework.coreui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.coreui.src4.engine.FontAbstract;
import pasa.cbentley.framework.coreui.src4.interfaces.IMFont;
import pasa.cbentley.framework.src4.host.LaunchValues;

public abstract class FontJ2SE extends FontAbstract {

   protected int    face, style, size;

   protected String font_name;

   protected int    points;

   protected int    fontWidth = 0;

   public FontJ2SE(CoreUiJ2seCtx jcac, int face, int style, int size) {
      super(jcac);
      this.face = face;
      this.style = style;
      this.size = size;

      //get which font to use.. use the default ont
      LaunchValues lv = jcac.getHOC().getLaunchValues();
      if (face == IMFont.FACE_MONOSPACE) {
         font_name = lv.fontNameMonoSpace;
      } else if (face == IMFont.FACE_PROPORTIONAL) {
         font_name = lv.fontNameProportional;
      } else {
         font_name = lv.fontNameSystem;
      }

      //when not in the j2me choice. take it as it is.
      int[] pointsa = cac.getFontFactory().getFontPoints();
      switch (size) {
         case IMFont.SIZE_SMALL:
            points = pointsa[2];
            break;
         case IMFont.SIZE_MEDIUM:
            points = pointsa[1];
            break;
         case IMFont.SIZE_LARGE:
            points = pointsa[0];
            break;
         case IMFont.SIZE_VERY_SMALL:
            points = pointsa[3];
            break;
         case IMFont.SIZE_VERY_LARGE:
            points = pointsa[4];
            break;
      }

      points += lv.fontPointsExtraShift;
   }

   @Override
   public int getWidthWeigh() {
      if (fontWidth != 0) {
         fontWidth = stringWidth("m");
      }
      return fontWidth;
   }
}
