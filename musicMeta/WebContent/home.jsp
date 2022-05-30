<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@page import="com.user.musicMeta.bean.Artist"%>
<%@page import="com.user.musicMeta.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.user.musicMeta.bean.User"%>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"crossorigin="anonymous">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-star-rating/4.0.1/css/star-rating.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-star-rating/4.0.1/js/star-rating.min.js"></script>
<style>
  .checked {  
            color : yellow;  
            font-size : 20px;  
        }  
        .unchecked {  
            font-size : 20px;  
        }  
        
        .star-rating {
  font-size: 0;
  white-space: nowrap;
  display: inline-block;
  /* width: 250px; remove this */
  height: 50px;
  overflow: hidden;
  position: relative;
  background: url('data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB3aWR0aD0iMjBweCIgaGVpZ2h0PSIyMHB4IiB2aWV3Qm94PSIwIDAgMjAgMjAiIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDIwIDIwIiB4bWw6c3BhY2U9InByZXNlcnZlIj48cG9seWdvbiBmaWxsPSIjREREREREIiBwb2ludHM9IjEwLDAgMTMuMDksNi41ODMgMjAsNy42MzkgMTUsMTIuNzY0IDE2LjE4LDIwIDEwLDE2LjU4MyAzLjgyLDIwIDUsMTIuNzY0IDAsNy42MzkgNi45MSw2LjU4MyAiLz48L3N2Zz4=');
  background-size: contain;
}
.star-rating i {
  opacity: 0;
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  /* width: 20%; remove this */
  z-index: 1;
  background: url('data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB3aWR0aD0iMjBweCIgaGVpZ2h0PSIyMHB4IiB2aWV3Qm94PSIwIDAgMjAgMjAiIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDIwIDIwIiB4bWw6c3BhY2U9InByZXNlcnZlIj48cG9seWdvbiBmaWxsPSIjRkZERjg4IiBwb2ludHM9IjEwLDAgMTMuMDksNi41ODMgMjAsNy42MzkgMTUsMTIuNzY0IDE2LjE4LDIwIDEwLDE2LjU4MyAzLjgyLDIwIDUsMTIuNzY0IDAsNy42MzkgNi45MSw2LjU4MyAiLz48L3N2Zz4=');
  background-size: contain;
}
.star-rating input {
  -moz-appearance: none;
  -webkit-appearance: none;
  opacity: 0;
  display: inline-block;
  /* width: 20%; remove this */
  height: 100%;
  margin: 0;
  padding: 0;
  z-index: 2;
  position: relative;
}
.star-rating input:hover + i,
.star-rating input:checked + i {
  opacity: 1;
}
.star-rating i ~ i {
  width: 40%;
}
.star-rating i ~ i ~ i {
  width: 60%;
}
.star-rating i ~ i ~ i ~ i {
  width: 80%;
}
.star-rating i ~ i ~ i ~ i ~ i {
  width: 100%;
}
::after,
::before {
  height: 100%;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  text-align: center;
  vertical-align: middle;
}

.star-rating.star-5 {width: 250px;}
.star-rating.star-5 input,
.star-rating.star-5 i {width: 20%;}
.star-rating.star-5 i ~ i {width: 40%;}
.star-rating.star-5 i ~ i ~ i {width: 60%;}
.star-rating.star-5 i ~ i ~ i ~ i {width: 80%;}
.star-rating.star-5 i ~ i ~ i ~ i ~i {width: 100%;}


        
</style>
<script type="text/javascript">
function rate(ths){
	alert(ths.id);
	alert($("#"+ths.id).val());
	var rate =$("#"+ths.id).val();
	var arr=ths.id.split("-");
	<% User user=(User)request.getAttribute("user");
	int id=user.getUsr_id();
	%>
	var usr_id='<%=id%>';
	alert("usr_id"+usr_id);
	alert("sng_id"+arr[1]);
	alert("rate"+rate);
	  $ .ajax({
			type : "POST",
			url :"/musicMeta/updateRating"+"?usr_id=" +usr_id+"&sng_id=" +arr[1]+"&rating=" +rate,
			 async :false,
			 
			 
			 success : function(data) {
					//alert(response);
					 alert("Success");
						},
				error : function(data) {
	           alert("faliure");
				}
		});
	 
}
</script>
</head>
<body>
<header>
<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: grey">
			
				<a href="https://www.xadmin.net" class="navbar-brand"> Home </a>
	
			 <ul class="navbar-nav ml-auto">
				<li><a href="addsong?id=<c:out value='${user.usr_id}' />"
					class="nav-link">ADD SONGS</a></li>
			</ul> 
		</nav>
		</header>

  
  <div class="table-responsive">   
<table class="table " >
<thead>
<tr class="table-default"><th scope="col">Song Name</th><th scope="col">Date of release</th><th scope="col" >Cover</th><th scope="col" >Rating</th></tr>
</thead>
<%List<Song> topSongs =(List<Song>)request.getAttribute("topSongs");
System.out.println(topSongs);
if(topSongs!=null)
for(Song song : topSongs) {
	  System.out.println(song.getSng_name());

%>
<tbody>
    <tr>
    
        <td>  <%=song.getSng_name()%></td>
         <td>  <%=song.getSng_dor()%></td>
         <td> <img src="data:image/png;base64,<%=song.getSng_cov()%>" height="80px" width="80px"/></td>
       
       <td> 
       <span class="star-rating star-5">
  <input type="radio" name="<%="rating"+song.getSng_id()%>" id="<%="rating1-"+song.getSng_id()%>"  onclick="rate(this)" value="1"><i></i>
  <input type="radio" name="<%="rating"+song.getSng_id()%>" id="<%="rating2-"+song.getSng_id()%>"  onclick="rate(this)" value="2"><i></i>
  <input type="radio" name="<%="rating"+song.getSng_id()%>" id="<%="rating3-"+song.getSng_id()%>"  onclick="rate(this)" value="3"><i></i>
  <input type="radio" name="<%="rating"+song.getSng_id()%>" id="<%="rating4-"+song.getSng_id()%>"  onclick="rate(this)" value="4"><i></i>
  <input type="radio" name="<%="rating"+song.getSng_id()%>" id="<%="rating5-"+song.getSng_id()%>"  onclick="rate(this)" value="5"><i></i>
</span>	
       </td>
    
    
    
    <%} %>
    </tr>
</tbody>


</table>


</div></body></html>

