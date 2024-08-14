package pasa.cbentley.framework.core.ui.j2se.engine;

import java.awt.Dimension;
import java.awt.Toolkit;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.IBOTypesBOC;
import pasa.cbentley.byteobjects.src4.stator.StatorBO;
import pasa.cbentley.byteobjects.src4.stator.StatorReaderBO;
import pasa.cbentley.byteobjects.src4.stator.StatorWriterBO;
import pasa.cbentley.core.src4.stator.ITechStator;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.core.src4.structs.IntToObjects;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.j2se.ctx.IConfigCoreUiJ2se;
import pasa.cbentley.framework.core.ui.src4.ctx.CoreUiCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.IConfigCoreUi;
import pasa.cbentley.framework.core.ui.src4.ctx.ObjectCUC;
import pasa.cbentley.framework.core.ui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.core.ui.src4.engine.WrapperAbstract;
import pasa.cbentley.framework.core.ui.src4.interfaces.ICanvasAppli;
import pasa.cbentley.framework.core.ui.src4.interfaces.ICanvasHost;
import pasa.cbentley.framework.core.ui.src4.tech.IBOCanvasHost;
import pasa.cbentley.framework.core.ui.src4.tech.IBOFramePos;
import pasa.cbentley.framework.core.ui.src4.tech.ITechHostUI;

public class ScreenManagerJ2se extends ObjectCUC implements ITechHostUI {

   public ScreenManagerJ2se(CoreUiJ2seCtx cuc) {
      super(cuc);
   }

   /**
    * Screen configuration
    */
   protected ByteObject screenConfigLoaded;

   public ByteObject getScreenConfigLoaded() {
      if (screenConfigLoaded == null) {
         subConfigLoad();
      }
      return screenConfigLoaded;
   }
   
   /**
    * Reads the Ctx settings for a sub byte object that equals current live config
    */
   public void subConfigLoad() {

      ByteObject bo = cuc.getBOCtxSettings();

      ByteObject liveScreenConfig = getScreenConfig();
      //get the configuration that matches the screens setup
      ByteObject[] bos = bo.getSubs();
      if (bos != null) {
         for (int i = 0; i < bos.length; i++) {
            ByteObject sb = bos[i];
            if (sb.equalsContent(liveScreenConfig)) {
               screenConfigLoaded = bos[i];
               break;
            }
         }
      }
      if (screenConfigLoaded == null) {
         screenConfigLoaded = liveScreenConfig; //live config without canvas parameters parameters
      }
   }
   
   public CoreUiJ2seCtx getCUCJ2se() {
      return (CoreUiJ2seCtx) cuc;
   }
   
   private ByteObject getScreenConfig() {
      CoreUiJ2seCtx cuc = getCoreUiJ2seCtx();
      HostDataUiJ2se hd = cuc.getHostDataUIJ2se();
      return hd.getScreenConfigLive();
   }

   /**
    * 
    * Creates a {@link IBOCanvasHost} if necessary
    * 
    * If null assume create a root host defautl with id 0
    * 
    * Else try in the ScreenConfig
    * 
    * Makes sure the {@link ByteObject} is valid with a {@link IBOFramePos}
    * When {@link IBOCanvasHost#TCANVAS_OFFSET_03_ID2} is zero.
    * This is ignored.
    * <br>
    * The xywh values defined in {@link IBOFramePos} are used CanvasTech is null or FramePos is null.
    * 
    * @param tech
    * @return
    */
   protected ByteObject loadValidNonNullTech(ByteObject tech, ByteObject screenConfigLoaded) {
      //load up the tech param
      if (tech == null) {
         //create the default tech with default ID. it will load ICON and Title from LaunchValues ?
         tech = cuc.createBOCanvasHostDefault();
      } else {
         //look up tech id in config
         int idInput = tech.get2(IBOCanvasHost.TCANVAS_OFFSET_03_ID2);
         if (idInput != 0) {
            //id was set previously
            if (screenConfigLoaded != null) {
               //#debug
               toDLog().pInit("Reading Config settings", screenConfigLoaded, this.getClass(), "loadValidNonNullTech");
               //read all the screen configuration for the CanvasTech matching the ID.
               ByteObject[] bos = screenConfigLoaded.getSubs();
               if (bos != null) {
                  //we have to find the canvas tech using the ID
                  for (int i = 0; i < bos.length; i++) {
                     if (bos[i].getType() == tech.getType()) {
                        int ids = bos[i].get2(IBOCanvasHost.TCANVAS_OFFSET_03_ID2);
                        if (ids == idInput) {
                           tech = bos[i]; //assign the found tech
                        }
                     }
                  }
               }
            }
         }
      }

      return tech;
   }


   /**
    * Populates the {@link IBOFramePos} with default {@link IConfigCoreUi}
    * and match those values with host capabilities
    * such as Width should not be bigger than screen size.
    * 
    * Some hosts will ignore {@link IBOFramePos} because its fullscreen or nothing.
    * 
    * Wrapper stuff. Only for Frame wrapper {@link WrapperAbstract}
    * @param framePos
    */
   public void setDefaultXYWH(ByteObject framePos) {
      framePos.checkType(IBOFramePos.FPOS_TYPE);
      IConfigCoreUiJ2se cfgUI = this.getCoreUiJ2seCtx().getConfigUIJ2se();
      
      int w = cfgUI.getDefaultCanvasW();
      int h = cfgUI.getDefaultCanvasH();
      int frameX = 10;
      int frameY = 10;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      if (w > screenSize.width) {
         w = screenSize.width;
      }
      if (h > screenSize.height) {
         h = screenSize.height;
      }
      frameX = screenSize.width / 2 - w / 2;
      frameY = screenSize.height / 2 - h / 2;
      framePos.set2(IBOFramePos.FPOS_OFFSET_02_X2, frameX);
      framePos.set2(IBOFramePos.FPOS_OFFSET_03_Y2, frameY);
      framePos.set2(IBOFramePos.FPOS_OFFSET_04_W2, w);
      framePos.set2(IBOFramePos.FPOS_OFFSET_05_H2, h);

   }
   
   private CoreUiJ2seCtx getCoreUiJ2seCtx() {
      return (CoreUiJ2seCtx) cuc;
   }

   public void setPosToCanvasHost(ByteObject tech) {
      //set starting values for non nulls
      ByteObject framePos = tech.getSubIndexed2(IBOCanvasHost.TCANVAS_OFFSET_14_FRAMEPOS2);
      if (framePos == null) {
         cuc.createBOFrameDefault();
         //set default size
         this.setDefaultXYWH(framePos);
         //set which ID? none. which means the canvas is not saved
         tech.setSubIndexed2(framePos, IBOCanvasHost.TCANVAS_OFFSET_14_FRAMEPOS2);
      } else {
         IConfigCoreUiJ2se cfgUI = this.getCoreUiJ2seCtx().getConfigUIJ2se();
         int w = cfgUI.getDefaultCanvasW();
         int h = cfgUI.getDefaultCanvasH();
         framePos.set2IfSmallerOrEqual(0, IBOFramePos.FPOS_OFFSET_04_W2, w);
         framePos.set2IfSmallerOrEqual(0, IBOFramePos.FPOS_OFFSET_05_H2, h);
      }
   }
   
   
   public void stateWriteTo(StatorWriter state) {
      if (state.getType() != ITechStator.TYPE_1_VIEW) {
         throw new IllegalArgumentException();
      }

      StatorWriterBO stator = (StatorWriterBO) state;
      StatorBO s = (StatorBO) stator.getStator();

      CanvasHostAbstract[] canvases = cuc.getCanvases();
      int numCanvases = canvases.length;

      ///GOAL here is to write framepositions associated with screenconfiguration key
      //this is out key. because we want our screen config
      //to decide the state configuration of the HostCanvas to start with
      ByteObject screenConfig = getScreenConfig();
      //make sure its only the head
      screenConfig = screenConfig.cloneCopyHead();
      //on this writer, we only want to write canvas positions.. not all the state
      StatorWriterBO statorWriterScreenConfig = s.getStatorWriterKeyedTo(screenConfig, ITechStator.TYPE_1_VIEW);
      state.getWriter().writeInt(numCanvases);
      for (int i = 0; i < numCanvases; i++) {
         CanvasHostAbstract ch = canvases[i];
         ByteObject boCanvasHost = ch.getBOCanvasHost();
         boCanvasHost = boCanvasHost.cloneCopyHead();
         ByteObject pos = ch.getFramePosNewWithXYWH();
         boCanvasHost.addByteObject(pos);
         screenConfig.addByteObject(boCanvasHost);
      }
      //write the full config with all canvashosts and positions
      statorWriterScreenConfig.writeByteObject(screenConfig);
      ///////// END of writing 

      //Now write our canvas in the main StatorWriter
      stator.getWriter().writeInt(numCanvases);
      for (int i = 0; i < numCanvases; i++) {
         CanvasHostAbstract ch = canvases[i];
         stator.writerToStatorable(ch);
      }

      //the caller/coordinator decides the key(screen config) of this state if any 
      //our job is to write our state. period. 

      //asks host ui context to saves state of canvases?
      //they may have some specific data to save

      //we don't want j2me to handle any such state. only state of main canvas
      //delegate the ui host state read/write to the uictx

      //.the application is designed to not know if it has several canvas.
      //debug area: in a single screen app, it is shown with a command ShowDebug
      //debug area: in a multiple screen app, it is shown with a command ShowDebug first
      // then the user moves it to another frame provided the application tags the View as "independant".
      // you do not want to do that with wizard pages.
      // The Show cmd provides options when hosts support several screens.
      //
      //
      // is that possible? We have Screen/Fragment/MyGui that are displayed in a Canvas.
      // by default, its over the other.. on j2se the user may want in a new canvas on a given screen
      // so we have to associate "Visiblity Action", depending on the host 
      // on a single screen, it would be as if the single canvas was divided in 2,3,4 sub areas
      // show replace, show on top, show new screen, system provides Alt tab inside the app
      // relation between perspective and state? 
      // ui perspective changes how the content is displayed
      // model perspective changes the content

      //that's why the UI state is decided by the CanvasHosts. An app can decide to behave outside the
      //paradigm described but then it has to access the CanvasHosts to learn how its own CanvasApplis

      //once canvases have been raised from their graves

      //the question is.. what kind of model state do we have? if its just a screen ID (maps the class name)..
      // the app code will create blank canvas type and that's it.

   }
   /**
    * Save the current Canvas configuration for the screen current setup.
    * <br>
    * makes sure the origin of each inside area is inside a container.
    * 
    * each canvas host has a unique ID to identify it
    * @param bo {@link ByteObject} of type {@link IBOTypesBOC#TYPE_012_CTX_SETTINGS}
    */
   public void setScreenConfigToCtxSettings(ByteObject bo) {
      //#debug
      bo.checkType(IBOTypesBOC.TYPE_012_CTX_SETTINGS);

      ByteObject screenConfig = getScreenConfig();
      //#debug
      toDLog().pInit("Screen Config ", screenConfig, ScreenManagerJ2se.class, "setScreenConfigToCtxSettings");

      CanvasHostAbstract[] canvases = cuc.getCanvases();

      for (int i = 0; i < canvases.length; i++) {
         CanvasHostAbstract ch = canvases[i];
         ByteObject canvasTech = ch.getBOCanvasHost();
         ByteObject framePos = cuc.createBOFrameDefault();
         ch.setXYWHToFramePos(framePos);
         boolean isFullScreen = ch.isCanvasFeatureEnabled(SUP_ID_27_FULLSCREEN);
         framePos.setFlag(IBOFramePos.FPOS_OFFSET_01_FLAG, IBOFramePos.FPOS_FLAG_1_FULLSCREEN, isFullScreen);

         //get current screen?
         canvasTech.setSubIndexed2(framePos, IBOCanvasHost.TCANVAS_OFFSET_14_FRAMEPOS2);

         //state of the canvas content?

         //#debug
         toDLog().pInit("CanvasTech #" + i, canvasTech, ScreenManagerJ2se.class, "setScreenConfigToCtxSettings");

         screenConfig.addByteObject(canvasTech);
      }
      //save current screen config
      ByteObject[] bos = bo.getSubs();
      boolean wasAdded = false;
      if (bos != null) {
         for (int i = 0; i < bos.length; i++) {
            ByteObject sb = bos[i];
            if (sb.equalsContent(screenConfig)) {
               bos[i] = screenConfig;
               wasAdded = true;
               break;
            }
         }
      }
      if (!wasAdded) {
         //add it
         bo.addSub(screenConfig);
      }

      //#debug
      toDLog().pInit("Settings of J2SE Module", bo, ScreenManagerJ2se.class, "setScreenConfigToCtxSettings");

   }
}
