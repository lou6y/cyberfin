package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table( name = "Loan")
public class Loan implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idLoan")
        private long idloan; 
        private int iduser;
        private double loanamount;
        private int nbrpayments;
        private String email;
        private String reason;
        private float annualincom;
        private int status;
        private float interstrate;
        private int score;
        private int age;
        private String guarantor ;
        
      
        @Temporal(TemporalType.DATE)
        private Date startdate;
        
        @Temporal(TemporalType.DATE)
        private Date requestdate;
        
        @Temporal(TemporalType.DATE)
        private Date nextpayment;
        
        @OneToMany(cascade = CascadeType.ALL, mappedBy="loan")
        private Set<LoanBill>loanbills;

		public long getIdloan() {
			return idloan;
		}

		public void setIdloan(int idloan) {
			this.idloan = idloan;
		}

		public int getIduser() {
			return iduser;
		}

		public void setIduser(int iduser) {
			this.iduser = iduser;
		}

		public double getLoanamount() {
			return loanamount;
		}

		public void setLoanamount(double loanamount) {
			this.loanamount = loanamount;
		}

		public int getNbrpayments() {
			return nbrpayments;
		}

		public void setNbrpayments(int nbrpayments) {
			this.nbrpayments = nbrpayments;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public float getAnnualincom() {
			return annualincom;
		}

		public void setAnnualincom(float annualincom) {
			this.annualincom = annualincom;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public float getInterstrate() {
			return interstrate;
		}

		public void setInterstrate(float interstrate) {
			this.interstrate = interstrate;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGuarantor() {
			return guarantor;
		}

		public void setGuarantor(String guarantor) {
			this.guarantor = guarantor;
		}

		public Date getStartdate() {
			return startdate;
		}

		public void setStartdate(Date startdate) {
			this.startdate = startdate;
		}

		public Date getRequestdate() {
			return requestdate;
		}

		public void setRequestdate(Date requestdate) {
			this.requestdate = requestdate;
		}

		public Date getNextpayment() {
			return nextpayment;
		}

		public void setNextpayment(Date nextpayment) {
			this.nextpayment = nextpayment;
		}

		public Loan(int iduser, double loanamount, int nbrpayments, String email, String reason, float annualincom,
				int status, float interstrate, int score, int age, String guarantor, Date startdate, Date requestdate,
				Date nextpayment) {
			super();
			this.iduser = iduser;
			this.loanamount = loanamount;
			this.nbrpayments = nbrpayments;
			this.email = email;
			this.reason = reason;
			this.annualincom = annualincom;
			this.status = status;
			this.interstrate = interstrate;
			this.score = score;
			this.age = age;
			this.guarantor = guarantor;
			this.startdate = startdate;
			this.requestdate = requestdate;
			this.nextpayment = nextpayment;
		}
		
		@ManyToOne
		Account account;
       
}
