$(document).ready(function(){
			var count=0;
			$(".add").click(function(){
				count++;
				console.log(count);
				if(count%2!=0){
					$(".info").css('display','block');
				}else{
					$(".info").css('display','none');
				}			
			})
			$(".save").click(function(){
				$.ajax({
					 "url" : "managerAction?actionMethod=add_drug_info",
					 "type" : "post",
					 "data" : {
					 	"drug_name":$("#drug_name").val(),
					 	"model":$("#model").val(),
					 	"drug_product":$("#drug_product").val(),
					 	"drug_price":$("#drug_price").val(),
					 	"cost_type":$("#cost_type").val(),
					 	"key":$("#key").val(),
					 	"count":$("#count").val(),
					 },
					 "dataType" : "text",
					 "success" : function(info) {
					 	if(Number(info)==1){
					 		info="药品添加成功";
					 		alert(info);
					 	}
					 }
				})
			})
		})