package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ChapterxiaoshuoxinxiEntity;
import com.entity.view.ChapterxiaoshuoxinxiView;

import com.service.ChapterxiaoshuoxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 小说信息章节表
 * 后端接口
 * @author 
 * @email 
 * @date 2023-04-19 19:19:08
 */
@RestController
@RequestMapping("/chapterxiaoshuoxinxi")
public class ChapterxiaoshuoxinxiController {
    @Autowired
    private ChapterxiaoshuoxinxiService chapterxiaoshuoxinxiService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi,
		HttpServletRequest request){
        EntityWrapper<ChapterxiaoshuoxinxiEntity> ew = new EntityWrapper<ChapterxiaoshuoxinxiEntity>();

		PageUtils page = chapterxiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chapterxiaoshuoxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi, 
		HttpServletRequest request){
        EntityWrapper<ChapterxiaoshuoxinxiEntity> ew = new EntityWrapper<ChapterxiaoshuoxinxiEntity>();

		PageUtils page = chapterxiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chapterxiaoshuoxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi){
       	EntityWrapper<ChapterxiaoshuoxinxiEntity> ew = new EntityWrapper<ChapterxiaoshuoxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chapterxiaoshuoxinxi, "chapterxiaoshuoxinxi")); 
        return R.ok().put("data", chapterxiaoshuoxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi){
        EntityWrapper< ChapterxiaoshuoxinxiEntity> ew = new EntityWrapper< ChapterxiaoshuoxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chapterxiaoshuoxinxi, "chapterxiaoshuoxinxi")); 
		ChapterxiaoshuoxinxiView chapterxiaoshuoxinxiView =  chapterxiaoshuoxinxiService.selectView(ew);
		return R.ok("查询小说信息章节表成功").put("data", chapterxiaoshuoxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi = chapterxiaoshuoxinxiService.selectById(id);
        return R.ok().put("data", chapterxiaoshuoxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi = chapterxiaoshuoxinxiService.selectById(id);
        return R.ok().put("data", chapterxiaoshuoxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi, HttpServletRequest request){
    	chapterxiaoshuoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chapterxiaoshuoxinxi);
        chapterxiaoshuoxinxiService.insert(chapterxiaoshuoxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi, HttpServletRequest request){
    	chapterxiaoshuoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chapterxiaoshuoxinxi);
        chapterxiaoshuoxinxiService.insert(chapterxiaoshuoxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChapterxiaoshuoxinxiEntity chapterxiaoshuoxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chapterxiaoshuoxinxi);
        chapterxiaoshuoxinxiService.updateById(chapterxiaoshuoxinxi);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chapterxiaoshuoxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
