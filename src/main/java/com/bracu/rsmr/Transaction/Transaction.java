package com.bracu.rsmr.Transaction;

import com.bracu.rsmr.Employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String transId;
    private String srcId;
    private String dstId;
    private Double amount;
    private boolean status;
    private String employeeName;
    public Transaction() {}

    public Transaction(String srcId, String dstId, Double amount) {
        this.srcId = srcId;
        this.dstId = dstId;
        this.amount = amount;
        this.status = this.amount <= 1000000;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getDstId() {
        return dstId;
    }

    public void setDstId(String dstId) {
        this.dstId = dstId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setEmployee(Employee employee) {
        this.employeeName = employee.getUsername();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
