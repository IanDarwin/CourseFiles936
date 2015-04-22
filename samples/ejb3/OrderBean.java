import javax.ejb.*;
import java.util.concurrent.Future;

@Stateless
public class OrderBean {
   @Asychronous
   public Future<Boolean> processOrderInWarehouse() {
      boolean orderSuccessful = false;
      // do the work
      return orderSuccessful;
   }
}
