package cf.pivotal.quoteClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import net.minidev.json.JSONArray;

import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.gson.GsonDecoder;

public class QuoteDecoder extends GsonDecoder {

	@Override
	public Object decode(Response response, Type type) throws IOException,
			DecodeException, FeignException {

		Response.Body body = response.body();
		if (body == null) {
			return null;
		}

		// System.out.println(Util.toString(body.asReader()));

		Type typeOfListOfQuote = new TypeToken<List<Quote>>() {
		}.getType();
		if (Quote.class.equals(type) || typeOfListOfQuote.equals(type)) {
			return processQuoteBody(body);
		}

		if (MarketSummary.class.equals(type)) {
			return processMarketSummaryBody(body);
		}

		return super.decode(response, type);
	}

	private BigDecimal getBigDecimal(ReadContext ctx, String path) {
		Object o = ctx.read(path);
		if (o == null) {
			return new BigDecimal(0);
		}
		return new BigDecimal(o.toString());
	}

	private Object processQuoteBody(Response.Body body) throws IOException {
		ReadContext ctx = JsonPath.parse(body.asInputStream());

		Object o = ctx.read("$");
		if (o instanceof JSONArray) {
			return quotesFromJson(ctx);
		}

		return quoteFromJson(ctx);
	}

	private MarketSummary processMarketSummaryBody(Response.Body body)
			throws IOException {
		ReadContext ctx = JsonPath.parse(body.asInputStream());

		MarketSummary ms = new MarketSummary();
		ms.setChange(getBigDecimal(ctx, "$.change"));
		ms.setPercentGain(getBigDecimal(ctx, "$.percentGain"));
		ms.setSummaryDate(new Date());
		ms.setTradeStockIndexAverage(getBigDecimal(ctx, "$.average"));
		ms.setTradeStockIndexOpenAverage(getBigDecimal(ctx, "$.open"));
		ms.setTradeStockIndexVolume(getBigDecimal(ctx, "$.volume"));

		return ms;
	}

	private Quote quoteFromJson(ReadContext ctx) {
		Quote q = new Quote();
		q.setChange1(getBigDecimal(ctx, "$.Change"));
		String name = ctx.read("$.Name");
		if (name != null) {
			q.setCompanyname(name);
		}
		q.setOpen1(getBigDecimal(ctx, "$.PreviousClose"));
		q.setHigh(getBigDecimal(ctx, "$.DaysHigh"));
		q.setLow(getBigDecimal(ctx, "$.DaysLow"));
		q.setOpen1(getBigDecimal(ctx, "$.PreviousClose"));
		q.setPrice(getBigDecimal(ctx, "$.Price"));
		q.setSymbol(ctx.read("$.Symbol").toString());
		q.setVolume(getBigDecimal(ctx, "$.Volume"));

		return q;
	}

	private List<Quote> quotesFromJson(ReadContext ctx) {
		ArrayList<Quote> quotes = new ArrayList<Quote>();

		JSONArray qs = ctx.read("$");
		for (int i = 0; i < qs.size(); i++) {
			Quote q = new Quote();
			q.setChange1(getBigDecimal(ctx, "$.[" + i + "].Change"));
			String name = ctx.read("$.[" + i + "].Name");
			if (name != null) {
				q.setCompanyname(name);
			}
			q.setOpen1(getBigDecimal(ctx, "$.[" + i + "].PreviousClose"));
			q.setHigh(getBigDecimal(ctx, "$.[" + i + "].DaysHigh"));
			q.setLow(getBigDecimal(ctx, "$.[" + i + "].DaysLow"));
			q.setPrice(getBigDecimal(ctx, "$.[" + i + "].Price"));
			q.setSymbol(ctx.read("$.[" + i + "].Symbol").toString());
			q.setVolume(getBigDecimal(ctx, "$.[" + i + "].Volume"));
			quotes.add(q);
		}

		return quotes;
	}

	public static String formatSymbols(Set<String> symbols) {
		if (symbols == null || symbols.size() < 1) {
			return "()";
		}

		StringBuffer sb = new StringBuffer("(");
		for (String s : symbols) {
			sb.append("\"");
			sb.append(s);
			sb.append("\",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}
}