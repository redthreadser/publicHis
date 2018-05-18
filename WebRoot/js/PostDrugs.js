$(document).ready(function() {
	//点击将患者的信息填充到到右侧详细信息栏
	$(".detail").click(function() {
		$("#content").empty();
		var p_number = $(this).prev().html();
		console.log(p_number);
		$.ajax({
	 		"url" : "examineAction?actionMethod=getPacienteInfo",
	 		"type" : "get",
	 		"data" : {
	 			"p_number": p_number,
	 			 "cost_type": 2
	 		},
	 		"dataType" : "json",
	 		"success" : function(paciente) {
		 		console.log(paciente);
		 		$("#p_name").html(paciente["p_name"]);
		 		$("#p_age").html(paciente["p_age"]);
		 		$("#p_sex").html(paciente["p_sex"]);
		 		$("#d_name").html(paciente["d_name"]);
		 		//将患者的详细药品信息查询出来
		 		$.ajax({
			 		"url" : "doctorAction?actionMethod=selectExamineByP_number",
			 		"type" : "get",
			 		"data" : {
			 			"p_number" : p_number,
			 			"cost_type": 2
			 		},
			 		"dataType" : "json",
			 		"success" : function(examines) {
			 			console.log(examines);
			 			$.each(examines, function(index, obj){
			 				var tr="<tr><td width=114.4>"+(index+1)+"</td> " +
			 						"<td class='pr_name' width=226.4>"+obj["project_name"]+"</td> " +
			 						"<td class = 'dr_number' width=114.4>"+obj["number"]+"</td>"+
			 						"<td class='sum_price' width=114.4>"+obj["sum_price"].toFixed(2)+"</td>  " +
			 						"<td class = 'ex_state' width=114.4>"+obj["ex_state"]+"</td>" +
			 						"<td  width=114.4> <button class = 'money'>缴费</button><button class = 'b_examine' >发药</button></td>" +
			 						"<input type = 'hidden' class = 'pr_no' value = "+obj["id" ]+"></tr>";
			 				$("#content").append(tr);
			 			});
			 				//点击发药
			 				$(".b_examine").click(function(e){
			 					//拿到所有的发药按钮
			 					$b_ex = $(".b_examine");
			 					//拿到所有的状态̬
			 					$ex_state = $(".ex_state");
			 					//拿到所有的的id
			 					var $pr_no = $(".pr_no");
			 					//拿到该患者的药品的数量
			 					var $dr_nubmer = $(".dr_number");
			 					//拿到所有的药品名字
			 					var $drug_name = $(".pr_name");
			 					var cur = $b_ex.index(this);
			 					var ex_id = $pr_no.eq(cur).val();
			 					console.log(ex_id);
			 					$.ajax({
			 				 		"url" : "examineAction?actionMethod=b_examine",
			 				 		"type" : "get",
			 				 		"data" : {
			 				 			"cost_type": 2,
			 				 			"ex_id" : ex_id,
			 				 			"drug_name": $drug_name.eq(cur).html(),
			 				 			"dr_count" : $dr_nubmer.eq(cur).html()
			 				 		},
			 				 		"dataType" : "text",
			 				 		"success" : function(success_info) {
			 				 			if (Number(success_info) == 1) {
			 				 				console.log(true);
			 				 				success_info = "发药成功";
			 				 				$ex_state.eq(cur).html("发药成功");
			 				 			}
			 				 			alert(success_info);
//			 				 			$b_ex.eq(cur).attr("disabled",true);
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
			 				 			"cost_type": 2
			 				 		},
			 				 		"dataType" : "text",
			 				 		"success" : function(examines) {
			 				 			console.log(Number(examines));
			 				 			if (Number(examines) == 1) {
			 				 				console.log(true);
			 				 				examines = "缴费成功";
			 				 				$ex_state.eq($cur).html("缴费成功");
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