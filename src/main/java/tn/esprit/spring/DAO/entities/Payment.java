package tn.esprit.spring.DAO.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Payment implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int payment_id;
    private Long account_id;
    private String beneficiary;
    private String beneficiary_acc_no;
    private double amount;
    private String reference_no;
    private String status;
    private String reason_code;
    private LocalDateTime created_at;

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getBeneficiary_acc_no() {
        return beneficiary_acc_no;
    }

    public void setBeneficiary_acc_no(String beneficiary_acc_no) {
        this.beneficiary_acc_no = beneficiary_acc_no;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason_code() {
        return reason_code;
    }

    public void setReason_code(String reason_code) {
        this.reason_code = reason_code;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Payment() {
		super();
	}
    
	public Payment(int payment_id, Long account_id, String beneficiary, String beneficiary_acc_no, double amount,
			String reference_no, String status, String reason_code, LocalDateTime created_at) {
		super();
		this.payment_id = payment_id;
		this.account_id = account_id;
		this.beneficiary = beneficiary;
		//acc number
		this.beneficiary_acc_no = beneficiary_acc_no;
		this.amount = amount;
		this.reference_no = reference_no;
		this.status = status;
		this.reason_code = reason_code;
		this.created_at = created_at;
	}
        
    
}
