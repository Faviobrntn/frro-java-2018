<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../js/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../jquery-slim.min.js"><\/script>')</script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
 feather.replace()
</script>

<script src="https://momentjs.com/downloads/moment-with-locales.min.js"></script>
<script src="../js/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">      
    $(function () {
		$('.fechahora').datetimepicker({
            format: "DD/MM/YYYY HH:mm",
            locale: 'es',
            defaultDate: false,
            stepping: 5,
            sideBySide: true
        });
		
		$('.fecha').datetimepicker({
            format: "DD/MM/YYYY",
            locale: 'es',
            defaultDate: false,
            stepping: 5,
            sideBySide: true
        });
	});
</script>