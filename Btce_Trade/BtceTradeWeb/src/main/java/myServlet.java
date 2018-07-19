/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.btcetrade.bitcoin.config.ObjectEncryption;
import com.btcetrade.bitcoin.config.TransactionFactory;
import com.btcetrade.bitcoin.config.TransactionForPaymentActivity;
import com.btcetrade.bitcoin.dbconfig.DBConfigurattion;
import com.btcetrade.bitcoin.model.VerifyPhoneNumber;

/**
 *
 * @author root
 */
@WebServlet(urlPatterns = {"/myServlet"})
public class myServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JavaIntegrationKit integrationKit = new JavaIntegrationKit();
        Map<String, String> values = integrationKit.hashCalMethod(request, response);
        PrintWriter writer = response.getWriter();
// build HTML code
        String surl="http://diittech.in/";
        String furl="http://diittech.in/";
        SessionFactory sessionFactory=new DBConfigurattion().getSessionFactory();
        String phoneNumber="9166001205";
        try{
        Session session=sessionFactory.openSession();
        VerifyPhoneNumber user= (VerifyPhoneNumber)session.createCriteria(VerifyPhoneNumber.class).add(Restrictions.eq("verifyNumber", phoneNumber)).uniqueResult();
        
        System.out.println(user.getEmailId());   
        values.put("email", user.getEmailId());
        values.put("firstname", "");
        values.put("phone", user.getVerifyNumber());
        values.put("", surl);
        values.put("", furl);
        values.put("productinfo", "BTC");
        
        }catch(HibernateException he){
        	he.printStackTrace();
        }
        
        
        ObjectEncryption encryptObject= new ObjectEncryption();
        if(values.get("txnid")==null){
        	TransactionForPaymentActivity tranForPaymentActivity=(TransactionForPaymentActivity) TransactionFactory.getTransactionInstance("payment");
        	
        	values.put("txnid", encryptObject.getDcrypt());
        
        }
        
        
        
        System.out.println(values.get("txnid"));
       
        String key="Rjv64D4M";
        System.out.println("Action"+values.get("action"));
        String htmlResponse = "<html> <body> \n"
                + "      \n"
                + "  \n"
                + "  <h1>BTCe-Trade Payment  </h1>\n"
                + "  \n" + "<div>"
                + "        <form id=\"payuform\" action=\"" + values.get("action") + "\"  name=\"payuform\" method=POST >\n"
                + "      <input type=\"hidden\" name=\"key\" value=" + key.trim() + ">"
                + "      <input type=\"hidden\" name=\"hash\" value=" + values.get("hash").trim() + ">"
                + "      <input type=\"hidden\" name=\"txnid\" value=" + values.get("txnid").trim() + ">"
                + "      <table>\n"
                + "        <tr>\n"
                + "          <td><b>Mandatory Parameters</b></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "         <td>Amount: </td>\n"
                + "          <td><input name=\"amount\" value=" + values.get("amount").trim() + " /></td>\n"
                + "          <td>First Name: </td>\n"
                + "          <td><input name=\"firstname\" id=\"firstname\" value=" + values.get("firstname").trim() + " /></td>\n"
                + "        <tr>\n"
                + "          <td>Email: </td>\n"
                + "          <td><input name=\"email\" id=\"email\" value=" + values.get("email").trim() + " /></td>\n"
                + "          <td>Phone: </td>\n"
                + "          <td><input name=\"phone\" value=" + values.get("phone") + " ></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Product Info: </td>\n"
                + "<td><input name=\"productinfo\" value=" + values.get("productinfo").trim() + " ></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Success URI: </td>\n"
                + "          <td colspan=\"3\"><input name=\"surl\"  size=\"64\" value=" + surl + "></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Failure URI: </td>\n"
                + "          <td colspan=\"3\"><input name=\"furl\" value=" + furl + " size=\"64\" ></td>\n"
                + "        </tr>\n"
                + "\n"
                + "        <tr>\n"
                + "          <td colspan=\"3\"><input type=\"hidden\" name=\"service_provider\" value=\"payu_paisa\" /></td>\n"
                + "        </tr>\n"
                + "             <tr>\n"
                + "          <td><b>Optional Parameters</b></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Last Name: </td>\n"
                + "          <td><input name=\"lastname\" id=\"lastname\" value=" + values.get("lastname") + " ></td>\n"
                + "          <td>Cancel URI: </td>\n"
                + "          <td><input name=\"curl\" value=" + values.get("curl") + " ></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Address1: </td>\n"
                + "          <td><input name=\"address1\" value=" + values.get("address1") + " ></td>\n"
                + "          <td>Address2: </td>\n"
                + "          <td><input name=\"address2\" value=" + values.get("address2") + " ></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>City: </td>\n"
                + "          <td><input name=\"city\" value=" + values.get("city") + "></td>\n"
                + "          <td>State: </td>\n"
                + "          <td><input name=\"state\" value=" + values.get("state") + "></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Country: </td>\n"
                + "          <td><input name=\"country\" value=" + values.get("country") + " ></td>\n"
                + "          <td>Zipcode: </td>\n"
                + "          <td><input name=\"zipcode\" value=" + values.get("zipcode") + " ></td>\n"
                + "        </tr>\n"
                + "          <td>UDF1: </td>\n"
                + "          <td><input name=\"udf1\" value=" + values.get("udf1") + "></td>\n"
                + "          <td>UDF2: </td>\n"
                + "          <td><input name=\"udf2\" value=" + values.get("udf2") + "></td>\n"
                + " <td><input name=\"hashString\" value=" + values.get("hashString") + "></td>\n"
                + "          <td>UDF3: </td>\n"
                + "          <td><input name=\"udf3\" value=" + values.get("udf3") + " ></td>\n"
                + "          <td>UDF4: </td>\n"
                + "          <td><input name=\"udf4\" value=" + values.get("udf4") + " ></td>\n"
                + "          <td>UDF5: </td>\n"
               + "          <td><input name=\"udf5\" value=" + values.get("udf5") + " ></td>\n"
                 + "          <td>PG: </td>\n"
               + "          <td><input name=\"pg\" value=" + values.get("pg") + " ></td>\n"
                + "        <td colspan=\"4\"><input type=\"submit\" value=\"Submit\"  /></td>\n"
                + "      \n"
                + "    \n"
                + "      </table>\n"
                + "    </form>\n"
                + " <script> "
                + " document.getElementById(\"payuform\").submit(); "
                + " </script> "
                + "       </div>   "
                + "  \n"
                + "  </body>\n"
                + "</html>";
// return response
        writer.println(htmlResponse);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
