import javax.ejb.*;
import java.util.concurrent.Future;

@Stateless
public class OrderBean {
   @Asynchronous
   public Future<Boolean> processOrderInWarehouse() {
      boolean orderSuccessful = false;
      // do the work, setting orderSuccessful=true
      new AsyncResult<Boolean>(orderSuccessful);
   }
}
