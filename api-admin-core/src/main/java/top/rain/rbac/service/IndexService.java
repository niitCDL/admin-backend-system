package top.rain.rbac.service;

import top.rain.rbac.vo.BarVO;
import top.rain.rbac.vo.LabelVO;
import top.rain.rbac.vo.PanelVO;

import java.util.List;
import java.util.Map;

public interface IndexService {

    /**
     * 后台首页统计1: 项目主要数据统计面板
     * @return
     */
    List<PanelVO> statistics1();

    /**
     * 后台首页统计2: echarts 柱状图
     * @return
     */
    BarVO statistics2(String type);

    /**
     * 后台首页统计3: 分类统计标签
     * @return
     */
    Map<String,List<LabelVO>> statistics3();
}
