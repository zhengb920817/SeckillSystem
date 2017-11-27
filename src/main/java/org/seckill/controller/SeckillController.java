package org.seckill.controller;

import java.util.Date;
import java.util.List;

import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillExposer;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.SecKill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seckill") // url:/模块/资源/{id}/细分 /seckill/list
public class SeckillController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	/*
	 * 获取秒杀商品列表
	 */
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String getSeckillList(Model model) {

		List<SecKill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}

	/*
	 * 获取产品详情
	 */
	@RequestMapping(path = "/{seckillId}/detail", method = RequestMethod.GET)
	public String getDetail(@PathVariable("seckillId") Long seckillId, Model model) {

		if (seckillId == null) {
			return "redirect:/seckill/list";
		}

		SecKill seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}

		model.addAttribute("seckillDetail", seckill);

		return "detail";
	}

	/*
	 * 获取秒杀地址
	 */
	@RequestMapping(path = "/{seckillId}/exposer", method = RequestMethod.POST, 
			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public SeckillResult<SeckillExposer> exposer(@PathVariable("seckillId") Long seckillId) {
		SeckillResult<SeckillExposer> result = null;
		try {
			SeckillExposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<SeckillExposer>(true, exposer);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			result = new SeckillResult<SeckillExposer>(false, e.getMessage());
		}

		return result;
	}

	/*
	 * 执行秒杀
	 */
	@RequestMapping(path = "/{seckillId}/{md5}/excution", method = {RequestMethod.POST,RequestMethod.GET},
			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public SeckillResult<SeckillExecution> excuteSeckill(
			@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, 
			@CookieValue(value = "killPhone", required = false) Long userPhone) {
		
		if(userPhone == null) {
			return new SeckillResult<SeckillExecution>(true, "未注册");
		}
		
		try {
			//SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
			SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, execution);
		} 
		catch(RepeatKillException e1) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
		catch(SeckillCloseException e2) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
	}
	
	/*
	 * 获取系统时间
	 */
	@RequestMapping(path = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> getServerTime(){
		Date nowTime = new Date();
		return new SeckillResult<Long>(true, nowTime.getTime());
	}
	
}
