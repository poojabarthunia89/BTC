import junit.framework.Assert;

import org.junit.Test;

import com.btcetrade.bitcoin.BlockChainApiImpl;
import com.btcetrade.bitcoin.model.AddressSpent;
import com.btcetrade.bitcoin.model.BalanceData;
import com.btcetrade.bitcoin.model.BalanceForAddress;


public class GETBlanceTest {

	@Test
	public void getAddressSpentTest(){
		BlockChainApiImpl blance= new BlockChainApiImpl();
		AddressSpent address=blance.getAddressSpent("1AryFXVUqYrEUgmij4px4PMMue9jf71pTm", "BTC");
		Assert.assertEquals(address, address);
	}
	@Test
	public void testForBalance() throws Exception
	{
		BlockChainApiImpl balance= new BlockChainApiImpl();
		BalanceForAddress balnce= new BalanceForAddress();
		balnce.setStatus("success");
		BalanceData data= new BalanceData();
		data.setNetwork("BTC");
		
		data.setAddress("1AryFXVUqYrEUgmij4px4PMMue9jf71pTm");
		data.setConfirmedBalance("0.00000000");
		data.setUnconfirmed_balance("0.00000000");
		balnce.setBlance(data);
		BalanceForAddress actual=balance.getAddressBalance("1AryFXVUqYrEUgmij4px4PMMue9jf71pTm", "BTC", 1);
		System.out.println(actual.getStatus());
		System.out.println(actual.getBlance().getAddress());
		System.out.println(actual.getBlance().getNetwork());
		System.out.println(actual.getBlance().getConfirmedBalance());
		System.out.println(actual.getBlance().getUnconfirmed_balance());
		
		//Assert.assertEquals(actual,actual);
		
	}
	
}
