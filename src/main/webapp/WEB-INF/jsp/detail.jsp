<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>秒杀详情页</title>
<%@include file="common/head.jsp"%>
<!--  使用一下js会无法输入手机号 坑！
<script src="https://cdn.bootcss.com/jquery/2.0.0/jquery.min.js"></script>
<script
		src="https://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
-->
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 
<script
	src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
 -->
<script src="http://cdn.staticfile.org/jquery.countdown/2.2.0/jquery.countdown.js"></script>
<script
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="/resource/script/seckill.js" type="text/javascript"></script>
</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>${seckillDetail.name}</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<span class="glyphicon glyphicon-time"></span> <span
						class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>

	<div class="modal fade" id="killPhoneModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">请填写手机号</h4>
				</div>

				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span> 秒杀电话：
					</h3>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey"
								placeholder="填写手机号^o^" class="form-control">
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="killPhoneBtn">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


	<script type="text/javascript">
	$(function(){
    	seckill.detail.init({
        seckillId:${seckillDetail.seckillId},
        startTime:${seckillDetail.startTime.time},
        endTime:${seckillDetail.endTime.time}
    	});
	});
</script>
</body>
</html>