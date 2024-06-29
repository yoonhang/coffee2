package com.example.coffee.v2.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.coffee.v2.service.CoffeeV2Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/v2")
@Log4j2
public class CoffeeV2 {

    @Autowired
    CoffeeV2Service v2Service;

    @GetMapping("/coffee")
    public String doCoffee(Model model) {

        /* 전체리스트 조회 */
        List<Map<String, String>> list = v2Service.doCoffeeList();

        model.addAttribute("list", list);
        System.out.println(list);

        return "/v2/coffee";
    }

    @GetMapping("/coffeeAjax")
    @ResponseBody
    public String doCoffeeAjax(Model model) {

        String strAcc = "{\"coffee_list\":[";
        strAcc += "{\"coffee_id\",\"150\"}";
        strAcc += "]}";

        return strAcc;
    }

    @PostMapping("/coffee")
    public String doCoffeePost(HttpServletRequest request, Model model) {

        String strStart_date = request.getParameter("start_date");
        String strEnd_date = request.getParameter("end_date");
        String strName = request.getParameter("name");
        String strKind = request.getParameter("kind");
        // log.info(strKind);

        /* 전체리스트 조회 - 오버로딩 */
        List<Map<String, String>> list = v2Service.doCoffeeList(strStart_date, strEnd_date, strName, strKind);

        model.addAttribute("list", list);
        // System.out.println(list);

        return "/v2/coffee";
    }

    @PostMapping("/coffeeAjaxPost")
    @ResponseBody
    public String doCoffeePostAjax(HttpServletRequest request) {

        String strStart_date = request.getParameter("start_date");
        String strEnd_date = request.getParameter("end_date");
        String strName = request.getParameter("name");
        String strKind = request.getParameter("kind");

        /* 전체리스트 조회 - 오버로딩 */
        List<Map<String, String>> list = v2Service.doCoffeeList(strStart_date, strEnd_date, strName, strKind);

        JSONArray jsonArray = new JSONArray();
        for (Map<String, String> fMap : list) {
            JSONObject resObject = new JSONObject(fMap);
            // log.info(resObject);
            jsonArray.put(resObject);
        }

        String strArr = "{\"coffee_list\":";
        strArr += String.valueOf(jsonArray);
        strArr += "}";
        log.info("strArr: -------------------" + strArr);

        return "strArr";
    }

    /* 등록하기 Get */
    @GetMapping("/insert")
    public String doInsert() {
        return "/v2/coffee_Ins";
    }

    /* 등록하기 Post , HttpServletRequest 사용 */
    @PostMapping("/insert")
    public String doInsertPost(@RequestParam(value = "name") String name,
            @RequestParam(value = "kind") String kind,
            @RequestParam(value = "price") String price,
            Model model) {
        // log.info(name + kind + price);
        int intI = v2Service.doInsert(name, kind, price);
        // log.info("doInsertPost " + intI);

        return "redirect:/v2/coffee";
    }

    /* 수정하기 Get , @RequestParam 사용 */
    @GetMapping("/update")
    public String doUpdate(@RequestParam(value = "coffee_id") String strCoffee_id, Model model) {

        Map<String, String> map = v2Service.doListOne(strCoffee_id);
        model.addAttribute("map", map);

        return "/v2/coffee_Up";
    }

    /* 수정하기 Post , @RequestParam 사용 */
    @PostMapping("/update")
    public String doUpdatePost(
            @RequestParam(value = "coffee_id") String strCoffee_id,
            @RequestParam(value = "name") String strName,
            @RequestParam(value = "kind") String strKind,
            @RequestParam(value = "price") String strPrice) {

        int intI = v2Service.doUpdate(strCoffee_id, strName, strKind, strPrice);
        // log.info("doUpdatePost Coffee ID: " + strCoffee_id + ", Result: " + intI);

        return "redirect:/v2/coffee";
    }

    /* 삭제하기 1 row , @RequestParam 사용 */
    @GetMapping("/delete")
    public String doDelete(@RequestParam(value = "coffee_id") String strCoffee_id) {

        int intI = v2Service.doDelete(strCoffee_id);
        // log.info("Deleted Coffee ID: " + strCoffee_id + ", Result: " + intI);

        return "redirect:/v2/coffee";
    }

}