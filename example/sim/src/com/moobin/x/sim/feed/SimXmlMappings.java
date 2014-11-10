package com.moobin.x.sim.feed;

import com.moobin.input.xml.XmlInputMapping;
import com.moobin.x.sim.Country;
import com.moobin.x.sim.Currency;

public class SimXmlMappings extends XmlInputMapping<Object> {

	public static XmlInputMapping<Currency> currencyXmlMapping = 
		new XmlInputMapping<Currency>(Currency.class, "/ISO_4217/CcyTbl/CcyNtry[Ccy][not(Ccy=preceding-sibling::*/Ccy)]") {
		{
			mapXpath("code", "Ccy");
			mapXpath("name", "CcyNm");
			mapXpath("currencyNumber", "CcyNbr");
			mapXpath("mnrUnit", "CcyMnrUnts");
		}
	};

	public static XmlInputMapping<Country> countryXmlMapping = 
		new XmlInputMapping<Country>(Country.class, "/countries/country") {
		{
			mapAttribute("name");
			mapAttribute("alpha2", "alpha-2");
			mapAttribute("alpha3", "alpha-3");
			mapAttribute("countryNumber", "country-code");
			mapAttribute("iso3166_2", "iso_3166-2");
			mapAttribute("region", "region-code");
			mapAttribute("subRegion", "sub-region-code");
		}
	};

}
