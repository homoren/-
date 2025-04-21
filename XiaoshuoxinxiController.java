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

import com.entity.XiaoshuoxinxiEntity;
import com.entity.view.XiaoshuoxinxiView;

import com.service.XiaoshuoxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 小说信息
 * 后端接口
 * @author 
 * @email 
 * @date 2023-04-19 19:19:08
 */
@RestController
@RequestMapping("/xiaoshuoxinxi")
public class XiaoshuoxinxiController {
    @Autowired
    private XiaoshuoxinxiService xiaoshuoxinxiService;

    @Autowired
    private StoreupService storeupService;

    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiaoshuoxinxiEntity xiaoshuoxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("zuozhe")) {
			xiaoshuoxinxi.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();

		PageUtils page = xiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuoxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XiaoshuoxinxiEntity xiaoshuoxinxi, 
		HttpServletRequest request){
        EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();

		PageUtils page = xiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuoxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiaoshuoxinxiEntity xiaoshuoxinxi){
       	EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiaoshuoxinxi, "xiaoshuoxinxi")); 
        return R.ok().put("data", xiaoshuoxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiaoshuoxinxiEntity xiaoshuoxinxi){
        EntityWrapper< XiaoshuoxinxiEntity> ew = new EntityWrapper< XiaoshuoxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiaoshuoxinxi, "xiaoshuoxinxi")); 
		XiaoshuoxinxiView xiaoshuoxinxiView =  xiaoshuoxinxiService.selectView(ew);
		return R.ok("查询小说信息成功").put("data", xiaoshuoxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiaoshuoxinxiEntity xiaoshuoxinxi = xiaoshuoxinxiService.selectById(id);
		xiaoshuoxinxi.setClicktime(new Date());
		xiaoshuoxinxiService.updateById(xiaoshuoxinxi);
        return R.ok().put("data", xiaoshuoxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiaoshuoxinxiEntity xiaoshuoxinxi = xiaoshuoxinxiService.selectById(id);
		xiaoshuoxinxi.setClicktime(new Date());
		xiaoshuoxinxiService.updateById(xiaoshuoxinxi);
        return R.ok().put("data", xiaoshuoxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request){
    	xiaoshuoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(xiaoshuoxinxi);
        xiaoshuoxinxiService.insert(xiaoshuoxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request){
    	xiaoshuoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(xiaoshuoxinxi);
        xiaoshuoxinxiService.insert(xiaoshuoxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiaoshuoxinxi);
        xiaoshuoxinxiService.updateById(xiaoshuoxinxi);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xiaoshuoxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request,String pre){
        EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = xiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuoxinxi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（按收藏推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "xiaoshuoleixing";
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "xiaoshuoxinxi").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<XiaoshuoxinxiEntity> xiaoshuoxinxiList = new ArrayList<XiaoshuoxinxiEntity>();
        //去重
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity s : storeups) {
                xiaoshuoxinxiList.addAll(xiaoshuoxinxiService.selectList(new EntityWrapper<XiaoshuoxinxiEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = xiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuoxinxi), params), params));
        List<XiaoshuoxinxiEntity> pageList = (List<XiaoshuoxinxiEntity>)page.getList();
        if(xiaoshuoxinxiList.size()<limit) {
            int toAddNum = (limit-xiaoshuoxinxiList.size())<=pageList.size()?(limit-xiaoshuoxinxiList.size()):pageList.size();
            for(XiaoshuoxinxiEntity o1 : pageList) {
                boolean addFlag = true;
                for(XiaoshuoxinxiEntity o2 : xiaoshuoxinxiList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    xiaoshuoxinxiList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(xiaoshuoxinxiList.size()>limit) {
            xiaoshuoxinxiList = xiaoshuoxinxiList.subList(0, limit);
        }
        page.setList(xiaoshuoxinxiList);
        return R.ok().put("data", page);
    }







}
