$(document).ready(function() {
		//点击加载病人详情信息
	$(".p_number").click(function() {
		var p_number = $(this).html();
		//console.log(typeof($());
		$.ajax({
	 		"url" : "doctorAction?actionMethod=selelctAllInfo",
	 		"type" : "post",
	 		"data" : "p_number="+p_number,
	 		"dataType" : "json",
	 		"success" : function(p) {
	 			console.log(p);
	 			$("#p_number").val(p["p_number"]);
	 			$("#p_name").val(p["p_name"]);
	 			$("#p_sex").val(p["p_sex"]);
	 			$("#p_age").val(p["p_age"]);
	 			$("#p_tel").val(p["p_tel"]);
	 		}
	 	})
	})
//点击删除病人
	$(".del_pct").on('click',function(){
		var $del_pct = $(".del_pct");
		var $p_number = $(".p_number");
		var cur = $del_pct.index(this);
		console.log(cur);
		$.ajax({
			"url" : "doctorAction?actionMethod=delPaciente",
			"type" : "post",
			"data" :{
				"p_number" : $p_number.eq(cur).html()
			},
			"dataType" : "text",
			"success" : function(del_pct){
				alert(del_pct);
			}
		})
	})
	//提交病人病历信息
	$("#submit").click(function() {
		console.log("fdfd");
		$.ajax({
	 		"url" : "doctorAction?actionMethod=insertRecord",
	 		"type" : "post",
	 		"data" : {
	 			"introduce":$("#introduce").val(),
	 			"illness" : $("#illness").val(),
	 			"detail" : $("#detail").val(),
	 			"agree" : $("#agree").val(),
	 			"p_number": $("#p_number").val()
	 		},
	 		"dataType" : "text",
	 		"success" : function(p) {
	 			alert(p);
	 			$("#submit").attr("disabled",true);
	 		}
	 	})
	})
	//更新病历
	$("#update").on('click',function(){
		$.ajax({
	 		"url" : "doctorAction?actionMethod=updateRecord",
	 		"type" : "post",
	 		"data" : {
	 			"introduce":$("#introduce").val(),
	 			"illness" : $("#illness").val(),
	 			"detail" : $("#detail").val(),
	 			"agree" : $("#agree").val()
	 		},
	 		"dataType" : "text",
	 		"success" : function(p) {
	 			alert(p);
	 		}
	 	})
	})
	//搜索药品
	$(".drug_name_1").keyup(function() {
		var key = $(this).val();
		if (key.trim().length > 0) {
			console.log(key);
			$.ajax({
		 		"url" : "doctorAction?actionMethod=selectDrugs",
		 		"type" : "post",
		 		"data" : "key="+key,
		 		"dataType" : "json",
		 		"success" : function(p) {
		 			$(".content").empty();
		 			$.each(p, function(index, obj){
		 				if (index >= 5) {
		 					return;
		 				}
		 				$(".content").append("<li class = 'search'>" + obj["drug_name"] 
		 				+ "</li><input type = 'hidden' class='dr_id' value="+obj["id"]+" >");
		 				
		 			})
		 			$(".search").click(function() {
		 				var all = $(this).next().val();
		 				console.log(all);
		 				var drug_name = $(this).html();
		 				$(".drug_name_1").val(drug_name);
		 				$(".content").empty();
		 				//填充药品后面信息
		 				$.ajax({
					 		"url" : "doctorAction?actionMethod=selectDrugByDr_id",
					 		"type" : "post",
					 		"data" : "dr_id="+all,
					 		"dataType" : "json",
					 		"success" : function(p) {
					 			$(".price_1").val(p["drug_price"]);
					 			$(".adress_1").val(p["drug_product"]);
					 			$(".model_1").val(p["model"]);
					 			$(".sum_1").val(p["drug_price"] * parseInt($(".number_1").val()));
					 			//当鼠标移开时计算药品的价格
					 			$(".number_1").blur(function() {
					 				console.log($(".number_1").val());
					 				$(".sum_1").val(p["drug_price"] * parseInt($(".number_1").val()));
					 			})
					 		}
		 				})
		 			})
		 		}
		 	})
		}
		//鼠标移开时div详情框消失
//		$(".drug_name_1").on('blur', function(){
//			setTimeout(function(){
//				console.log($(this).keyup());
//				if (!$(".drug_name_1").keyup()) {
//					
//					$(".content").hide();
//				} else {
//					$(".content").show();
//				}
//			}, 100)
//		})
		
		
		
		
		
		//鼠标移开药品搜索栏时，底下的详情框隐藏
//				$(".drug_name_1").{
//					console.log(e);
//					$(".content").css("display", "none");
//				})
//				console.log(m);
//				sum = parseInt($(".price_1").val()) * $(".number_1");
//	 			$(".sum_1").val(sum);
	})
	//增加一行
//			var count = 1;
//			$("#btn").click(function() {
//				count ++;
//				$("#table").after("<tr><td>" +count +"</td> <td>" +
//						"<input type = 'text' class = 'drug_name'"+" id = 'first_d'/>" +
//					"<div class = 'content'></div></td>" 
//	   				+"<td><input type = 'text' class = 'price_1'/></td>"
//	   				+"<td><input type = 'text' class ='model_1'/></td>" 
//	   				+"<td><input type = 'text' class = 'adress_1'/></td>"
//	   				+"<td><input type = 'text' class = 'number_1' id = 'first_num' value = 1></td>" 
//	   				+"<td><input type = 'text' class = 'sum_1'/></td>"
//	   				+"</tr><tr>")
//			})
	//保存药品，将药品信息传到后台
	$("#dr_btn").click(function() {
		var p_number = $("#p_number").val();
		if (p_number == null || p_number == "") {
			alert("病历号不能为空");
			return;
		}
		//插入该患者的药品到数据库
		$.ajax({
	 		"url" : "doctorAction?actionMethod=insertPDrug",
	 		"type" : "post",
	 		"data" : {
	 			"p_number" : p_number,
	 			"drug_name" : $(".drug_name_1").val(),
	 			"drug_number" : $(".number_1").val(),
	 			"drug_sum" : $(".sum_1").val(),
	 			"drug_product" : $(".adress_1").val(),
	 			"drug_price" : $(".price_1").val(),
	 			"model" : $(".model_1").val(),
	 			"cost_type": 2
	 		},
	 		"dataType" : "text",
	 		"success" : function(p) {
	 			//显示药品保存成功消息
	 			if (Number(p) == 1) {
		 				p = "该药数量不足，请换用其他药物";
		 				alert(p);
		 				return;
		 		}
	 			//清空搜索栏
	 			$(".drug_name_1").val("");
	 			$(".price_1").val("");
	 			$(".adress_1").val("");
	 			$(".model_1").val("");
	 			$(".sum_1").val("");
	 			$(".number_1").val(1);
	 			$("#d_drug").empty();
	 			show_drug();
	 		}
		})
	})
	//点击查看，显示该患者的所有药品
	$("#showAllDrug").on('click', function(){
		show_drug();
	})
	//查询出所有已经保存的药品
	function show_drug(){
		$("#d_drug").empty();
		$.ajax({
	 		"url" : "doctorAction?actionMethod=selectExamineByP_number",
	 		"type" : "post",
	 		"data" : {
	 			"p_number" : $("#p_number").val(),
	 			"cost_type": 2
	 		},
	 		"dataType" : "JSON",
	 		"success" : function(drug) {
	 			$.each(drug, function(index, obj) {
	 				$("#d_drug").append("<tr class = 'show'><td>"+(index+1)+"</td><td>"+obj["project_name"]+"</td> <td>"
	 						+obj["price"]+"</td> <td>"+obj["model"]+"</td> <td>"+obj["add_content"]+"</td> <td>"
	 						+obj["number"]+"</td> <td>"+obj["sum_price"].toFixed(2)+"</td>" +
	 						"<td><button class = 'delete_drug'>delete</button></td>"
	 						+"<td ><input type = 'hidden' class = 'pr_no' value = "+obj["id" ]+"></td></tr>");
	 			})
	 			//点击删除已经开的药
	 			$(".delete_drug").on('click',function(){
	 				//拿到所有的删除按钮
	 				var $del = $(".delete_drug");
 					var $pr_no = $(".pr_no");
 					var cur = $del.index(this);
 					var ex_id = $pr_no.eq(cur).val();
 					console.log(cur +" >>>> "+ex_id);
 					$.ajax({
 				 		"url" : "doctorAction?actionMethod=deleleExamine",
 				 		"type" : "post",
 				 		"data" : "ex_id="+ex_id,
 				 		"dataType" : "text",
 				 		"success" : function(del_info) {
 				 			alert(del_info);
 				 			show_drug();
 				 		}
 				 	}) 
 				 })           
	 		}
		})
		
	}
	
	//查询检查信息
	$("#ex_name").keyup(function() {
		var key = $(this).val();
		console.log(key);
		if (key.trim().length > 0) {
			$.ajax({
		 		"url" : "doctorAction?actionMethod=selectProjects",
		 		"type" : "post",
		 		"data" : "key="+key,
		 		"dataType" : "json",
		 		"success" : function(p) {
		 			console.log(p);
//				 			$(".content_2").empty();
		 			$.each(p, function(index, obj){
		 				//控制搜索框的数量为5个
		 				if (index >= 5) {
		 					return;
		 				}
		 				$(".content_2").append("<li class = 'search_2'><span class = 'span'>" + obj["project_name"] + "</span>" +
		 						"<input type = 'hidden' class = 'hid' value = "+obj["pr_price"]+"></li>");
		 			})
		 			//点击填充点击的检查到搜索栏
		 			$(".search_2").click(function() {
		 				//点击拿到所点击的li的值，即检查的全称
		 				var all = $(this).children(".span").html(); 
		 				//拿到点击药品的编号
		 				var ex_pr = $(this).children(".hid").val();
		 				//将价格填充到价格栏
		 				$("#ex_price").val(ex_pr);
//				 				console.log(all);
		 				//将检查项目的名称填充到检查框
		 				$("#ex_name").val(all);
		 				$(".content_2").empty();
		 			})
		 		}
		 	})
		}
	})
	//将检查项目传到后台
	$("#ex_btn").on('click',function() {
		console.log("were");
		$.ajax({
	 		"url" : "doctorAction?actionMethod=insertProject",
	 		"type" : "post",
	 		"data" : {
	 			"p_number" : $("#p_number").val(),
	 			"ex_name" : $("#ex_name").val(),
	 			"ex_price" : $("#ex_price").val(),
	 			"cost_type": 3
	 		},
	 		"dataType" : "text",
	 		"success" : function(p) {
	 			alert(p);
	 			//清空搜索框
//			 			 $("#p_number").val("");
	 			 $("#ex_name").val("");
	 			 $("#ex_price").val("");
//			 			$.ajax({
//					 		"url" : "doctorAction?actionMethod=selectExamineByP_number",
//					 		"type" : "get",
//					 		"data" : {
//					 			"p_number" : $("#p_number").val(),
//					 			"cost_type": 3
//					 		},
//					 		"dataType" : "JSON",
//					 		"success" : function(exss) {
//					 			console.log("ex= " +exss);
//					 			$.each(exss, function(index, obj) {
//					 				
//					 				
////					 				$("#ex_table").append("<tr class = 'show'><td>"+(index+1)+"</td><td class = 'pr_name'>"+obj["project_name"]+"</td> <td>"
////					 					+obj["price"]+"</td>" +
////					 						"<td><input type = 'hidden' class = 'pr_no' value = "+obj["id" ]+"></td></tr>" );
////									
//					 				$("#ex_table").append("<tr class = 'show'><td>"+(index+1)+"</td><td class = 'pr_name'>"+obj["project_name"]+"</td> <td>"
//						 					+obj["price"]+"</td><td><button class = 'del_ex_btn'>delete</button></td>" +
//						 					"<td><input type = 'hidden' class = 'pr_no' value = "+obj["id" ]+"></td></tr>" );
//					 			})
////					 				$("#show_ex_result").append("<tr class = 'show'><td>"+(index+1)+"</td><td class = 'pr_name'>"+obj["project_name"]+"</td> <td>"
////										 +obj["ex_result"]+"</td> <td class = 'ex_state'>"+obj["ex_state"]+"</td></tr>" );
//					 				//点击删除检查项目 
//					 				$(".del_ex_btn").click(function(){
//					 					var $del = $(".del_ex_btn");
//					 					var $pr_no = $(".pr_no");
//					 					var cur = $del.index(this);
//					 					var ex_id = $pr_no.eq(cur).val();
//					 					$.ajax({
//					 				 		"url" : "doctorAction?actionMethod=deleleExamine",
//					 				 		"type" : "get",
//					 				 		"data" : "ex_id="+ex_id,
//					 				 		"dataType" : "text",
//					 				 		"success" : function(del_info) {
//					 				 			alert(del_info);
//					 				 		}
//					 				 	}) 
//					 				 })           
//					 		}
//						})
	 			show_ex();
	 		}
		})
	})
//点击查看所有已经开检查单的项目
 $("#ex_butn").click(function(){
	 console.log("dddd");
	show_ex();
	 
 })
 //查看检查项目
 function show_ex(){
	//清空
	 $("#ex_detail").empty();
	 //向后台请求得到该患者的所有检查项目列表
	$.ajax({
 		"url" : "doctorAction?actionMethod=selectExamineByP_number",
 		"type" : "post",
 		"data" : {
 			"p_number" : $("#p_number").val(),
 			"cost_type": 3
 		},
 		"dataType" : "JSON",
 		"success" : function(exss) {
 			console.log("ex= " +exss);
 			$.each(exss, function(index, obj) {
 				$("#ex_detail").append("<tr class = 'show'>" +
 						"<td width=204>"+(index+1)+"</td>" +
 						"<td class = 'pr_name' width=425>"+obj["project_name"]+"</td> "+
 					"<td width=50>"+obj["price"]+"</td>" +
 					"<td width=70><input type = 'button' class = 'del_ex_btn' value = 'delete'/></td>" +
 						"<td ><input type = 'hidden' class = 'pr_no' value = "+obj["id" ]+"></td></tr>" );
//					$("#show_ex_result").append("<tr class = 'show'><td>"+(index+1)+"</td><td class = 'pr_name'>"+obj["project_name"]+"</td> <td>"
//						 +obj["ex_result"]+"</td> <td class = 'ex_state'>"+obj["ex_state"]+"</td></tr>" );
 			})	
 				//点击删除检查项目 
 				$(".del_ex_btn").click(function(){
 					var $del = $(".del_ex_btn");
 					var $pr_no = $(".pr_no");
 					var cur = $del.index(this);
 					var ex_id = $pr_no.eq(cur).val();
 					$.ajax({
 				 		"url" : "doctorAction?actionMethod=deleleExamine",
 				 		"type" : "post",
 				 		"data" : "ex_id="+ex_id,
 				 		"dataType" : "text",
 				 		"success" : function(del_info) {
 				 			alert(del_info);
 				 			show_ex();
 				 		}
 				 	}) 
 				 })           
 		}
	})
}


	
	//底部导航栏
	$(function(){
		var $btn = $("#button_content .butt");
		var $b_content = $(".b_content");
		$("#button_content button").click(function(){
			console.log("dfd");
			var cur_index = $btn.index(this);
			console.log(cur_index);
//					$b_content.eq(cur_index).fadeIn().siblings().fadeOut();
			$b_content.eq(cur_index).css('display','block');
			$btn.eq(cur_index).addClass("button_brg");
			$b_content.eq(cur_index).siblings().css('display','none');
			$btn.eq(cur_index).siblings().removeClass("button_brg");
			if (cur_index == 2) {
				console.log("dfdf");
				$("#show_ex_result").empty();
				$.ajax({
			 		"url" : "doctorAction?actionMethod=selectExamineByP_number",
			 		"type" : "post",
			 		"data" : {
			 			"p_number" : $("#p_number").val(),
			 			"cost_type": 3
			 		},
			 		"dataType" : "JSON",
			 		"success" : function(exmm) {
			 			console.log("ex= " +exmm);
			 			$.each(exmm, function(index, obj) {
			 				if (obj["ex_result"] == undefined) {
			 					obj["ex_result"] = "未检查";
			 				}
		 					$("#show_ex_result").append("<tr class = 'show'><td>"+(index+1)+"</td><td class = 'pr_name'>"+obj["project_name"]+"</td> <td>"
		 						+obj["ex_result"]+"</td> <td class = 'ex_state'>"+obj["ex_state"]+"</td></tr>" );
			 			})
			 		}
	 			})
			}
//					$btn.eq(cur_index).addClass('show_ex');
//					$btn.eq(cur_index).removeClass('show_ex');
		})
	})
	
})