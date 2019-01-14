package com.cpscs.omsorderbill.controller;

import com.cpscs.common.utils.Result;
import com.cpscs.omsorderbill.domain.Page;
import com.cpscs.omsorderbill.domain.TabQueryOrder;
import com.cpscs.omsorderbill.service.TabQueryOrderService;
import com.cpscs.omsorderbill.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/2 0002
 */
@RequestMapping()
@RestController
public class TabQueryOrderController {
    @Autowired
    private TabQueryOrderService tabQueryOrderService;



    @GetMapping(value="/excel")
    public void excelWrite(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, HttpServletResponse response){


        Page<TabQueryOrder> page = tabQueryOrderService.getOnePage(pageNo, pageSize);

        /*测试导出表格*/
        ExcelUtil<TabQueryOrder> excelUtil=new ExcelUtil<>();
        List<String> titleNames=Arrays.asList(
                new String [] {"ID","订单日期","订单加载日期","ETA日期","订单号","邮件号","主单邮件号","网络单号","产品编码","产品类型","收件人","电话","起运城市","目的省","目的市","目的区"});
        List<String> fieldNames=Arrays.asList(
                new String[]{"id","orderDate","loadDate","etaDate","dnNo","mailNo","mstMailNo","netNo",
                        "pdtCode","pdtType","recipient","telNo","despatchCity","destinationProvince","destinationCity","destinationCounty"}
        );
        File file = excelUtil.writeExcel(page.getRows(), TabQueryOrder.class,fieldNames,titleNames,"ResponseExcel.xls");
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }


    @GetMapping(value="/all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("admin:user:user")
    public Result orderAll(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){

        Page<TabQueryOrder> page=tabQueryOrderService.getOnePage(pageNo,pageSize);

        return Result.ok().put("page",page);
    }

    @PutMapping(value="/one",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result orderOneEdit(@RequestBody TabQueryOrder tabQueryOrder){
        //System.out.println(tabQueryOrder);
        if(tabQueryOrder==null){
            return Result.error(500,"请求数据报文错误");
        }
        if(tabQueryOrder.getId()==null||"".equals(tabQueryOrder.getId())){
            return Result.error(500,"未指定修改条目的id");
        }
        //根据id修改
        tabQueryOrder=tabQueryOrderService.editOnePage(tabQueryOrder);
        if(tabQueryOrder==null){
            return Result.error(501,"修改失败，请稍后再试");
        }
        return Result.ok().put("order",tabQueryOrder);
    }

    @PostMapping(value="/one")
    public Result orderOneAdd(@RequestBody TabQueryOrder tabQueryOrder){
        if(tabQueryOrder==null){
            return Result.error(500,"请求数据报文错误");
        }
        int result = tabQueryOrderService.addOnePage(tabQueryOrder);
        if(result==0){
            return Result.error(501,"修改失败，请稍后再试");
        }
        return Result.ok();
    }

    @DeleteMapping(value="/one")
    public Result orderOneRemove(@RequestParam(defaultValue = "-1") String id){
        if("-1".equals(id)){
            return Result.error(500,"id未指定");
        }
        int result = tabQueryOrderService.removeOnePage(id);
        if(result==0){
            return Result.error(501,"增添失败请稍后再试");
        }
        return Result.ok();

    }
    /**
     * 实现文件上传
     * */
    @ResponseBody
    @PostMapping(value="/excel")
    public Result excelRead(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error(500,"文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "D:/test";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            //处理传入的文件

                ExcelUtil<TabQueryOrder> excelUtil=new ExcelUtil<>();
                List<TabQueryOrder> result=excelUtil.readExcel(file, TabQueryOrder.class,new String[]{"id","orderDate","loadDate","etaDate","dnNo","mailNo","mstMailNo","netNo",
                        "pdtCode","pdtType","recipient","telNo","despatchCity","destinationProvince","destinationCity","destinationCounty"});
                for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i));
                    //存数据
                    tabQueryOrderService.addOnePage(result.get(i));
                }

            return Result.ok();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(dest.getAbsolutePath());
            return Result.error(500,"服务器内部错误");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Result.error(500,"文件写入异常");
        }


    }


}
