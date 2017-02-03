package Builder;

import java.awt.Color;
import java.security.GeneralSecurityException;
import java.time.Year;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import Builder.L4U.Builder;

public class L4U {
	private String companyName;
	private Color color;
	private int licenceNum;
	private Date year;
	// private enum acsLevel;
	private double price;
	private boolean sumRoof;

	private L4U(Builder builder) {
		this.companyName = builder.companyName;
		this.color = builder.color;
		this.licenceNum = builder.licenceNum;
		this.year = builder.year;
		this.price = builder.price;
		this.sumRoof = builder.sumRoof;
	}

	/** Getters and Setters **/
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isSumRoof() {
		return sumRoof;
	}

	public void setSumRoof(boolean sumRoof) {
		this.sumRoof = sumRoof;
	}

	public int getLicenceNum() {
		return licenceNum;
	}

	/** Builder Class **/
	public static class Builder {
		private String companyName;
		private Color color;
		private int licenceNum;
		private Date year;
		// private enum acsLevel;
		private double price;
		private boolean sumRoof;
		private static Random randomize = new Random();

		public Builder(String company, Color color) {
			this.companyName = company;
			this.color = color;
			this.licenceNum = randomize.nextInt(100000) + 1000000;
		}

		public L4U builder() {
			return new L4U(this);
		}

		public Builder companyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		public Builder color(Color color) {
			this.color = color;
			return this;
		}

		public Builder year(Date year) {
			this.year = year;
			return this;
		}

		public Builder price(double price) {
			this.price = price;
			return this;
		}

		public Builder sumRoof(boolean sumRoof) {
			this.sumRoof = sumRoof;
			return this;
		}
	}

	public String toString() {
		return "Company: " + getCompanyName() + "\nYear: " + getYear() + "\n" + "License: " + getLicenceNum()
				+ "\nPrice: " + getPrice();
	}

	public void print() {
		JOptionPane.showMessageDialog(null, "l4U " + toString());
	}
}
