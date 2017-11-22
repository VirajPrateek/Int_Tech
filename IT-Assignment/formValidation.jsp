<%@ page contentType="text/HTML"%>
<!DOCTYPE html>
<html>
<head>
	<title>Form Validation</title>
</head>
<body>
	<header>
		<h1>Data Validation</h1>
		<h3>(Using JSP)</h3>
	</header>
	<article>
		<form method="POST" action="formValidation.jsp">
			
 <table>
 <tr>
 <td>Name:</td> 
 <td>
 <input type="text" name="userName">
 </td>
 </tr>
 <tr>
 <td>Birth Date:</td>
 <td>
 <input type="text" name="birthDate">
 </td>
 <td>(Use format yyyy-mm-dd)</td>
 </tr>
 <tr>
 <td>Email Address:</td>
 <td>
 <input type="text" name="emailAddr">
 </td>
 <td>(Use format name@company.com)</td>
 </tr>
 <tr>
 <td>Gender:</td>
 <td>
 <input type="radio" name="gender" value="m"
checked>Male<br>
 <input type="radio" name="gender" value="f">Female
 </td>
 </tr>
 <tr>
 <td>Lucky number:</td>
 <td>
 <input type="text" name="luckyNumber">
 </td>
 <td>(A number between 1 and 100)</td>
 </tr>
 <tr>
 <td>Favorite Foods:</td>
 <td>
 <input type="checkbox" name="food" value="z">Pizza<br>
 <input type="checkbox" name="food" value="p">Pasta<br>
 <input type="checkbox" name="food" value="c">Chinese
 </td>
 </tr>
 <tr>
 <td colspan=2>
 <input type="submit" value="Send Data">
 </td>
 </tr>
 </table>	</form>
	</article>
	<footer>
		Code by: Kumar Prateek Viraj
	</footer>
</body>
<style type="text/css">
 	body{
 		text-align: center;
 	}
	header,footer{
		text-align: center;
		background-color: black;
		color: white;
	}
	input[type="text"], select{
		text-align: center;
		width: 200px;
		padding: 8px;
		margin-bottom: 5px;
	}
</style>
</html>