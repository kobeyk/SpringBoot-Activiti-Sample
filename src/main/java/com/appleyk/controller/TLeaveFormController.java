package com.appleyk.controller;


import com.appleyk.common.TResult;
import com.appleyk.model.TLeaveForm;
import com.appleyk.service.TLeaveformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Appleyk
 * @since 2020-03-01
 */
@RestController
@RequestMapping("/leaveform")
@CrossOrigin
public class TLeaveFormController {

    @Autowired
    private TLeaveformService leaveformService;

    /**先申请一个请假条，这时候，请假信息并没有填写，注意！！！*/
    @PostMapping("/apply")
    public TResult apply(@RequestBody TLeaveForm leaveForm) throws Exception{
        return TResult.ok(leaveformService.apply(leaveForm));
    }

    /**查询当前任务*/
    @GetMapping("/task/query/{id}")
    public TResult queryTasks(@PathVariable("id") Long id){
        return TResult.ok(leaveformService.queryByBusinessKey(id));
    }

}

