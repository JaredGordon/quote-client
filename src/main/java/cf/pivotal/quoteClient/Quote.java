/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cf.pivotal.quoteClient;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Quote implements Serializable {

	private static final long serialVersionUID = 1L;

	private String quoteid = "";

	public String getQuoteid() {
		return this.quoteid;
	}

	public void setQuoteid(String s) {
		if (s != null) {
			this.quoteid = s;
		}
	}

	private BigDecimal low;

	private BigDecimal open1;

	private BigDecimal volume;

	private BigDecimal price;

	private BigDecimal high;

	private String companyname;

	private String symbol = "";

	private BigDecimal change1;

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getOpen1() {
		return open1;
	}

	public void setOpen1(BigDecimal open1) {
		this.open1 = open1;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String s) {
		if (s != null) {
			this.symbol = s;
			setQuoteid(s);
		}
	}

	public BigDecimal getChange1() {
		return change1;
	}

	public void setChange1(BigDecimal change1) {
		this.change1 = change1;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
