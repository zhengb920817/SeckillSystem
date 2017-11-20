//存放秒杀页面交互逻辑js代码
var seckill = {
	URL : {

	},

	validatePhone : function(phone) {
		if (phone && phone.length == 11 && !isNaN(phone)) {
			return true;
		} else {
			return false;
		}
	},
	detail : {
		// 详情页初始化
		init : function(params) {
			// 手机验证和登录，计时交互
			// 在cookie中查找手机号
			var killPhone = $.cookie('killPhone');
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var seckillId = params['seckillId']
			if (!seckill.validatePhone(killPhone)) {
				var killPhoneModal = $('#killPhoneModal');
				killPhoneModal.modal({
					// 显示弹出层
					show : true,
					backdrop : 'static',
					keybord : false
				});
				
				$('#killPhoneBtn').click(function(){
					var inputPhone = $('killPhoneKey').val();
					if(seckill.validatePhone(inputPhone)){
						$.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
						window.location.reload();
					}
					else{
						$('#killPhoneMessage').hide().
							html('<label class="label label-danger">手机号错误！</label>').show(300);
					}
				});
			}
		}
	}
}