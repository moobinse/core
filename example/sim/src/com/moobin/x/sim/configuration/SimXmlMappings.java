package com.moobin.x.sim.configuration;

import com.moobin.input.xml.XmlInputMapping;
import com.moobin.x.sim.Country;
import com.moobin.x.sim.Currency;

public class SimXmlMappings extends XmlInputMapping<Object> {

	public static XmlInputMapping<Currency> currencyXmlMapping = 
		new XmlInputMapping<>(Currency.class, "/ISO_4217/CcyTbl/CcyNtry[Ccy][not(Ccy=preceding-sibling::*/Ccy)]")
			.map("code", "Ccy")
			.map("name", "CcyNm")
			.map("currencyNumber", "CcyNbr")
			.map("mnrUnit", "CcyMnrUnts");

	public static XmlInputMapping<Country> countryXmlMapping = 
		new XmlInputMapping<>(Country.class, "/countries/country")
 			.map("name", "@name")
			.map("alpha2", "@alpha-2")
			.map("alpha3", "@alpha-3")
			.map("countryNumber", "@country-code")
			.map("iso3166_2", "@iso_3166-2")
			.map("region", "@region-code")
			.map("subRegion", "@sub-region-code");

}
