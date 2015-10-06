$(function() {
	$("#departDatepicker").datepicker();
	$("#returnDatepicker").datepicker();

});
function changeOccured() {
//	alert("coucou");
	$("#weekDiv").hide();
    $('#departDatepicker').datepicker( "option", "disabled", false );
    $('#returnDatepicker').datepicker( "option", "disabled", false );

	var option = $('#tripType option:selected');
	console.log("Triptype changed value = "
			+ option.val() + " text = "
			+ option.text());
	$('#returnDatepicker').datepicker("option", "showOn", "focus");
	$('#departDatepicker').datepicker("option", "showOn", "focus");

	$('#returnDateLine').show();

	$('.returnDateLabel').css('color', 'Black');
	$('.departDateLabel').css('color', 'Black');
	if (option.val() == 'RETURN') {
		$("#returnDatepicker").val('');
		$("#departDatepicker").val('');
		$("#week").val(0);
	} else {
		if (option.val() == 'ONE_WAY') {
			$('#returnDateLine').hide();
			$("#returnDatepicker").val('');
			$("#departDatepicker").val('');
		} else {
			$("#weekDiv").show();
		    $('#departDatepicker').datepicker( "option", "disabled", true );
		    $('#returnDatepicker').datepicker( "option", "disabled", true );
			$.getJSON('GetTravelDates', {
				"tripType" : option.val(),
				"week" : $("#week").val()
			},
			function(data) {
				console.log("return from GetTravelDates data = "
						+ data.departureDate+" - "+data.returnDate);
				$("#departDatepicker").val(data.departureDate);
				$("#returnDatepicker").val(data.returnDate);
				console.log("datepickers setted");
			});
			$('#departDatepicker').datepicker("option", "showOn", "button");
			$('#returnDatepicker').datepicker("option", "showOn", "button");
			$('.ui-datepicker-trigger').hide();
		}
	}
}
$(document).ready(
	function() {
		$('#tripType').change(function(){changeOccured()});
		$('#week').change(function(){changeOccured()});
	$('#tripType').trigger('change');
	loadAirportsOptionList();
	// voir http://www.sitepoint.com/basic-jquery-form-validation-tutorial/
	(function($,W,D)
			{
			    var JQUERY4U = {};
			 
			    JQUERY4U.UTIL =
			    {
			        setupFormValidation: function()
			        {
			            //form validation rules
			            $("#requestForm").validate({
			                rules: {
			                	departAirport: "required",
			                	arrivalAirport: "required",
			                	departDate : "required"
			                },
			                messages: {
			                	departAirport: "Please enter a departure airport",
			                	arrivalAirport: "Please enter an arrival airport",
			                    departDate: "Please enter a departure date"
			                },
			                submitHandler: function(form) {
			                    form.submit();
			                }
			            });
			        }
			    }
			 
			    //when the dom has loaded setup form validation rules
			    $(D).ready(function($) {
			        JQUERY4U.UTIL.setupFormValidation();
			    });
			 
			})(jQuery, window, document);
});
