<form action="purchase" method="POST">
<!-- Note that the amount is in paise = 50 INR -->
<script
    src="https://checkout.razorpay.com/v1/checkout.js"
    data-key="rzp_test_IXzNGVhHCNH2lv"
    data-amount="1000"
    data-buttontext="Pay with Razorpay"
    data-name="Pawan Barthunia"
    data-description="Purchase Description"
    data-image="https://your-awesome-site.com/your_logo.jpg"
    data-prefill.name="Harshil Mathur"
    data-prefill.email="support@razorpay.com"
    data-theme.color="#F37254"
></script>
<input type="hidden" value="Hidden Element" name="hidden">
</form>