package cf.pivotal.quoteClient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MarketSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal average;
	private BigDecimal open;
	private BigDecimal volume;
	private BigDecimal change;

	public MarketSummary(Quote index, List<Quote> topLosers,
			List<Quote> topGainers) {
		load(index);
	}

	private void load(Quote index) {
		if (index == null) {
			return;
		}
		setAverage(index.getPrice());
		setOpen(index.getOpen1());
		setVolume(index.getVolume());
		setChange(index.getChange1());
	}

	public BigDecimal getAverage() {
		return average;
	}

	private void setAverage(BigDecimal average) {
		this.average = average;
	}

	public BigDecimal getOpen() {
		return open;
	}

	private void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	private void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getChange() {
		return change;
	}

	private void setChange(BigDecimal change) {
		this.change = change;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
