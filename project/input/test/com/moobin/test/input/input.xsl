<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		{
			"Country" : [<xsl:apply-templates select="//CcyNtry" mode="country"/>],
			"Currency" : [<xsl:apply-templates select="//CcyNtry" mode="currency"/>]
		}
	</xsl:template>

	<xsl:template match="CcyNtry" mode="country">
		{
			name : "<xsl:value-of select="CtryNm"/>",
			currencyCode : "<xsl:value-of select="Ccy"/>",
			currencyNumber : <xsl:value-of select="CcyNbr"/>
		},
	</xsl:template>

	<xsl:template match="CcyNtry" mode="currency">
		{
			name : "<xsl:value-of select="CcyNm"/>",
			currencyCode : "<xsl:value-of select="Ccy"/>",
			currencyNumber : <xsl:value-of select="CcyNbr"/>,
			minorUnits : <xsl:value-of select="CcyMnrUnts"/>
		}
	</xsl:template>
	
</xsl:stylesheet>