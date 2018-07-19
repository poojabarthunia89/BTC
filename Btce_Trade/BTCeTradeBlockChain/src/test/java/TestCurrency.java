import junit.framework.Assert;

import org.junit.Test;

import com.btcetrade.factory.CurrencyChangeFactory;
import com.btcetrade.factory.ICurrency;


public class TestCurrency {

	//79837.5390625,1250.97998046875
	@Test
	public void testCurrency(){
		CurrencyChangeFactory factoy=new CurrencyChangeFactory();
		ICurrency currency=factoy.getCurrecyININRORUSD("INR");
		//Assert.assertEquals((float)79837.54,currency.changeCurrency((float)1250.98));
		
		
	}
	
	
}
