<%@ page import="com.Servlets.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank Dashboard</title>
<link href="./styles.css" rel="stylesheet">
<!-- Font Awesome -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
  rel="stylesheet"
/>
<!-- Google Fonts -->
<link
  href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
  rel="stylesheet"
/>
<!-- MDB -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css"
  rel="stylesheet"
/>
</head>
<body>
	<section class="h-90 gradient-form" style="background-color: #eee; ">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-10">
        <div class="card rounded-3 text-black">
          <div class="row g-0">
            <div class="col-lg-6">
              <div class="card-body p-md-5 mx-md-4">
                <h4 class="mb-4" style="color: #dd3675" >R3sys Digital Bank</h4>

                <div class="text-center pb-4">
                  <a href="./deposite.html"><button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button">Deposit</button></a>
                  <a href="./withdraw.html"><button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button">Withdraw</button></a>
                  <a href="./viewbalance.jsp"><button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button">View Balance</button></a>
                  <a href="./moneytransfer.html"><button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button">Money Transfer</button></a>
                </div>
              </div>
            </div>
            <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
              <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                <h3 class="mb-4">Welcome</h3>
                <% String username = User.getName(); %>
                <h1 class="mb-4"> <%= username %> !</h1>
                <p class="small mb-0">With a focus on innovation and cutting-edge technology, 
                we provide you with convenient and secure banking solutions. Whether you're managing your accounts, 
                making transactions, or exploring investment opportunities, our goal is to empower you to achieve 
                your financial goals. Trust us to be your partner in your financial journey.</p></p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- MDB -->
<script
  type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"
></script>
</body>
</html>
