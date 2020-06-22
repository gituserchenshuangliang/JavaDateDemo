package com.date.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * Date Calendar DateFormat
 * @author Cherry
 * 2020年4月15日
 */
@DisplayName("JavaDateAndTimeDemo")
public class JavaDateAndTimeDemo {
	
//	@BeforeEach
	public void dateShow() {
		//时间上的顺时  Date 除getTime()和setTime()外其他的方法大多废弃
		long temp = System.currentTimeMillis();
		Date date = new Date();
		date.setTime(temp);
		date.getTime();
	}
	
	
	//DateFormat处理时间字符串
	@Test
	public void dateFormatShow() throws ParseException {
		DateFormat[] f = new DateFormat[]{
						DateFormat.getTimeInstance(),
						DateFormat.getDateInstance(DateFormat.LONG),
						DateFormat.getDateTimeInstance()};
		List<DateFormat> list = Arrays.asList(f);
		list.forEach((d) -> {
			//System.out.println(d.format(new Date()));
		});
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String date = sdf.format(new Date());

		Date m = sdf.parse("1990-12-01 00:00:00");
		System.out.println(sdf.format(m));
		
		Calendar c = Calendar.getInstance();
		c.set(1990, Calendar.DECEMBER, 1);
		System.out.println(c.get(Calendar.MONDAY));
		
		long year = ageShow(c, Calendar.YEAR);
		long d = ageShow(c, Calendar.DATE);
		System.out.printf("%d岁，%d天",year,d);
	}
	
	public long ageShow(Calendar begin,int type) {
		Calendar c = (Calendar) begin.clone();
		Calendar n = Calendar.getInstance();
		long i = 0;
		while(c.before(n)) {
			c.add(type, 1);
			i++;
		}
		System.out.println(i);
		return i;
	}
	
	
	@Test
	@Disabled
	public void timeZoneShow() {
		TimeZone tz = TimeZone.getDefault();
		System.out.println(tz.getID());
		System.out.println(Calendar.getInstance(tz).getTimeZone().getID());
		System.out.println(Calendar.getInstance().getTimeZone().getID());
		
		Calendar c = Calendar.getInstance();
		TimeZone tz1 = TimeZone.getDefault(); 
		tz1.setID("Asia/BeiJin");
		c.setTimeZone(tz1);
		System.out.println(tz1.getID());
	}
	
	@Test
	public void localDateTime() {
		int hour = 23;
		int minute = 46;
		int year = 1990;
		int month = 12;
		int day = 23;
		//now()方法获取实例
		LocalTime lt = LocalTime.of(hour, minute);
		LocalDate ld = LocalDate.of(year, month, day);
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		System.out.println(ldt.toString());
	}
	
	@Test
	public void periodShow() {
		//Temporal终极接口
		//计算时间差	Period只能接受不小于日的单位，Duration可以接受LocalDateTime和LocalTime
		LocalDate ld = LocalDate.parse("1990-12-01");
		LocalDate ld1 = LocalDate.now();
		Period period = Period.between(ld, ld1);
		System.out.printf("你活了%d年%d月%d天!",period.getYears(),period.getMonths(),period.getDays());
		
		LocalDateTime ldt = LocalDateTime.of(1990, 10, 25, 12, 12,35);
		LocalDateTime ldt1 = LocalDateTime.now();
		Duration duration = Duration.between(ldt, ldt1);
		System.out.printf("你活了%d年%d月%d天!",period.getYears(),period.getMonths(),period.getDays());
	}
	
	@Test
	public void monthShow() {
		for(Month m : Month.values()) {
			System.out.print(m.ordinal());
			System.out.print(m.getValue());
			System.out.println(m);
		}
	}
	
}
