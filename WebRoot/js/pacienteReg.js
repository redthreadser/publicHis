$(document).ready(function(){
// 			电话号码填写完写入病历号
			function randomMath(){
				var num = "";
				for(var i = 0; i <10; i++){
					num += Math.floor(Math.random()*10); 
				}
				$(".p_number input").val(num);
			}
			randomMath();
// 			$(".p_tel input").blur(function(){
// 				var p_tel = $(this).val();
// 				$(".p_number input").val(p_tel);
// 			});
// 			科室选择完失去焦点进入后台选择该科室的医生
			$("#department").blur(function(){
				var department_id = $(this).val();
				$.ajax({
					url:"registerAction?actionMethod=showDepDoctor",
					type:"post",
					data:{"department_id":department_id},
					dataType:"json",
					success:function(result){
				 		$("#doctor").empty();
						$.each(result,function(index,obj){
							$("#doctor").append(
								"<option value='"+obj["doctor_id"]+"'>"+obj["d_name"]+"</option>"
							);
						});
					},
	 			});
			});
// 			医生人数上限
			$("#doctor").blur(function(){
				var doctor_id = $(this).val();
				$.ajax({
					url:"registerAction?actionMethod=showDoctorCount",
					type:"post",
					data:{"doctor_id":doctor_id},
					dataType:"json",
					success:function(count){
						console.log(count);
						if(count == 50){
							$("#submit").attr("disabled", true);	
							$(".count").html("该医生挂号人数已满，请选其他医生！");
						}else{
							$("#submit").attr("disabled", false);	
							$(".count").html("");
						}
					},
				});
			});
			function successAlert(){
				 var success = $(".success").val();
				if(success != ""){
					alert("您已挂号成功！");
				}
			}
			successAlert();
//			/*
//			 * 零点医生挂号人数清空
//			 */
//			function time(){
//				var zeroTime = new Date();
//				zeroTime.setSeconds(zeroTime.getSeconds()+20);
//				zeroTime.setMinutes(zeroTime.getMinutes());
//				zeroTime.setHours(zeroTime.getHours());
//				var zero = zeroTime.getHours().toString()+":" + zeroTime.getMinutes().toString()+":"+zeroTime.getSeconds();
//				console.log(zero);
//				var time = window.setInterval(function(){
//					var nowTime = new Date();
//					var now = nowTime.getHours().toString()+":" + nowTime.getMinutes().toString()+":"+nowTime.getSeconds();
//					console.log(now);
//					if(now == zero){
//						console.log("success");
//						$.ajax({
//							url:"registerAction?actionMethod=clearCount",
//							type:"post",
//							dataType:"json",
//							success:function(result){
//								if(result > 0){
//									window.location.reload();
//									clearInterval(time);
//								}
//							},
//						});
//					}
//				}, 1000); 
//			}
//			time();
});