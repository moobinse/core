package com.moobin.test.example;

import com.moobin.input.xml.XmlInputMapping;

public class TestXmlInputMappings {

	public static XmlInputMapping<Currency> currencyMappings() {
		return new XmlInputMapping<Currency>(Currency.class, "/ISO_4217/CcyTbl/CcyNtry[Ccy][not(Ccy=preceding-sibling::*/Ccy)]") {
			
			{
				mapTextElement("code", "Ccy");
				mapTextElement("name", "CcyNm");
				mapTextElement("currencyNumber", "CcyNbr");
				mapTextElement("mnrUnit", "CcyMnrUnts");
			}
		};
	}
	
	public static XmlInputMapping<Country> countryMappings() {
		return new XmlInputMapping<Country>(Country.class, "/countries/country") {
			{
				mapAttribute("name", "name");
				mapAttribute("alpha2", "alpha-2");
				mapAttribute("alpha3", "alpha-3");
				mapAttribute("countryNumber", "country-code");
				mapAttribute("iso3166_2", "iso_3166-2");
				mapAttribute("region", "region-code");
				mapAttribute("subRegion", "sub-region-code");
			}
		};
	}

}
