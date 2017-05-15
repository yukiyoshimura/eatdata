package resource;
import java.util.List;

public abstract class AbstractResource<T> {
    
	  //画面から受け取ったリクエストをセットする
      public abstract List<T> setResource(List<T> beans);
      
}
