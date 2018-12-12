<%@page import="java.util.ArrayList, entity.Registro"%>
<% String[] tipos = Registro.tipos; %>
<% String[] estados = Registro.estados; %>
<% String colorIngreso = "#abe6ab"; String colorGasto = "#eaafaf"; %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Reportes | Importador</title>

    <!-- Bootstrap core CSS -->
    <link href="../styles/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../styles/dashboard.css" rel="stylesheet">
    
    <link href="../styles/bootstrap-datetimepicker.min.css" rel="stylesheet">
    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.9.0/fullcalendar.min.css" rel="stylesheet">
<!-- 	<link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.9.0/fullcalendar.print.css" rel="stylesheet"> -->
  </head>

  <body>
    <jsp:include page="/elementos/header.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
      
        <jsp:include page="/elementos/menu.jsp"></jsp:include>
        
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	            <h1 class="h2">Calendario de registros</h1>
	            <div class="btn-toolbar mb-2 mb-md-0">
	            </div>
	        </div>
	        <div class="row">
	        	<div class="col-sm-12 col-md-12 col-lg-12">
	        		<div id="calendar"></div>
	        	</div>

			</div>
        </main>
     </div>
    </div>
    <jsp:include page="/elementos/footer.jsp"></jsp:include>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.9.0/fullcalendar.min.js"></script>
    <script>
	    $(document).ready(function () {

	        $('#calendar').fullCalendar({
	            header: {
	                left: 'prev,next today',
	                center: 'title',
	                right: 'month,agendaWeek,agendaDay,listWeek'
	            },
	            //minTime: "00:00:00",
	            //maxTime: "23:30:00",
	            // O
	            defaultView: 'month', // month, agendaWeek, agendaDay, listWeek
	            allDaySlot: false,
	            hiddenDays: [], // Ocultar dias, los domingos = 0
	            locale: 'es',
	            //slotDuration: "00:45:00", // Duracion de los bloques
	            //defaultDate: '2018-12-12',
	            navLinks: true,
	            eventLimit: true,
	            events: {
	            	method: "post",
	                url: "../calendario",
	                error: function() {
	                    alert('Ocurrió un error al cargar el calendario.');
	                },
	            },
	            eventClick: function(calEvent, jsEvent, view) {
	                //console.info(calEvent);
	                // console.info(jsEvent);
	                // console.info(view);
	                // console.info('ID: '+calEvent.id);
	                // console.info('Titulo: ' + calEvent.title);
	                //console.info('Inicio: '+calEvent.start.format('DD-MM-YYYY HH:mm:ss'));
	                // console.info('Fin: '+calEvent.end.format('DD-MM-YYYY HH:mm:ss'));
	                // console.info('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
	                // console.info('View: ' + view.name);
	
	                var start = calEvent.start.format('YYYY-MM-DD HH:mm');
	                var hoy = new Date();
	                
	                
	               	alert(calEvent.title+"\n- Fecha: "+calEvent.start.format('DD-MM-YYYY')+"\n- Hora: "+calEvent.start.format('HH:mm')+"\n- Importe: $("+calEvent.importe+")");
	
	                // change the border color just for fun
	                // $(this).css('border-color', 'rgb(0, 168, 255)');
	                $(this).css('border-color', 'rgb(244, 244, 244)');
	
	            }
	        });
	
	    });
	</script>
	
  </body>
</html>
