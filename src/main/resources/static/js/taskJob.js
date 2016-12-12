
$(function(){
	 $("input[name='optionsRadiosinline']").click(function(){
		 var checkradio=$("input[name='optionsRadiosinline']:checked").val();
			if(checkradio==1){
				$("#web_div").show();
				$("#db_div").hide();
				$("#local_div").hide();
			}else if(checkradio==2){
				$("#web_div").hide();
				$("#db_div").show();
				$("#local_div").hide();
			}else if(checkradio==3){
				$("#web_div").hide();
				$("#db_div").hide();
				$("#local_div").show();
			}
	 });
});