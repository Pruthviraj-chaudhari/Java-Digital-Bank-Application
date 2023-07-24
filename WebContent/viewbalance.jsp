<%@ page import="com.Servlets.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank Dashboard - View Balance</title>
<link href="./styles.css" rel="stylesheet">
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet">
<!-- MDB -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css"
	rel="stylesheet">
</head>
<body>
	<section class="h-80 gradient-form" style="background-color: #eee;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-xl-10">
					<div class="card rounded-3 text-black">
						<div class="row g-0">
							<div class="col-lg-6">
								<div class="card-body p-md-5 mx-md-4">
									<div class="text-center">
										<img
											src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
											style="width: 185px;" alt="logo">
										<h4 class="mt-1 mb-5 pb-1">Bank Dashboard - View Balance</h4>
									</div>

									<div class="text-center">
										<p>
											Account Holder Name: <strong><%=User.getName()%></strong>
										</p>
										<p>
											Account Number: <strong><%=User.getAccNo()%></strong>
										</p>
										<p>Your current account balance is:</p>
										<h3>
											$ <strong><%=User.getBalance()%></strong>
										</h3>
										
										<a href="dashboard.jsp"><button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3 mt-4" type="button">Go to dashboard</button></a>
									</div>
									

								</div>
							</div>
							<div class="col-lg-6 d-flex align-items-center gradient-custom-2">
								<div class="text-white px-3 py-4 p-md-5 mx-md-4">
									<h4 class="mb-4">We are more than just a Bank</h4>
									<p class="small mb-0">At our Bank, we believe in providing
										exceptional financial services that go beyond traditional
										banking. Our commitment to excellence and customer
										satisfaction sets us apart from the rest. With a focus on
										innovation and personalized solutions, we strive to meet the
										unique needs of each and every client.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- MDB -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>
</body>
</html>
