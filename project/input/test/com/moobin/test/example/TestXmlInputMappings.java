package com.moobin.test.example;

import com.moobin.input.xml.XmlInputMapping;

public class TestXmlInputMappings {

	public static XmlInputMapping<Currency> currencyMappings() {
		return new XmlInputMapping<Currency>(Currency.class, "/ISO_4217/CcyTbl/CcyNtry[Ccy][not(Ccy=preceding-sibling::*/Ccy)]")
			.map("code", "Ccy")
			.map("name", "CcyNm")
			.map("currencyNumber", "CcyNbr")
			.map("mnrUnit", "CcyMnrUnts");
	}
	
	public static XmlInputMapping<Country> countryMappings() {
		return new XmlInputMapping<>(Country.class, "/countries/country")
 			.map("name", "@name")
			.map("alpha2", "@alpha-2")
			.map("alpha3", "@alpha-3")
			.map("countryNumber", "@country-code")
			.map("iso3166_2", "@iso_3166-2")
			.map("region", "@region-code")
			.map("subRegion", "@sub-region-code");
	}

}
