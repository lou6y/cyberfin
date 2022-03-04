package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "LoanBill")
public class LoanBill implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ref")
        private long ref; 
        private double principalamouant;
        private double interstamount;
        private double totalamount;
        private boolean billstatus;
        
        @Temporal(TemporalType.DATE)
        private Date paymentdate;
       
        @Temporal(TemporalType.DATE)
        private Date payedat;
        
        @ManyToOne 
		Loan loan;

		public long getRef() {
			return ref;
			
			
		}

		public void setRef(int ref) {
			this.ref = ref;
		}

		public double getPrincipalamouant() {
			return principalamouant;
		}

		public void setPrincipalamouant(double principalamouant) {
			this.principalamouant = principalamouant;
		}

		public double getInterstamount() {
			return interstamount;
		}

		public void setInterstamount(double interstamount) {
			this.interstamount = interstamount;
		}

		public double getTotalamount() {
			return totalamount;
		}

		public void setTotalamount(double totalamount) {
			this.totalamount = totalamount;
		}

		public boolean isBillstatus() {
			return billstatus;
		}

		public void setBillstatus(boolean billstatus) {
			this.billstatus = billstatus;
		}

		public Date getPaymentdate() {
			return paymentdate;
		}

		public void setPaymentdate(Date paymentdate) {
			this.paymentdate = paymentdate;
		}

		public Date getPayedat() {
			return payedat;
		}

		public void setPayedat(Date payedat) {
			this.payedat = payedat;
		}

		public LoanBill(double principalamouant, double interstamount, double totalamount, boolean billstatus,
				Date paymentdate, Date payedat) {
			super();
			this.principalamouant = principalamouant;
			this.interstamount = interstamount;
			this.totalamount = totalamount;
			this.billstatus = billstatus;
			this.paymentdate = paymentdate;
			this.payedat = payedat;
		}
}
