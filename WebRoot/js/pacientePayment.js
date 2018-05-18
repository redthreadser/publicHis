$(document).ready(function() {
	$("#pNumber").blur(function() {
		var p_number = $(this).val();
		$.ajax({
			url : "registerAction?actionMethod=showPaciente",
			type : "post",
			data : {
				"p_number" : p_number
			},
			dataType : "json",
			success : function(p) {
				console.log(p);
				$("#p_name").html(p["p_name"]);
				$("#p_sex").html(p["p_sex"]);
				$("#p_age").html(p["p_age"]);
				$("#p_tel").html(p["p_tel"]);
				$("#extraMoney").val(p["extraMoney"]);
			},

		});
	});
	function successAlert() {
		var success = $(".success").val();
		if (success != "") {
			alert("您已缴费成功！");
		}
	}
	successAlert();
})