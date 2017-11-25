<!DOCTYPE html>
<html>
<head>
	<title>JSP</title>
	<font size="5" color="red">
		<marquee width='850'> The classpaths must be set for executing custom tags!
			The folder directory must be intact and java files must be compiled duly.
		</marquee></font>
</head>
<body>
<div id="container">
	<header>
		<h1>Internet Technologies</h1>
		<h3>Javaserver Pages</h3>
	</header>
	<nav>
			<a href="q1.jsp" target="output"><pre>1. Display the pattern</pre></a>
			<a href="main.html" target="output">2. Caluculator</a> <br/>		
			<a href="q3.jsp" target="output">3. Validation</a> <br/>
			<a href="q4.jsp" target="output">4. Greeting</a> <br/>
			<a href="q5.jsp" target="output">5. Even - Odd</a> <br/>
			<a href="q61.jsp" target="output">6. Custom Tags</a> <br/>
			<a href="q7.jsp" target="output">7. Custom Substring Tag</a> <br/>
			<a href="q9.jsp" target="output">7. Custom Date Tag</a> <br/>
			<a href="q10.jsp" target="output">7. Shopping</a> <br/>
	</nav>
	<article>
		<iframe name="output" src="myimage.png"></iframe>
	</article>
	<footer>
		Code by : Kumar Prateek Viraj
 	</footer>
</div>
</body>
<style type="text/css">
	body{
			text-align: center;
	}
nav{
		float: left;
		max-width: 170px;
		margin-top: 50px;
		padding: 1em;
		font-size: 25px;
	}
a{
	font-size: 20px;
	text-decoration: none;
}
	article{
		min-height: 490px;
		margin-left: 380px;
	    border-left: 1px solid gray;
	    padding: 0.2em;
	    overflow: hidden;
	    
	}
	article iframe{
		float: right;
		min-width: 100%;
		min-height: 450px;
		border: none;
	}
	header,footer{
		color: #fff;
		background-color: black;
	}
</style>
</html>