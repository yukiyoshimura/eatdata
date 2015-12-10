package resource;

import java.io.IOException;

import org.junit.Test;

import junit.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import responseBean.RegistResponseBean;

public class test1 {

	@Test
	public void parseJsonToBeanByJacksonTest_Normal() throws IOException {

		// 期待値
		RegistResponseBean expected = new RegistResponseBean();
		expected.setNo("100");
		expected.setName("JSON変換文字列テスト1");

		// Jacksonのマッパーを生成
		ObjectMapper mapper = new ObjectMapper();
 
		// JavaBeansオブジェクトをJSON文字列へ変換
		String jsonStr = mapper.writeValueAsString(expected);
		
		System.out.println(jsonStr);

		
		// 準備
//		String param = "{\"no\":\"102\",\"name\":\"JSON変換文字列テスト2\"}";
		String param = "{\"name\":\"test\",\"no\":\"notest\"}";
		RegistResponseBean bean = mapper.readValue(param, RegistResponseBean.class);
		
		System.out.println(bean.getName());
		System.out.println(bean.getNo());
		
		
		
	
	}
	
	
}
