$(document).ready(function() {
	$(".p_number input").blur(function() {
		var p_number = $(this).val();
		console.log(p_number);
		$.ajax({
			url:"registerAction?actionMethod=showCost",
			type:"post",
			data:{"p_number":p_number},
			dataType:"json",
			success:function(result){
				$(".content").empty();
				$.each(result,function(index,obj){
					$(".name").html(obj["p_name"]);
					$(".content").append(
						"<tr><td class='project'>"+obj["project_name"]+"</td>" +
				    		"<td class='price'>"+obj["pr_price"]+"</td>" +
				    		"<td class='time'>"+obj["cost_time"]+"</td>" +
				    		"<td class='option'>"+obj["option_name"]+"</td></tr>"
					);
				});
			}
		});
	});
});