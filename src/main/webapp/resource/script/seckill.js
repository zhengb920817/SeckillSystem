var seckill = {
	URL : {
		now:function(){
			return '/seckill/time/now';
		},

		exposer:function(seckillId){
			return '/seckill/' + seckillId + '/exposer';
		},
		
		excution: function(seckillId,md5){
			return '/seckill/' + seckillId + '/' + md5 + '/excution'; 
		}
	},

	validatePhone : function(phone) {
		if (phone && phone.length == 11 && !isNaN(phone)) {
			return true;
		} else {
			return false;
		}
	},
	
	handleSeckillKill: function(seckillId, node) {
		//处理秒杀逻辑
		node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
		$.post(seckill.URL.exposer(seckillId), {}, function(result){
			if(result && result['success']){
				var exposer = result['data'];
				if(exposer['exposed']){
					//开启秒杀
					var md5 = exposer['md5'];
					var killUrl = seckill.URL.excution(seckillId,md5);
					console.log('killurl: ' + killUrl);
					$('#killBtn').one('click',function(){
						//执行秒杀请求操作
						//先禁用按钮
						$(this).addClass('disabled');
						//发送秒杀请求
						$.post(killUrl, {}, function(result){		
							console.log('秒杀请求返回结果：' + result['data']);
							if(result && result['success']){
								var killResult = result['data'];
								console.log('killResult:' + killResult);
								var stateInfo = killResult['stateInfo'];
								console.log('stateInfo' + stateInfo);
								//显示秒杀结果
								node.html('<span class="label label-success">'+ stateInfo +'</span>');
							}
							else{
								console.log('秒杀请求失败：' + result);
							}
						});
					})
					
					node.show();
				}
				else{
					//未开启秒杀
					var now = exposer['nowTime'];
					var startTime = exposer['startTime'];
					var entTime = exposer['endTime'];
					seckill.countdown(seckillId, now, startTime, endTime);
				}
			}
			else{
				console.log('result:'+result);
			}
		})
	},
	
	countdown:function(seckillId,nowTime,startTime,endTime){
		var seckillBox = $('#seckill-box');
		//时间判断
		if(nowTime > endTime){
			seckillBox.html('秒杀结束');
		}
		else if(nowTime < startTime){
			//秒杀未开始
			var killTime = new Date(startTime + 1000);
			seckillBox.countdown(killTime, function(event){
				var format = event.strftime('秒杀计时：%D天 %H时 %M分 %S秒');
				seckillBox.html(format);
			}).on('finish.countdown',function(){
				alert('秒杀开始');
				seckill.handleSeckillKill(seckillId,seckillBox);
			})
		}
		else{
			//秒杀开始
			seckill.handleSeckillKill(seckillId,seckillBox);
		}
	},
	
	detail : {
		init : function(params) {
			var killPhone = $.cookie('killPhone');
			
			if (!seckill.validatePhone(killPhone)) {
				var killPhoneModal = $('#killPhoneModal');
				killPhoneModal.modal({
					show : true,
					backdrop : 'static',
					keyboard : false
				});
				$('#killPhoneBtn').click(function(){
					var inputPhone = $('#killPhoneKey').val();
					if(seckill.validatePhone(inputPhone)){
						$.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
						window.location.reload();
					}
					else{
						$('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
					}
				});
			}
			
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var seckillId = params['seckillId'];
			//已经登录
			//计时交互
			$.get(seckill.URL.now(),{},function(result){
				if(result && result['success']){
					var nowTime = result['data'];
					seckill.countdown(seckillId,nowTime,startTime,endTime);
				} 
				else{
					console.log('result:' + result);
				}
			})
			
		}
	}
}