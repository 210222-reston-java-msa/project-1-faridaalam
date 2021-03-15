<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Employee Reimbursement System - Sign Up</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">

<style>
.jumbotron{margin-bottom: 0px !important;}

</style>
</head>
<body>
	<div class="jumbotron jumbotron-fluid bg-info">

		<div class="container bg-info text-white">
			<h1 class="display-5">Employee Reimbursement System</h1>
			<p class="lead">Edit Profile</p>

		</div>
	</div>



	<nav class="navbar navbar-expand-lg navbar-dark bg-info">
		<div class="container">
			<a class="navbar-brand" href="#"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarsExample07" aria-controls="navbarsExample07"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarsExample07">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="home.html">Home <span class="sr-only">(current)</span></a></li>
					
					<!--   <li class="nav-item">
              <a class="nav-link disabled" href="#">Disabled</a>
            </li> -->
				</ul>
				<ul class="navbar-nav ">
					<li class="nav-item active dropdown nav-right"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown07"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
						<div class="dropdown-menu" aria-labelledby="dropdown07">
							<a class="dropdown-item" href="#">Edit Profile</a> <a
								class="dropdown-item" id="logOut" href="#">Log out</a>
							<!--   <a class="dropdown-item" href="#">Something else here</a> -->
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<form name="loginForm">
			<!--  onSubmit=sendLogin())> sendLogin is a JS function -->
			<!-- we can add an event listener here to trigger a javascript function -->

			<div class="form-group text-info">
				<label for="firstname"> First Name </label>
				<!--  note that the name of the input matches to our loginServlet logic -->
				<input type="text" class="form-control" name="firstname" id="firstname"
					placeholder="Enter First Name" value= "" required> 
			</div>
		
			<div class="form-group text-info">
				<label for="lastname"> Last Name </label>
				<!--  note that the name of the input matches to our loginServlet logic -->
				<input type="Last Name" class="form-control" name="lastname" id="lastname"
					placeholder="Enter Last Name" value = "" required>
			</div>

			<div class="form-group text-info">
				<label for="email"> Email </label>
				<!--  note that the name of the input matches to our loginServlet logic -->
				<input type="email" class="form-control" name="email" id="email"
					placeholder="Enter Email" value = "" required disabled>
			</div>

			<div class="form-group text-info">
				<label for="pWord">Enter Your password to update profile</label>
				<!--  note that the name of the input matches to our loginServlet logic -->
				<input type="password" class="form-control" name="password"
					id="pWord" placeholder="Enter your old Password" required>
			</div>
					<div class="form-group text-info">
				<label for="newpWord">Enter a new Password to change</label>
				
				<input type="password" class="form-control" name="password"
					id="newpWord1" placeholder="Enter a new Password" required>
			</div>
					<div class="form-group text-info">
				<label for="pWord">Confirm your new password</label>
				<!--  note that the name of the input matches to our loginServlet logic -->
				<input type="password" class="form-control" name="password"
					id="newpWord2" placeholder="Confirm your password" required>
			</div>


			<button type="button" id="clickMe" class="btn btn-info">Update</button>
			<span class="float-right"> 
			<button type="button" id="clickMe2" class="btn btn-outline-info">Cancel</button>
			</span>
			<div id="warningText" class="text-danger"></div>
		</form>

	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="scripts/edit-profile.js"></script>
</body>
</html>