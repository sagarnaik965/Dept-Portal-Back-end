	$(document)
			.ready(
					function() {
						alert("milind");

						jQuery.validator.addMethod("blankvalid", function(
								value, element) {
							if (/^\S+$/.test(value)) {
								return true;
							} else {
								return false;
							}
							;
						}, "Licence key cannot contain blank space!");

						jQuery.validator.addMethod("validlicence", function(
								value, element) {
							if (/(^([a-zA-Z0-9-_]){8,80}$)/.test(value)) {
								return true;
							} else {
								return false;
							}
							;
						}, "Please enter valid Licence key!");

						var dateInput = document.getElementById('validTill');

						dateInput.value = '';

						$("#updateasalk")
								.validate(
										{
											rules : {
												licenseKey : {
													required : true,
													blankvalid : true,
													validlicence : true,

												},
												validTill : {
													required : true,
												}
											},
											messages : {
												licenseKey : {
													required : "Licence key cannot be blank!",

												},
												validTill : {
													required : "Date cannot be blank! ",

												},
											},
											errorElement : "span",
											errorPlacement : function(error,
													element) {
												// Add the `help-block` class to the error element
												console.log(error + " "
														+ element);
												//error.addClass( "help-block" );

												if (element.prop("type") === "checkbox") {
													error.insertAfter(element
															.parent("label"));
												} else {
													error.insertAfter(element);
												}
											},
											highlight : function(element,
													errorClass, validClass) {
												$(element).parents(".col-sm-5")
														.addClass("has-error")
														.removeClass(
																"has-success");
											},
											unhighlight : function(element,
													errorClass, validClass) {
												$(element)
														.parents(".col-sm-5")
														.addClass("has-success")
														.removeClass(
																"has-error");
											}
										});
					});

	$("input").focus(function() {
		//  alert("asdad");
		$("#e1").hide();
		$("#e2").hide();
	});
