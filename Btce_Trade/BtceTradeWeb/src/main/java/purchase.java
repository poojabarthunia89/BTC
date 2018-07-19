

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

*//**
 * Servlet implementation class purchase
 */
@WebServlet("/purchase")
public class purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public purchase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id=request.getParameter("razorpay_payment_id");
	System.out.println(id);
/*		try {
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_IXzNGVhHCNH2lv", "Dry6UnGJe8HV0fMW4dAvIYhm");
			Payment payment = razorpayClient.Payments.fetch(id);
			int amount = payment.get("amount");
			String paymentId = payment.get("id");
			Date createdAt = payment.get("created_at");
			
			System.out.println(amount+","+","+paymentId+","+createdAt);
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
*/		
		/*BTC
		System.out.println(id);*/
		doGet(request, response);
	}

}
