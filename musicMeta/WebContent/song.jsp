<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@page import="com.user.musicMeta.bean.Artist"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
 -->
 
 <%-- <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
 --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" 
          href=
"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
 <script src=
"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js">
    </script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


 
<%-- <link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet"> --%>


<script>




$(document).ready(function(){
	var uploadField = document.getElementById("fileUpload");

	uploadField.onchange = function() {
	    if(this.files[0].size > 50000){
	       alert("File is too big!");
	       this.value = "";
	    };
	};

	
	 $(".datepicker").datepicker({
         clearBtn: true,
         format: "dd/mm/yyyy",
     });
     $(".datepicker").on("change", function() {
         let pickedDate = $("input").val();
         $("#showdate").text(
         `You picked this ${pickedDate} Date`);
     });
	var url="/musicMeta/listArtist";
	$.getJSON(url, function(data){
		 //alert("khvhjv");
	}); 

	

	$('.selectpicker').on('change', function (e) {
		
	  /*  var selectedval = e.target.value;
	    alert(selectedval); */
	    var allVal=$("#artistName").val();
	   // alert(allVal);
	    selector = document.getElementsByName('artst_id')[0];
	    element = $(selector);
	    element.val(allVal);
	    //alert("ddfs"+ element.val());
	});


	//$('select').selectpicker();
	var usr_id='<%=request.getAttribute("usr_id")%>'

	
	
	
	
	
/* 	$('#fileUpload').on('change',function ()
	        {
	            var filePath = $(this).val();
	            console.log(filePath);
	            var path = (window.URL || window.webkitURL).createObjectURL(filePath);
	            console.log('path', path);
	        }); */
	     
		  
		  $('#modelWindow').modal('hide');
		
	$('#MybtnModal').click(function(){
		//alert("hii");
		
		$('#modelWindow').modal('show');
	});
	$('#save').click(function() {
		  var artst_name = $("#artst_name").val();
		  var artst_dob= $("#dob").val();
		 
		  var artst_bio= $("#bio").val();
		  if(artst_name==""||artst_dob==""||artst_bio==""){
			  alert("Please fill all the fields!");
			  
		  }
		 
		  else{
		  
		  $ .ajax({
				type : "POST",
				url :"/musicMeta/addArtist"+"?artst_name=" +artst_name+"&artst_dob=" +artst_dob+"&artst_bio=" +artst_bio+"&usr_id="+usr_id,
				 async :false,
				 
				 
				success : function(data) {
					//alert(response);
					 alert("Success");
						},
				error : function(data) {
                 alert("faliure");
				}
			}); 
 
		  
		  
		  $('#modelWindow').modal('hide');
		  window.location.reload();}
		});
	
	
});




</script>



</script>
</head>
<body>
 <div class="container">
  <h2>Adding a new song:</h2>
  <div class="card">
   <div class="card-body">
    <form action="<%=request.getContextPath()%>/saveSong" method="post" enctype = "multipart/form-data">
 <input type="hidden" name="usr_id" value=<%=request.getAttribute("usr_id")%> />
 
 <input type="hidden" name="artst_id" > 
 
     <div class="form-group row">
      <label for="dateReleased" class="col-sm-2 col-form-label">Song Name</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="sng_name"
        placeholder="Enter Song Name" required>
      </div>
     </div>

     

     <div class=" form-group row">
     
      <label for="dateReleased" class="col-sm-2 col-form-label">Date Released</label>
      <div class="col-sm-7">
       <div class="datepicker date 
                    input-group p-0 shadow-sm">
            <input id="reservationDate" 
                   type="text"
                   placeholder="Choose a date" 
                   class="form-control py-4 px-4" name="sng_dor" required/>
            <div class="input-group-append"> 
              <span class="input-group-text px-4">
                  <i class="fa fa-clock-o"></i>
                </span> 
            </div>
        </div>
      </div>
     </div>
<div class="form-group row">
    <label for="exampleFormControlFile1" class="col-sm-2 col-form-label">Artwork</label>
    <div class="col-sm-7">
    <input type="file"  id ="fileUpload"class="form-control-file" accept=".jpg, .jpeg, .png" name="sng_cover" required>
    </div>
  </div>
  <div class="form-group row">
  <label for="artists" class="col-sm-2 col-form-label">Artists</label>
  <div class="col-sm-5" >
  
<select id="artistName" class="selectpicker" multiple data-live-search="true" required>
 <%List<Artist> listArtist =(List<Artist>)request.getAttribute("listArtist");
		  System.out.println(listArtist);
		  if(listArtist!=null)
		  for(Artist artist : listArtist) {
			  System.out.println(artist.getArtst_name());
 %>
 
  <option value='<%=artist.getArtst_id() %>'><%=artist.getArtst_name() %></option>
  <%} %>
 
  
</select>

</div>

<button  disabled class="btn btn-primary"><i class="fa fa-plus"></i><span  id="MybtnModal">Add Artist</span></button>
</div>
     
      <button type="submit" class="btn btn-primary">Cancel</button>
     <button type="submit" class="btn btn-primary">Save</button>
    </form>
   </div>
  </div>
 </div>
 
 
<div class="modal fade" id="modelWindow" >
            <div class="modal-dialog modal-lg vertical-align-center">
              <div class="modal-content">
                <div class="modal-header">
                 
                  <h4 class="modal-title">Artist Name</h4>
                </div>
              <div class="card">
   <div class="card-body">
                <div class="modal-body">
       
                     <div class="  form-group row">
      <label for="dateReleased" class="col-sm-3 col-form-label">Artist Name</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" id="artst_name" name="artistName"
    >
      </div>
     </div>
     <div class=" form-group row">
       <label for="dob" class="col-sm-3 col-form-label">Date Of Release</label>
      <div class="col-sm-7">
       
       <div class="datepicker date 
                    input-group p-0 shadow-sm">
            <input  
                   type="text"
                   placeholder="Choose a date" 
                   class="form-control py-4 px-4" id="dob"  />
            <div class="input-group-append"> 
              <span class="input-group-text px-4">
                  <i class="fa fa-clock-o"></i>
                </span> 
            </div>
      </div>
      </div>
      <div class="form-group row">
       <label for="bio" class="col-sm-3 col-form-label">Bio</label>
      <div class="col-sm-7">
       <textarea class="form-control" id="bio" ></textarea>
        
      </div>
      </div>
     
                    
                    
                    
                </div>
                </div>
                </div>
                <div class="modal-footer  mr-auto">
                 <button id="save"  class="btn btn-primary" type="button" name="save-details">Save</button>
  
                    <button type="button" data-dismiss="modal" class="btn btn-primary">Close</button>
                </div>
              </div>
            </div>
        </div>
</body>
</html>