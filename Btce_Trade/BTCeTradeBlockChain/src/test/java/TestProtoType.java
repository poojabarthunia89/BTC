import junit.framework.Assert;

import org.junit.Test;

import com.btcetrade.prototype.TransactionCopy;
import com.btcetrade.prototype.TransactionPrototype;


public class TestProtoType {

	@Test
	public void testTransactionCopy(){
		TransactionCopy transactionMerchant= new TransactionCopy("1234", "$123#", "$4567#", (float)0.00000001);
		transactionMerchant.showRecord();
		TransactionCopy transactionClient=(TransactionCopy) transactionMerchant.getClone();
		transactionClient.showRecord();
		//Assert.assertEquals(transactionMerchant, transactionClient);
	}
}
