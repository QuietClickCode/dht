package com.retailers.sbj.common.vo;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class EmpDataVo {
    private String empName;
    private Long tid;
    private Long agentTodayPresent;
    private Long agentNoPresent;
    private Long agentRegisTotal;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getAgentNoPresent() {
        return agentNoPresent;
    }

    public void setAgentNoPresent(Long agentNoPresent) {
        this.agentNoPresent = agentNoPresent;
    }

    public Long getAgentRegisTotal() {
        return agentRegisTotal;
    }

    public void setAgentRegisTotal(Long agentRegisTotal) {
        this.agentRegisTotal = agentRegisTotal;
    }

    public Long getAgentTodayPresent() {
        return agentTodayPresent;
    }

    public void setAgentTodayPresent(Long agentTodayPresent) {
        this.agentTodayPresent = agentTodayPresent;
    }
}
