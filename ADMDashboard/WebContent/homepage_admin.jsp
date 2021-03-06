<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
       
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  	   <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com">
	
	
  <title>Admin Dashboard ADM</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
 
  
    <script src="https://apis.google.com/js/platform.js" async defer>
	 </script>
	

</head>

<body>

  <!--  Scripts-->
  <!--Import jQuery before materialize.js-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script>
    function signOut() {
	    gapi.load('auth2', function() {
        gapi.auth2.init();
      });
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
		window.location.href = "index.html";
      });
    }

    function onLoad() {
      gapi.load('auth2', function() {
        gapi.auth2.init();
      });
    }
         $(document).ready(function() {
         $('select').material_select();
      });
     

     
  </script>

  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
  <header>
<!-- START OF NAVBAR --> 
 <!--<div class="navbar-fixed">
 <nav class = "green">
    <div class="nav-wrapper">
   
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li> <a href="#" onclick="signOut();"><i class="material-icons">settings</i>Settings/Sign Out</a></li>
		  </ul>
    </div>
  </nav>
</div> -->

    <nav class="top-nav">
        <div class="container">
            <div class="nav-wrapper">
                    <a class="page-title">Post-Acts Deadlines</a>
            </div>
        </div>    
    </nav>
      
    <div class="container">
        <a class="button-collapse top-nav full hide-on-large-only" href="#" data-activates="slide-out">
            <i class="material-icons medium">menu</i>
        </a>  
    </div>

 <ul id="slide-out" class="side-nav fixed" style="width: 300px; transfor: translateX(-100%);">
     <li>
        <div class="userView">
			   <a href="#!user"><img class="circle" src="images/cso.jpg"></a>
			   <a href="#!name"><span class="name">ORG NAME(org image above) </span></a>
			   <a href="#!email"><span class="email">orgname@gmail.com</span></a>
			 </div>
    </li>
			<li><a href="homepage admin.html"><i class="material-icons">dashboard</i>Dashboard</a></li>
						<li><div class="divider"></div></li>
			<li><a href="calendar.html"><i class="material-icons">today</i>Calendar of special deadlines</a></li>
		  <li><a href="organizations.html"><i class="material-icons">view_list</i>List of Organizations</a></li>
      </ul>
		  
        <!-- END OF NAVBAR -->
    </header>
<main>
    <div class="container">
<!-- START OF MAIN VIEW( Body) -->
    <div class="row">
        <form class="col s12">
          <div class="row">
            <div class="input-field col s6 l3 offset-s6">
              <i class="material-icons prefix">search</i>
              <input id="icon_prefix" type="text" class="validate">
              <label for="icon_prefix">Search</label>
            </div>
          </div>
        </form>
    </div>
    <!-- TABLE -->
    <div class="row">
      <table class = "bordered">
        <thead>
          <tr>
              <th data-field="orgCode">Organization</th>
              <th data-field="eventName">Event</th>
              <th data-field="eventDeadline">Deadline</th>
              <th data-field="editDeadline">Edit</th>
              <th data-field="eventStatus">Status</th>
          </tr>
        </thead>

        <tbody>
            <!-- loop START -->
          <tr>
            <td>ORG NAME</td>
            <td>Event Name</td>
            <td>11-30-2016</td>
            <td>
                <button data-target="modal2" class="btn modal-trigger btn-floating waves-effect waves-light tooltipped" data-position="bottom" data-delay="50" data-tooltip="Edit Event Deadline"><i class="material-icons right">mode_edit</i></button>
            <div id="modal2" class="modal">
              <div class="modal-content">
                    <h4>Event Name</h4><br>
                    Org Name<br>
                    Deadline 
                    <div class="row">
                        <div class="">
                            <input type="date" class="datepicker">
                        </div>
                    </div>
              </div>
              <div class="modal-footer">
                <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat green-text text-darken-3">SAVE</a>
                <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat red-text">CANCEL</a>
              </div>
            </div>
              </td>
              <td>
              
              <i class="small material-icons" style="color:red; font-size: 2.6rem;">warning</i></td></td>
          </tr>
            <!-- loop END -->
          <tr>
            <td>ORG NAME</td>
            <td>Event Name</td>
            <td>01-31-2017</td>
            <td>
                <button data-target="modal2" class="btn modal-trigger btn-floating waves-effect waves-light tooltipped" data-position="bottom" data-delay="50" data-tooltip="Edit Event Deadline"><i class="material-icons right">mode_edit</i></button>
            <div id="modal2" class="modal">
              <div class="modal-content">
                    <h4>Event Name</h4><br>
                    Org Name<br>
                    Deadline 
                    <div class="row">
                        <div class="">
                            <input type="date" class="datepicker">
                        </div>
                    </div>
              </div>
              <div class="modal-footer">
                <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat green-text text-darken-3">SAVE</a>
                <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat red-text">CANCEL</a>
              </div>
            </div>
              </td>
              <td>
              
              <i class="small material-icons" style="; font-size: 2.6rem;">info_outline</i></td></td>
          </tr><tr>
            <td>ORG NAME</td>
            <td>Event Name</td>
            <td>01-31-2017</td>
            <td>
                <button data-target="modal2" class="btn modal-trigger btn-floating waves-effect waves-light tooltipped" data-position="bottom" data-delay="50" data-tooltip="Edit Event Deadline"><i class="material-icons right">mode_edit</i></button>
            <div id="modal2" class="modal">
              <div class="modal-content">
                    <h4>Event Name</h4><br>
                    Org Name<br>
                    Deadline 
                    <div class="row">
                        <div class="">
                            <input type="date" class="datepicker">
                        </div>
                    </div>
              </div>
              <div class="modal-footer">
                <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat green-text text-darken-3">SAVE</a>
                <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat red-text">CANCEL</a>
              </div>
            </div>
              </td>
              <td>
              <i class="small material-icons" style="color:orange; font-size: 2.6rem;">error_outline</i></td></td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- END OF TABLE -->
	
<!-- END OF MAIN VIEW (Body) -->
    </div>
    </main>
 <script>
 // Initialize collapse button
  $(".button-collapse").sideNav();
  // Initialize collapsible (uncomment the line below if you use the dropdown variation)
  //$('.collapsible').collapsible();
</script>
<script >
  $(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();
  });
</script>

</body>
</html>