package pasa.cbentley.framework.coreui.j2se.engine;

import pasa.cbentley.core.src4.thread.IBRunnable;
import pasa.cbentley.core.src4.thread.WorkerThread;
import pasa.cbentley.framework.coreui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.coreui.src4.engine.AbstractExecutor;

public abstract class J2SEThreader extends AbstractExecutor {
   private WorkerThread wt;

   public J2SEThreader(CoreUiJ2seCtx cuc) {
      super(cuc);
   }

   public WorkerThread getWT() {
      if (wt == null) {
         wt = new WorkerThread(cuc.getUCtx());
      }
      return wt;
   }

   public Object getThreadLocal(String key) {
      // TODO Auto-generated method stub
      return null;
   }

   public abstract void callSerially(Runnable run);

   public void processRunnable(IBRunnable r) {
      if (r.hasRunFlag(IBRunnable.FLAG_07_UI_THREAD)) {
         callSerially(r);
      } else {
         getWT().addToQueue(r);
      }
   }
}
