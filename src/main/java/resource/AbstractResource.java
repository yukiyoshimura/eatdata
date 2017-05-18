package resource;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractResource<T> {
    
	  static final Logger logger = LoggerFactory.getLogger("resource");
	  //画面から受け取ったリクエストをセットする
      public abstract List<T> setResource(List<T> beans);
      
}
