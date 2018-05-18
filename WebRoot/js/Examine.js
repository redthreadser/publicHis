$(document).ready(function() {
	$(".detail").click(function() {
		$("#content").empty();
		var p_number = $(this).prev().html();
		console.log(p_number);
		$.ajax({
	 		"url" : "examineAction?actionMethod=getPacienteInfo",
	 		"type" : "get",
	 		"data" : {
	 			"p_number":p_number,
	 			"cost_type" : 3 
	 		},
	 		"dataType" : "json",
	 		"success" : function(paciente) {
		 		console.log(paciente);
		 		$("#p_name").html(paciente["p_name"]);
		 		$("#p_age").html(paciente["p_age"]);
		 		$("#p_sex").html(paciente["p_sex"]);
		 		$("#d_name").html(paciente["d_name"]);
		 		//查询该患者对应的详细项目名称
		 		$.ajax({
			 		"url" : "doctorAction?actionMethod=selectExamineByP_number",
			 		"type" : "get",
			 		"data" : {
			 			"p_number" : p_number,
			 			"cost_type": 3
			 		},
			 		"dataType" : "json",
			 		"success" : function(examines) {
			 			console.log(examines);
			 			$.each(examines, function(index, obj){
			 				if (obj["ex_result"] == undefined) {
			 					obj["ex_result"] = "未检查";
			 				}
			 				var tr="<tr><td width=100>"+(index+1)+"</td> " +
			 						"<td class='pr_name'>"+obj["project_name"]+"</td>" +
			 						" <td class='sum_price' width=100.8>"+obj["sum_price"]+"</td>" +
			 						"<td width=100.8>"+obj["ex_result"]+"</td> " +
			 						"<td class = 'ex_state' width=100.8>"+obj["ex_state"]+"</td>" +
			 						"<td width=100.8> <button class = 'money' >缴费</button> <button class = 'b_examine' >检查</button></td>" +
			 						"<input type = 'hidden' class = 'pr_no' value = "+obj["id" ]+"></tr>";
			 				$("#content").append(tr);
			 			});
			 				//点击检查
			 				$(".b_examine").click(function(e){
			 					console.log("dddlll");
			 					//拿到所有的检查按钮
			 					$b_ex = $(".b_examine");
			 					//拿到所有的状态̬
			 					$ex_state = $(".ex_state");
			 					//拿到所有的的id
			 					var $pr_no = $(".pr_no");
			 					var $cur = $b_ex.index(this);
			 					var ex_id = $pr_no.eq($cur).val();
			 					console.log(ex_id);
			 					$.ajax({
			 				 		"url" : "examineAction?actionMethod=b_examine",
			 				 		"type" : "get",
			 				 		"data" : {
			 				 			"ex_id" : ex_id,
			 				 			 "cost_type" : 3
			 				 		},
			 				 		"dataType" : "text",
			 				 		"success" : function(success_info) {
			 				 			if (Number(success_info) == 1) {
			 				 				console.log(true);
			 				 				success_info = "检查成功";
			 				 				$ex_state.eq($cur).html("检查成功");
			 				 			}
			 				 			alert(success_info);
//			 				 			$b_ex.eq($cur).attr("disabled",true);
			 				 		}
			 					})
			 				})
			 				//点击缴费
			 				$(".money").click(function(){
			 					//拿到所有的缴费按钮
			 					$money = $(".money");
			 					$ex_state = $(".ex_state");
			 					$project_name = $(".pr_name");
			 					$sum_price = $(".sum_price");
			 					var $pr_no = $(".pr_no");
			 					var $cur = $money.index(this);
			 					var ex_id = $pr_no.eq($cur).val();
			 					console.log(ex_id);
			 					console.log( $project_name.eq($cur).html());
			 					$.ajax({
			 				 		"url" : "examineAction?actionMethod=ex_cost",
			 				 		"type" : "get",
			 				 		"data" : {
			 				 			"p_number" : p_number,
			 				 			"ex_id" : ex_id,
			 				 			"project_name" : $project_name.eq($cur).html(),
			 				 			"sum_price" :  $sum_price.eq($cur).html(),
			 				 			"cost_type": 3
			 				 		},
			 				 		"dataType" : "text",
			 				 		"success" : function(examines) {
			 				 			if (Number(examines) == 1) {
			 				 				console.log(true);
			 				 				examines = "缴费成功";
			 				 				$ex_state.eq($cur).html("已缴费");
			 				 			}
			 				 			alert(examines);
//			 				 			$money.eq($cur).attr("disabled",true);
			 				 		}
			 					})
			 				})
			 		}
		 		})
	 		}
	 		
	 	})
	})
	
	
	
	
})